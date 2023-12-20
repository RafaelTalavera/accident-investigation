package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.Investigation;
import com.axiomasoluciones.accidentinvestigation.models.entity.WorkPlace;
import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;

import java.util.List;

public record WorkPlaceResponseDTO(
        Long id,
        String name,
        String setor,
        List<Worker> workers,
        List<Investigation> investigations
) {
    public WorkPlaceResponseDTO(WorkPlace workPlace){
        this(workPlace.getId(), workPlace.getName(), workPlace.getSector(), workPlace.getWorkers(),
                workPlace.getInvestigations());
    }
}
