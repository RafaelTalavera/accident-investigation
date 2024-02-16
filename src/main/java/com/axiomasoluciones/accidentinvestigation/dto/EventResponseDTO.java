package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.*;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.*;

import java.time.LocalDate;



public record EventResponseDTO(
        String id,
        LocalDate dateEvent,
        Severity severity,
        BodyPart bodyPart,
        Injury injury,
        Boolean entry,
        WorkOccasion workOccasion,
        HoursWorked hoursWorked,
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
        Boolean fails,

        String userId
) {
    public EventResponseDTO(Event event){
        this(
                event.getId(),
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
                event.getLockedIn(),
                event.getLockedRequired(),
                event.getLockedUsed(),
                event.getFails(),
                event.getUserId()
        );
    }
}


