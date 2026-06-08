package com.nunesd66.digitalbank.domain.event;

import java.math.BigDecimal;

public record TransferenciaConcluidaEvent (
    Long idRemetente,
    Long idDestinatario,
    BigDecimal valor
){}
