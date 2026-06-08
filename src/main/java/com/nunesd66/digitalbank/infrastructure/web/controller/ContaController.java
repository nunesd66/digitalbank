package com.nunesd66.digitalbank.infrastructure.web.controller;

import com.nunesd66.digitalbank.application.FindAllContasUseCase;
import com.nunesd66.digitalbank.infrastructure.web.dto.ContaResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/conta")
@AllArgsConstructor
public class ContaController implements ContaApiDocs {

    private final FindAllContasUseCase findAllContasUseCase;

    @GetMapping
    public ResponseEntity<List<ContaResponse>> getAll() {
        List<ContaResponse> response = findAllContasUseCase.execute();
        return ResponseEntity.status(OK).body(response);
    }

}
