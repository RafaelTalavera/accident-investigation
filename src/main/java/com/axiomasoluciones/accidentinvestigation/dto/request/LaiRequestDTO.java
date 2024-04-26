package com.axiomasoluciones.accidentinvestigation.dto.request;

import java.time.LocalDate;

public record LaiRequestDTO(
        LocalDate date,
        String organizationId,
        String nameOrganization,
        String area,
        String tipo,
        String activity,
        String aspect,
        String impact,
        String temporality,
        String description,
        String cycle,
        int frequency,
        int damage,
        String stateHolder,
        String legislation,
        String meaningfulness,
        String typeOfControl,
        String descriptionOfControl,
        LocalDate dateOfRevision,
        String userId
) {
}
