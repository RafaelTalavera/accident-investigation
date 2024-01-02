package com.axiomasoluciones.accidentinvestigation.dto.util;


public record OrganizationalRequestDTO(
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
        Boolean expectedBehavior,
        Long workPlaceId,
        Long workerId,
        Long eventId
) {
}
