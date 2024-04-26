package com.axiomasoluciones.accidentinvestigation.dto.response;

import com.axiomasoluciones.accidentinvestigation.models.entity.Consumo;

import java.time.LocalDate;

public record ConsumoResponseDTO(
        String Id,
        LocalDate date,
        String organizationId,
        String nameOrganization,
        String fuente,
        String tipoFuente,
        String combustible,
        String unidad,
        Double consumo,
        String userId

) {
    public ConsumoResponseDTO(Consumo consumo) {
        this(consumo.getId(),
                consumo.getDate(),
                consumo.getOrganizationId(),
                consumo.getNameOrganization(),
                consumo.getFuente(),
                consumo.getTipoFuente(),
                consumo.getCombustible(),
                consumo.getUnidad(),
                consumo.getConsumo(),
                consumo.getUserId());

    }
}
