package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.*;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.BodyPart;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.IncidentType;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.Injury;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.Severity;

import java.time.LocalDate;


public record EventRequestDTO(
        LocalDate dateEvent,
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
        Method method,
        Machine machine,
        Activity activity
) {
}
