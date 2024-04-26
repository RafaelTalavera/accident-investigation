package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;

import java.util.List;
import java.util.Optional;

public interface IEventService {

    public List<Event> findAll();

    Optional<Event> findById(String id);

    Event save(Event event);

    public void deleteById(String id);

    Event editEvent(String id, Event editedEvent);

    void delete(Event event);

    String extractUserEmailFromToken(String token);

    public List<Event> findByUserId(String userId);

    public String getCausa(String id);


}


