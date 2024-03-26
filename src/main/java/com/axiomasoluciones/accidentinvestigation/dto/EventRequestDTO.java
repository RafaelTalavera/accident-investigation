package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.util.enums.*;
import java.time.LocalDate;

public record EventRequestDTO(
        String title,
        LocalDate dateEvent,
        String severity,
        String bodyPart,
        String injury,
        Boolean entry,
        Boolean workOccasion,
        Boolean hoursWorked,
        Boolean accidentHistory,
        Boolean authorization,
        Boolean authorizationWork,
        Boolean pts,
        Boolean ptsApplied,
        Boolean machine,
        String energy,
        Boolean lockedRequired,
        Boolean lockedUsed,
        Boolean fails,
        String userId,
        String comment
) {
}
