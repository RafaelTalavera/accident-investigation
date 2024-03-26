package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.*;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.enums.*;

import java.time.LocalDate;



public record EventResponseDTO(
        String id,
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
    public EventResponseDTO(Event event){
        this(
                event.getId(),
                event.getTitle(),
                event.getDateEvent(),
                event.getSeverity(),
                event.getBodyPart(),
                event.getInjury(),
                event.getEntry(),
                event.getWorkOccasion(),
                event.getHoursWorked(),
                event.getAccidentHistory(),
                event.getAuthorization(),
                event.getAuthorizationWork(),
                event.getPts(),
                event.getPtsApplied(),
                event.getMachine(),
                event.getEnergy(),
                event.getLockedRequired(),
                event.getLockedUsed(),
                event.getFails(),
                event.getUserId(),
                event.getComment()
        );
    }
}


