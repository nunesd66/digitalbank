package com.nunesd66.digitalbank.infrastructure.persistence.conta;

import com.nunesd66.digitalbank.domain.model.Conta;
import com.nunesd66.digitalbank.infrastructure.web.dto.ContaResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContaMapper {
    List<ContaResponse> toListResponse(List<Conta> contas);
    Conta toDomain(ContaEntity contaEntity);
    List<Conta> toListDomain(List<ContaEntity> entities);
    ContaEntity toEntity(Conta conta);
}
