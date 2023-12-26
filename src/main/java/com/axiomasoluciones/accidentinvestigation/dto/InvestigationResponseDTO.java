package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.models.entity.Investigation;
import com.axiomasoluciones.accidentinvestigation.models.entity.WorkPlace;
import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;


public record InvestigationResponseDTO(
                Long id,
                String climate,
                String workEquipment,
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
    public InvestigationResponseDTO(Investigation investigation) {
        this(investigation.getId(),
                investigation.getClimate(),
                investigation.getWorkEquipment(),
                investigation.getWorkOccasionDetails(),
                investigation.getWorkOccasion(),
                investigation.getAuthorizationDetails(),
                investigation.getAuthorizationWork(),
                investigation.getRiskDetails(),
                investigation.getRisk(),
                investigation.getPtsDetails(),
                investigation.getPts(),
                investigation.getTrainingDetails(),
                investigation.getTraining(),
                investigation.getExpectedBehaviorDetails(),
                investigation.getExpectedBehavior(),
                investigation.getDefenseFailedDetails(),
                investigation.getDefenseFailed(),
                investigation.getWorkPlace().getId(),
                investigation.getWorker().getId(),
                investigation.getEvent().getId());

    }

}
