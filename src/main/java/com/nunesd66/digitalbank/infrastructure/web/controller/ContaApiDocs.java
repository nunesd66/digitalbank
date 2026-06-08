package com.nunesd66.digitalbank.infrastructure.web.controller;

import com.nunesd66.digitalbank.infrastructure.web.dto.ContaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Contas", description = "Consulta de contas bancárias")
public interface ContaApiDocs {

    @Operation(summary = "Lista todas as contas")
    @ApiResponse(
            responseCode = "200",
            description = "Contas retornadas com sucesso",
            content = @Content(schema = @Schema(implementation = ContaResponse.class))
    )
    ResponseEntity<List<ContaResponse>> getAll();

}
