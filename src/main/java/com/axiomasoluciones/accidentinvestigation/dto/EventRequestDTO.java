package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.WorkPlace;
import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.BodyParts;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Injury;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Severity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record EventRequestDTO(
        LocalDateTime dateEvent,
        String description,
        Severity severity,
        Severity poSeverity,
        List<BodyParts> bodyPartsList,
        List<Injury> injuriesList,
        String imagen,
        String editionalImagen,
        Long workerId,
        Long  workPlaceId
) {
}
