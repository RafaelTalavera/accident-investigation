package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.Organizational;
import com.axiomasoluciones.accidentinvestigation.models.entity.WorkEquipment;
import com.axiomasoluciones.accidentinvestigation.models.entity.WorkPlace;
import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.BodyPart;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.IncidentType;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Injury;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Severity;

import java.time.LocalDateTime;


public record EventRequestDTO(
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
}
