package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.*;
import java.time.LocalDate;

public record EventRequestDTO(
        LocalDate dateEvent,
        Severity severity,
        BodyPart bodyPart,
        Injury injury,
        IncidentType incidenType,
        LocalDate entry,
        WorkOccasion workOccasion,
        HoursWorked hoursWorked,
        LocalDate trainingDate,
        Boolean accidentHistory,
        Boolean authorization,
        Boolean authorizationWork,
        Boolean pts,
        Boolean ptsApplied,

        Boolean machine,
        Energy energy,
        Boolean lockedIn,
        Boolean lockedRequired,
        Boolean lockedUsed,
        Boolean defense,
        Boolean defenseIntegrity,
        Boolean workEquipmentFails,
        Boolean correctUseEquimant,

        String userId
) {
}
