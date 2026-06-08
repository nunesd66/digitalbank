package com.nunesd66.digitalbank.domain.model;

import com.nunesd66.digitalbank.domain.exception.ContaOrigemDestinoIguaisException;
import com.nunesd66.digitalbank.domain.exception.ValorTransferenciaInvalidoException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transferencia {

    private Long id;
    private Conta remetente;
    private Conta destinatario;
    private BigDecimal valor;
    private LocalDateTime dataTransferencia;

    public static Transferencia createNew(
            Conta remetente,
            Conta destinatario,
            BigDecimal valor
    ) {
        if (remetente.getId().equals(destinatario.getId())) {
            throw new ContaOrigemDestinoIguaisException("A conta de origem não pode ser a mesma da conta de destino.");
        }

        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorTransferenciaInvalidoException("O valor deve ser maior que zero.");
        }

        remetente.sacar(valor);
        destinatario.depositar(valor);

        return new Transferencia(
                null,
                remetente,
                destinatario,
                valor,
                null
        );
    }

}
