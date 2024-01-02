package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;

import java.util.List;
import java.util.Optional;

public interface IEventService {

    public List<Event> findAll();

    Optional<Event> findById(Long id);

    Event save(Event event);

    public void deleteById(Long id);

    Event editEvent(Long id, Event editedEvent);

    void delete(Event event);
}
