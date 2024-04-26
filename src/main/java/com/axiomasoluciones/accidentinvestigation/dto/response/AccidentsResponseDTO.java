package com.axiomasoluciones.accidentinvestigation.dto.response;

import com.axiomasoluciones.accidentinvestigation.models.entity.Accidents;

import java.time.LocalDate;

public record AccidentsResponseDTO(
        String id,
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
        String userId,
        long lostDay) {
    public AccidentsResponseDTO(Accidents accidents, long lostDay){
        this(accidents.getId(),
                accidents.getOrganizationId(),
                accidents.getNameOrganization(),
                accidents.getPuesto(),
                accidents.getArea(),
                accidents.getName(),
                accidents.getSeverity(),
                accidents.getDateEvent(),
                accidents.getDateAlta(),
                accidents.isAlta(),
                accidents.getMonthEvent(),
                accidents.getYearEvent(),
                accidents.getUserId(),
                lostDay
        );
    }


    public AccidentsResponseDTO(Accidents accidents){
        this(accidents, 0);
    }


}
