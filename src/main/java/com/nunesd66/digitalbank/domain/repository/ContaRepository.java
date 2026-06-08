package com.nunesd66.digitalbank.domain.repository;

import com.nunesd66.digitalbank.domain.model.Conta;

import java.util.List;

public interface ContaRepository {
    List<Conta> findAll();
    void save(Conta conta);
    Conta findByIdForUpdate(Long id);
}
