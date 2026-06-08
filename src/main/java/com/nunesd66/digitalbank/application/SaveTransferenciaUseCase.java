package com.nunesd66.digitalbank.application;

import com.nunesd66.digitalbank.domain.event.TransferenciaConcluidaEvent;
import com.nunesd66.digitalbank.domain.model.Conta;
import com.nunesd66.digitalbank.domain.model.Transferencia;
import com.nunesd66.digitalbank.domain.repository.ContaRepository;
import com.nunesd66.digitalbank.domain.repository.TransferenciaRepository;
import com.nunesd66.digitalbank.infrastructure.persistence.transferencia.TransferenciaMapper;
import com.nunesd66.digitalbank.infrastructure.web.dto.TransferenciaRequest;
import com.nunesd66.digitalbank.infrastructure.web.dto.TransferenciaResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SaveTransferenciaUseCase {

    private final TransferenciaMapper mapper;
    private final TransferenciaRepository transferenciaRepository;
    private final ContaRepository contaRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public TransferenciaResponse execute(TransferenciaRequest request) {
        Conta remetente = contaRepository.findByIdForUpdate(request.idRemetente());
        Conta destinatario = contaRepository.findByIdForUpdate(request.idDestinatario());

        Transferencia transferencia = Transferencia.createNew(remetente, destinatario, request.valor());

        contaRepository.save(remetente);
        contaRepository.save(destinatario);
        transferencia = transferenciaRepository.save(transferencia);

        eventPublisher.publishEvent(new TransferenciaConcluidaEvent(
                request.idRemetente(),
                request.idDestinatario(),
                request.valor()
        ));

        return mapper.toResponse(transferencia);
    }

}
