package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.*;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.BodyPart;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.IncidentType;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Injury;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Severity;

import java.time.LocalDateTime;


public record EventResponseDTO(
        String id,
        LocalDateTime dateEvent,
        String description,
        Severity severity,
        Severity poSeverity,
        BodyPart bodyPart,
        Injury injury,
        IncidentType incidenType,
        String imagen,
        String aditionalImagen,
        Worker worker,
        WorkPlace workPlace,
        Organizational organizacional,
        WorkEquipment workEquipement
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
                event.getWorker(),
                event.getWorkPlace(),
                event.getOrganizationals(),
                event.getWorkEquipment()

        );
    }
}


