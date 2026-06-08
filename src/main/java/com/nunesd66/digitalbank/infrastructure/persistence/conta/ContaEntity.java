package com.nunesd66.digitalbank.infrastructure.persistence.conta;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "CONTA")
@NoArgsConstructor
public class ContaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITULAR", length = 100, nullable = false)
    private String titular;

    @Column(name = "SALDO", precision = 19, scale = 2, nullable = false)
    private BigDecimal saldo;


}
