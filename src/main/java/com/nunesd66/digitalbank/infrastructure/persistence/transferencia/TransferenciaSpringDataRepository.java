package com.nunesd66.digitalbank.infrastructure.persistence.transferencia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaSpringDataRepository extends JpaRepository<TransferenciaEntity, Long> {
}
