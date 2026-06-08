package com.nunesd66.digitalbank.domain.model;

import com.nunesd66.digitalbank.domain.exception.ContaSaldoInsuficienteException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conta {

    private Long id;
    private String titular;
    private BigDecimal saldo;

    public void depositar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    public void sacar(BigDecimal valor) {
        if (saldo.compareTo(valor) < 0) {
            throw new ContaSaldoInsuficienteException("Saldo insuficiente.");
        }

        saldo = saldo.subtract(valor);
    }
}
