package com.axiomasoluciones.accidentinvestigation.dto.util;

import com.axiomasoluciones.accidentinvestigation.models.entity.Organizational;


public record OrganizationalResponseDTO(
                Long id,
                String eppDetails,
                Boolean eppDesignated,
                Boolean eppUsed,
                String authorizationDetails,
                Boolean authorizationWork,
                String riskDetails,
                Boolean risk,

                String ptsDetails,
                Boolean pts,
                Boolean ptsApplied,
                String changeDetails,
                Boolean change,
                String blockingDetails,
                Boolean blocking,
                String expectedBehaviorDetails,
                Boolean expectedBehavior


) {
    public OrganizationalResponseDTO(Organizational organizational) {
        this(organizational.getId(),
                organizational.getEppDetails(),
                organizational.getEppDesignated(),
                organizational.getEppUsed(),
                organizational.getAuthorizationDetails(),
                organizational.getAuthorizationWork(),
                organizational.getRiskDetails(),
                organizational.getRisk(),
                organizational.getPtsDetails(),
                organizational.getPts(),
                organizational.getPtsApplied(),
                organizational.getChangeDetails(),
                organizational.getChange(),
                organizational.getBlockingDetails(),
                organizational.getBlocking(),
                organizational.getExpectedBehaviorDetails(),
                organizational.getExpectedBehavior());

    }
}





