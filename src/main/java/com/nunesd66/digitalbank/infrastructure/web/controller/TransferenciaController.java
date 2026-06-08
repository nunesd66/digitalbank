package com.nunesd66.digitalbank.infrastructure.web.controller;

import com.nunesd66.digitalbank.application.FindAllTransferenciaUseCase;
import com.nunesd66.digitalbank.application.SaveTransferenciaUseCase;
import com.nunesd66.digitalbank.infrastructure.web.dto.TransferenciaRequest;
import com.nunesd66.digitalbank.infrastructure.web.dto.TransferenciaResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/transferencia")
@AllArgsConstructor
public class TransferenciaController implements TransferenciaApiDocs {

    private final FindAllTransferenciaUseCase findAllTransferenciaUseCase;
    private final SaveTransferenciaUseCase saveTransferenciaUseCase;

    @GetMapping
    public ResponseEntity<List<TransferenciaResponse>> findAll() {
        List<TransferenciaResponse> response = findAllTransferenciaUseCase.execute();
        return ResponseEntity.status(OK).body(response);
    }

    @PostMapping
    public ResponseEntity<TransferenciaResponse> save(@Valid @RequestBody TransferenciaRequest request) {
        TransferenciaResponse response = saveTransferenciaUseCase.execute(request);
        return ResponseEntity.status(CREATED).body(response);
    }

}
