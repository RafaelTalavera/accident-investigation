package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.models.entity.WorkPlace;
import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Energy;

public record InvestigationRequestDTO(
        String climate,
        String workEquipment,
        Energy energy,
        String workOccasionDetails,
        Boolean workOccasion,
        String authorizationDetails,
        Boolean authorizationWork,
        String riskDetails,
        Boolean risk,
        String ptsDetails,
        Boolean pts,
        String trainingDetails,
        Boolean training,
        String expectedBehaviorDetails,
        Boolean expectedBehavior,
        String defenseFailedDetails,
        Boolean defenseFailed,
        Long workPlaceId,
        Long workerId,
        Long eventId
) {
}
