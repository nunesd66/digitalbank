package com.nunesd66.digitalbank.infrastructure.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ContaResponse(

        @Schema(example = "1", description = "ID único do registro da conta")
        Long id,

        @Schema(example = "Joaquim Silva", description = "Nome do titular da conta")
        String titular,

        @Schema(example = "450.00", description = "Saldo da conta")
        BigDecimal saldo
) {}
