package com.axiomasoluciones.accidentinvestigation.dto.util;

import com.axiomasoluciones.accidentinvestigation.models.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record WorkPlaceResponseDTO(
        Long id,
        String name,
        String sector,
        String adverseWeatherDetalis,
        Boolean adverseWeather,
        String lightingDetails,
        Boolean lighting,
        LocalDate inspectionDate,
        Boolean inspection

) {
    public WorkPlaceResponseDTO(WorkPlace workPlace){
        this(workPlace.getId(),
                workPlace.getName(),
                workPlace.getSector(),
                workPlace.getAdverseWeatherDetalis(),
                workPlace.getAdverseWeather(),
                workPlace.getLightingDetails(),
                workPlace.getLighting(),
                workPlace.getInspectionDate(),
                workPlace.getInspection());

    }
}
