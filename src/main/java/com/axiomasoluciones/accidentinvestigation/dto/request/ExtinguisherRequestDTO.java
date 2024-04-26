package com.axiomasoluciones.accidentinvestigation.dto.request;

import java.time.LocalDate;

public record ExtinguisherRequestDTO(
        LocalDate date,
        String organizationId,
        String nameOrganization,
        String sector,
        String extId,
        String tipo,
        Double kg,
        LocalDate vencimiento,
        Boolean access,
        Boolean signaling,
        Boolean presion,
        String observaciones,
        Boolean enabled,
        String userId
) {
}
