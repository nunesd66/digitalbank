package com.nunesd66.digitalbank.infrastructure.persistence.transferencia;

import com.nunesd66.digitalbank.infrastructure.persistence.conta.ContaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSFERENCIA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_REMETENTE", updatable = false)
    private ContaEntity remetente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DESTINATARIO", updatable = false)
    private ContaEntity destinatario;

    @Column(name = "VALOR", precision = 19, scale = 2, nullable = false, updatable = false)
    private BigDecimal valor;

    @Column(name = "DATA_TRANSFERENCIA", updatable = false)
    private LocalDateTime dataTransferencia;

    @PrePersist
    protected void onCreate() {
        this.dataTransferencia = LocalDateTime.now();
    }
}
