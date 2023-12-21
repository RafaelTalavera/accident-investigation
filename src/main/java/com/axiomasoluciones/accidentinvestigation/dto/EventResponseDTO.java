package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.models.entity.WorkPlace;
import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.BodyParts;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Injury;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Severity;

import java.time.LocalDateTime;
import java.util.List;

public record EventResponseDTO(
        Long id,
        LocalDateTime dateEvent,
        String description,
        Severity severity,
        Severity poSeverity,
        List<BodyParts> bodyPartsList,
        List<Injury> injuries,
        String imagen,
        String aditionalImagen,
        Long workerId,
        Long  workPlaceId
) {
    public EventResponseDTO(Event event){
        this(
                event.getId(),
                event.getDateEvent(),
                event.getDescription(),
                event.getSeverity(),
                event.getPoSeverity(),
                event.getBodyPartsList(),
                event.getInjuriesList(),
                event.getImagen(),
                event.getAditionalImagen(),  // Corregido el nombre del método
                event.getWorker().getId(),
                event.getWorkPlace().getId()  // Usar getWorkPlace en lugar de getWorker para el lugar de trabajo
        );
    }
}


