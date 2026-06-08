package com.nunesd66.digitalbank.application;

import com.nunesd66.digitalbank.domain.event.TransferenciaConcluidaEvent;
import com.nunesd66.digitalbank.domain.exception.ContaNaoEncontradaException;
import com.nunesd66.digitalbank.domain.model.Conta;
import com.nunesd66.digitalbank.domain.model.Transferencia;
import com.nunesd66.digitalbank.domain.repository.ContaRepository;
import com.nunesd66.digitalbank.domain.repository.TransferenciaRepository;
import com.nunesd66.digitalbank.infrastructure.persistence.transferencia.TransferenciaMapper;
import com.nunesd66.digitalbank.infrastructure.web.dto.TransferenciaRequest;
import com.nunesd66.digitalbank.infrastructure.web.dto.TransferenciaResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaveTransferenciaUseCaseTest {

    @Mock private TransferenciaRepository transferenciaRepository;
    @Mock private ContaRepository contaRepository;
    @Mock private ApplicationEventPublisher eventPublisher;
    @Mock private TransferenciaMapper mapper;

    @InjectMocks
    private SaveTransferenciaUseCase useCase;

    @Test
    void deveRealizarTransferenciaComSucesso() {
        Conta remetente    = new Conta(1L, "Ana", new BigDecimal("500.00"));
        Conta destinatario = new Conta(2L, "Bob", new BigDecimal("100.00"));
        TransferenciaRequest req = new TransferenciaRequest(1L, 2L,
                new BigDecimal("200.00"));

        Transferencia transferenciaFake = new Transferencia(
                10L, remetente, destinatario,
                new BigDecimal("200.00"), LocalDateTime.now());

        TransferenciaResponse responseFake = new TransferenciaResponse(
                10L, "Ana", "Bob", new BigDecimal("200.00"), LocalDateTime.now());

        when(contaRepository.findByIdForUpdate(1L)).thenReturn(remetente);
        when(contaRepository.findByIdForUpdate(2L)).thenReturn(destinatario);
        when(transferenciaRepository.save(any())).thenReturn(transferenciaFake);
        when(mapper.toResponse(any())).thenReturn(responseFake);

        TransferenciaResponse result = useCase.execute(req);

        assertNotNull(result);
        assertEquals(10L, result.id());
        verify(contaRepository, times(2)).save(any());

        ArgumentCaptor<TransferenciaConcluidaEvent> captor = ArgumentCaptor.forClass(TransferenciaConcluidaEvent.class);

        verify(eventPublisher).publishEvent(captor.capture());

        TransferenciaConcluidaEvent event = captor.getValue();

        assertEquals(1L, event.idRemetente());
        assertEquals(2L, event.idDestinatario());
        assertEquals(new BigDecimal("200.00"), event.valor());
    }

    @Test
    void deveLancarExcecaoQuandoContaNaoExistir() {
        when(contaRepository.findByIdForUpdate(99L)).thenThrow(new ContaNaoEncontradaException("Conta não encontrada"));

        TransferenciaRequest req = new TransferenciaRequest(99L, 2L, new BigDecimal("100.00"));

        assertThrows(ContaNaoEncontradaException.class, () -> useCase.execute(req));
    }
}
