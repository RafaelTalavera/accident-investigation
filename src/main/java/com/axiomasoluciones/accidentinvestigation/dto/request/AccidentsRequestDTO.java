package com.axiomasoluciones.accidentinvestigation.dto.request;

import java.time.LocalDate;

public record AccidentsRequestDTO(

        String organizationId,
        String nameOrganization,
        String puesto,
        String area,
        String name,
        String severity,
        LocalDate dateEvent,
        LocalDate dateAlta,
        boolean alta,
        int monthEvent,
        int yearEvent,
        String userId
) {
}
