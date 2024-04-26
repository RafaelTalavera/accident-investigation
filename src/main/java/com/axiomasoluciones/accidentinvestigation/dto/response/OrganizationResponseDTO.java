package com.axiomasoluciones.accidentinvestigation.dto.response;

import com.axiomasoluciones.accidentinvestigation.models.entity.Organization;



public record OrganizationResponseDTO(
        String id,
        String name,
        String tipoOrga,
        String sector,
        Integer nEmpleados,
        Double superficie,
        String userId
        ) {
    public OrganizationResponseDTO(Organization organization){
        this(organization.getId(),
                organization.getName(),
                organization.getTipoOrga(),
                organization.getSector(),
                organization.getNEmpleados(),
                organization.getSuperficie(),
                organization.getUserId()
                );
    }

}
