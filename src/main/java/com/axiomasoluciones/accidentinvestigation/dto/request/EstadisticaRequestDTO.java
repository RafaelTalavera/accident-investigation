package com.axiomasoluciones.accidentinvestigation.dto.request;

public record EstadisticaRequestDTO(
        String organizationId,
        String nameOrganization,
        int month,
        int year,
        int people,
        int hours,
        String userId
) {

}
