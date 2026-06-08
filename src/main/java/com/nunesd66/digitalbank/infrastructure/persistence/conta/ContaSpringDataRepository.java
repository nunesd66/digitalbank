package com.nunesd66.digitalbank.infrastructure.persistence.conta;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContaSpringDataRepository extends JpaRepository<ContaEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from ContaEntity c where c.id = :id")
    Optional<ContaEntity> findByIdForUpdate(Long id);

}
