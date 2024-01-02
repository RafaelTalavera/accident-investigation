package com.axiomasoluciones.accidentinvestigation.dto.util;

import com.axiomasoluciones.accidentinvestigation.models.entity.util.BodyPart;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.IncidentType;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Injury;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Severity;

import java.time.LocalDateTime;
import java.util.List;

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
        Long workerId,
        Long  workPlaceId,
        Long organizacionalId,
        Long workEquipementId

) {
}
