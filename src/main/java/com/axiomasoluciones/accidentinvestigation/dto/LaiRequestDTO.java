package com.axiomasoluciones.accidentinvestigation.dto;

import java.time.LocalDate;

public record LaiRequestDTO(
        LocalDate date,
       // String organization,
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
        String dateOfRevision,
        String userId
) {
}
