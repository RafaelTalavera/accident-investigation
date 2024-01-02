package com.axiomasoluciones.accidentinvestigation.dto.util;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.BodyPart;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.IncidentType;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Injury;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Severity;

import java.time.LocalDateTime;

public record EventResponseDTO(
        Long id,
        LocalDateTime dateEvent,
        String description,
        Severity severity,
        Severity poSeverity,
        BodyPart bodyPart,
        Injury injury,
        IncidentType incidenType,
        String imagen,
        String aditionalImagen,
        Long workerId,
        Long  workPlaceId,
        Long organizacionalId,
        Long workEquipementId
) {
    public EventResponseDTO(Event event){
        this(
                event.getId(),
                event.getDateEvent(),
                event.getDescription(),
                event.getSeverity(),
                event.getPoSeverity(),
                event.getBodyPart(),
                event.getInjury(),
                event.getIncidenType(),
                event.getImagen(),
                event.getAditionalImagen(),
                event.getWorker().getId(),
                event.getWorkPlace().getId(),
                event.getOrganizationals().getId(),
                event.getWorkEquipment().getId()

        );
    }
}


