package com.nunesd66.digitalbank.domain.model;

import com.nunesd66.digitalbank.domain.exception.ContaOrigemDestinoIguaisException;
import com.nunesd66.digitalbank.domain.exception.ContaSaldoInsuficienteException;
import com.nunesd66.digitalbank.domain.exception.ValorTransferenciaInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransferenciaTest {

    private Conta remetente;
    private Conta destinatario;

    @BeforeEach
    void before() {
        remetente = new Conta(1L, "Ana", new BigDecimal("500.00"));
        destinatario = new Conta(2L, "Bob", new BigDecimal("100.00"));
    }

    @Test
    @DisplayName("Deve transferir valor corretamente")
    void deveTransferirValor() {
        Transferencia t = Transferencia.createNew(remetente, destinatario, new BigDecimal("200.00"));
        assertEquals(new BigDecimal("300.00"), remetente.getSaldo());
        assertEquals(new BigDecimal("300.00"), destinatario.getSaldo());
        assertEquals(new BigDecimal("200.00"), t.getValor());
    }

    @Test
    @DisplayName("Deve lançar exceção quando tentar transferir para mesma conta de origem")
    void deveLancarExcecaoQuandoMesmaContaOrigem() {
        assertThrows(
                ContaOrigemDestinoIguaisException.class,
                () -> Transferencia.createNew(remetente, remetente, new BigDecimal("100.00")));
    }

    @Test
    @DisplayName("Deve lançar exceção quando tentar transferir valor zerado")
    void deveLancarExcecaoQuandoValorZero() {
        assertThrows(
                ValorTransferenciaInvalidoException.class,
                () -> Transferencia.createNew(remetente, destinatario, BigDecimal.ZERO));
    }

    @Test
    @DisplayName("Deve lançar exceção quando tentar transferir valor negativo")
    void deveLancarExcecaoQuandoValorNegativo() {
        assertThrows(
                ValorTransferenciaInvalidoException.class,
                () -> Transferencia.createNew(remetente, destinatario, new BigDecimal("-1.00")));
    }

    @Test
    @DisplayName("Deve lançar exceção quando tentar transferir valor insuficiente")
    void deveLancarExcecaoQuandoValorInsuficiente() {
        assertThrows(
                ContaSaldoInsuficienteException.class,
                () -> Transferencia.createNew(remetente, destinatario, new BigDecimal("9999.00")));
    }

}
