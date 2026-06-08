package com.nunesd66.digitalbank.infrastructure.persistence.transferencia;

import com.nunesd66.digitalbank.domain.model.Transferencia;
import com.nunesd66.digitalbank.domain.repository.TransferenciaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TransferenciaRepositoryJpa  implements TransferenciaRepository {

    private final TransferenciaSpringDataRepository repository;
    private final TransferenciaMapper mapper;

    @Override
    public Transferencia save(Transferencia transferencia) {
        TransferenciaEntity entity = mapper.toEntity(transferencia);
        entity = repository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public List<Transferencia> findAll() {
        List<TransferenciaEntity> entities = repository.findAll();
        return mapper.toListDomain(entities);
    }
}
