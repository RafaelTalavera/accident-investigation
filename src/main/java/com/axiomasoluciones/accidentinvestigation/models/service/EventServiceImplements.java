package com.axiomasoluciones.accidentinvestigation.models.service;

import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IEventDao;
import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImplements implements IEventService {

    @Autowired
    private IEventDao eventDao;

    @Override
    @Transactional(readOnly = true)
    public List<Event> findAll() {
        return (List<Event>) eventDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Event> findById(Long id) {
        return eventDao.findById(id);
    }

    @Override
    @Transactional
    public Event save(Event event) {
        return eventDao.save(event);
    }

    @Override
    @Transactional
    public void deleteByIdEvent(Long id) {
        Event existingEvent = eventDao.findById(id)
                        .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));

        eventDao.deleteById(id);
    }

    @Override
    public Event editEvent(Long id, Event editedEvent) {
        Event existEvent = eventDao.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));

        existEvent.setDateEvent(editedEvent.getDateEvent());
        existEvent.setDescription(editedEvent.getDescription());
        existEvent.setSeverity(editedEvent.getSeverity());
        existEvent.setPoSeverity(editedEvent.getPoSeverity());
        existEvent.setBodyPartsList(editedEvent.getBodyPartsList());
        existEvent.setInjuriesList(editedEvent.getInjuriesList());
        existEvent.setImagen(editedEvent.getImagen());
        existEvent.setAditionalImagen(editedEvent.getAditionalImagen());

        return eventDao.save(existEvent);
    }
}
