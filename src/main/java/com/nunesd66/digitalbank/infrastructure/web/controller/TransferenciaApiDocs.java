package com.nunesd66.digitalbank.infrastructure.web.controller;

import com.nunesd66.digitalbank.infrastructure.web.dto.TransferenciaRequest;
import com.nunesd66.digitalbank.infrastructure.web.dto.TransferenciaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Transferências", description = "Endpoints para gestão de transferências bancárias")
public interface TransferenciaApiDocs {

    @Operation(
            summary = "Lista todas as transferências",
            description = "Retorna o histórico completo de movimentações"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista retornada com sucesso",
            content = @Content(schema = @Schema(implementation = TransferenciaResponse.class))
    )
    ResponseEntity<List<TransferenciaResponse>> findAll();

    @Operation(summary = "Realiza uma transferência")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Transferência realizada",
                    content = @Content(schema = @Schema(implementation = TransferenciaResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos ou saldo insuficiente",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Validação falhou: ...")
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Conta não encontrada",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Conta não encontrada: ...")
                    )
            )
    })
    ResponseEntity<TransferenciaResponse> save(@Valid @RequestBody TransferenciaRequest request);

}
