package com.nunesd66.digitalbank.infrastructure.persistence.conta;

import com.nunesd66.digitalbank.domain.exception.ContaNaoEncontradaException;
import com.nunesd66.digitalbank.domain.model.Conta;
import com.nunesd66.digitalbank.domain.repository.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ContaRepositoryJpa implements ContaRepository {

    private final ContaSpringDataRepository repository;
    private final ContaMapper mapper;

    @Override
    public Conta findByIdForUpdate(Long id) {
        return repository
                .findByIdForUpdate(id)
                .map(mapper::toDomain)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta de ID " + id + " não encontrada."));
    }

    @Override
    public List<Conta> findAll() {
        List<ContaEntity> entities = repository.findAll();
        return mapper.toListDomain(entities);
    }

    @Override
    public void save(Conta conta) {
        ContaEntity entity = mapper.toEntity(conta);
        repository.save(entity);
    }
}
