package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.models.entity.WorkPlace;

import java.time.LocalDate;

import java.util.List;

public record WorkerRequestDTO(
 String fullName,
 LocalDate birth,
 String address,
 LocalDate entry,
 Long workPlaceId,
 List<Event> events

) {
}
