package com.nunesd66.digitalbank.domain.repository;

import com.nunesd66.digitalbank.domain.model.Transferencia;

import java.util.List;

public interface TransferenciaRepository {
    Transferencia save(Transferencia transferencia);
    List<Transferencia> findAll();
}
