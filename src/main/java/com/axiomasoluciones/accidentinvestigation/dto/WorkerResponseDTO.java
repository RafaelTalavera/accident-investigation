package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.models.entity.WorkPlace;
import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record WorkerResponseDTO(
        Long id,
        String fullName,
        LocalDate birth,
        String address,
        LocalDate entry,
        Long workPlaceId,
        List<Event> events
) {
    public WorkerResponseDTO(Worker worker){
        this(worker.getId(),
                worker.getFullName(),
                worker.getBirth(),
                worker.getAddress(),
                worker.getEntry(),
                worker.getWorkPlace().getId(),
                worker.getEvents());
    }
}
