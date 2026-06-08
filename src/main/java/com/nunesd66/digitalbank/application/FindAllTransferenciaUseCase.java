package com.nunesd66.digitalbank.application;

import com.nunesd66.digitalbank.domain.model.Transferencia;
import com.nunesd66.digitalbank.domain.repository.TransferenciaRepository;
import com.nunesd66.digitalbank.infrastructure.persistence.transferencia.TransferenciaMapper;
import com.nunesd66.digitalbank.infrastructure.web.dto.TransferenciaResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindAllTransferenciaUseCase {

    private final TransferenciaRepository repository;
    private final TransferenciaMapper mapper;

    public List<TransferenciaResponse> execute() {
        List<Transferencia> transferencias = repository.findAll();
        return mapper.toListResponse(transferencias);
    }

}
