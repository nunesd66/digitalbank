package com.nunesd66.digitalbank.infrastructure.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransferenciaRequest(

        @Schema(example = "1", description = "ID único do remetente da transferência")
        @NotNull(message = "ID do remetente é obrigatório")
        Long idRemetente,

        @Schema(example = "2", description = "ID único do destinatario da transferência")
        @NotNull(message = "ID do destinatário é obrigatório")
        Long idDestinatario,

        @Schema(example = "350.00", description = "valor da transferência")
        @NotNull(message = "Valor é obrigatório")
        @Positive(message = "Valor deve ser maior que zero")
        BigDecimal valor
) {}
