package com.nunesd66.digitalbank.application;

import com.nunesd66.digitalbank.domain.model.Conta;
import com.nunesd66.digitalbank.domain.repository.ContaRepository;
import com.nunesd66.digitalbank.infrastructure.persistence.conta.ContaMapper;
import com.nunesd66.digitalbank.infrastructure.web.dto.ContaResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindAllContasUseCase {

    private final ContaRepository repository;
    private final ContaMapper mapper;

    public List<ContaResponse> execute() {
        List<Conta> contas = repository.findAll();
        return mapper.toListResponse(contas);
    }

}
