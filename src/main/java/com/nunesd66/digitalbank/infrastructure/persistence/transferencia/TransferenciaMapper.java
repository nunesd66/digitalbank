package com.nunesd66.digitalbank.infrastructure.persistence.transferencia;

import com.nunesd66.digitalbank.domain.model.Transferencia;
import com.nunesd66.digitalbank.infrastructure.web.dto.TransferenciaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransferenciaMapper {
    TransferenciaEntity toEntity(Transferencia transferencia);
    Transferencia toDomain(TransferenciaEntity entity);
    List<Transferencia> toListDomain(List<TransferenciaEntity> entities);
    List<TransferenciaResponse> toListResponse(List<Transferencia> transferencias);

    @Mapping(target = "nomeRemetente", source = "remetente.titular")
    @Mapping(target = "nomeDestinatario", source = "destinatario.titular")
    TransferenciaResponse toResponse(Transferencia transferencia);
}
