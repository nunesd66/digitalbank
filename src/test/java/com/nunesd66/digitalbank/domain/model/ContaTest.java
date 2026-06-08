package com.nunesd66.digitalbank.domain.model;

import com.nunesd66.digitalbank.domain.exception.ContaSaldoInsuficienteException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContaTest {

    @Test
    @DisplayName("Deve adicionar o valor do saldo quando o depósito for válido")
    void deveDepositarValor() {
        Conta conta = new Conta(1L, "Daniel", new BigDecimal("10"));
        conta.depositar(BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(110), conta.getSaldo());
    }

    @Test@DisplayName("Deve subtrair o valor do saldo quando o saque for válido")
    void deveSacarValor() {
        Conta conta = new Conta(1L, "Daniel", new BigDecimal("25"));
        conta.sacar(new BigDecimal("25"));
        assertEquals(BigDecimal.ZERO, conta.getSaldo());
    }

    @Test
    @DisplayName("Deve lançar exceção quando tentar sacar valor maior que o saldo")
    void deveLancarExcecaoQuandoSaldoInsuficiente() {
        Conta conta = new Conta(1L, "Daniel", new BigDecimal("20"));

        assertThrows(
                ContaSaldoInsuficienteException.class,
                () -> conta.sacar(BigDecimal.valueOf(50))
        );
    }

}
