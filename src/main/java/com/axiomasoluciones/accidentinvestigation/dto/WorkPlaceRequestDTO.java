package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.Investigation;
import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;

import java.util.List;

public record WorkPlaceRequestDTO(
        String name,
        String sector,
        Long workersId,
        Long ivestigationsId
) {
}
