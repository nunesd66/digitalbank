package com.nunesd66.digitalbank.infrastructure.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferenciaResponse(

        @Schema(example = "1", description = "ID único do registro da transferência")
        Long id,

        @Schema(example = "Joaquim Silva", description = "Nome do remetente da transferência")
        String nomeRemetente,

        @Schema(example = "Joaquim Araújo", description = "Nome do destinatário da transferência")
        String nomeDestinatario,

        @Schema(example = "450.00", description = "Valor da transferência")
        BigDecimal valor,

        @Schema(example = "2026-06-08T11:30:02.881559553", description = "Data da transferência")
        LocalDateTime dataTransferencia
) {}
