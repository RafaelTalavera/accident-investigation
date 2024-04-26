package com.axiomasoluciones.accidentinvestigation.dto.response;

import com.axiomasoluciones.accidentinvestigation.models.entity.Estadistica;

public record EstadisticaResponseDTO(
        String id,
        String organizationId,
        String nameOrganization,
        int month,
        int year,
        int people,
        int hours,
        String userId
) {
    public EstadisticaResponseDTO(Estadistica estadistica){
        this(estadistica.getId(),
                estadistica.getOrganizationId(),
                estadistica.getNameOrganization(),
                estadistica.getMonth(),
                estadistica.getYear(),
                estadistica.getPeople(),
                estadistica.getHours(),
                estadistica.getUserId());
    }
}
