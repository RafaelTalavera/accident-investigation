package com.axiomasoluciones.accidentinvestigation.dto.response;

import com.axiomasoluciones.accidentinvestigation.models.entity.Extinguisher;

import java.time.LocalDate;

public record ExtinguisherResponseDTO(
        String id,
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
){
    public ExtinguisherResponseDTO(Extinguisher extinguisher){
        this(extinguisher.getId(),
                extinguisher.getDate(),
                extinguisher.getOrganizationId(),
                extinguisher.getNameOrganization(),
                extinguisher.getSector(),
                extinguisher.getExtId(),
                extinguisher.getTipo(),
                extinguisher.getKg(),
                extinguisher.getVencimiento(),
                extinguisher.getAccess(),
                extinguisher.getSignaling(),
                extinguisher.getPresion(),
                extinguisher.getObservaciones(),
                extinguisher.getEnabled(),
                extinguisher.getUserId()

        );
    }
    public ExtinguisherResponseDTO(String errorMessage) {
        this(null,null, null, null, null, null, null, null, null, null, null, null, errorMessage, null, null);
    }
}
