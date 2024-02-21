package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IEventDao;
import com.axiomasoluciones.accidentinvestigation.models.entity.Event;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImplements implements IEventService {

    @Autowired
    private IEventDao eventDao;

    @Autowired
    private CausasHandler causasHandler;

    @Autowired
    private AuthenticationService authenticationService;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;


    @Override
    @Transactional(readOnly = true)
    public List<Event> findAll() {
        return (List<Event>) eventDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Event> findById(String id) {
        return eventDao.findById(id);
    }

    @Override
    @Transactional
    public Event save(Event event) {
        return eventDao.save(event);
    }

    @Override
    @Transactional
    public String extractUserEmailFromToken(String token) {
            try {
                // Remover la palabra "Bearer " del inicio del token
                String jwtToken = token.replace("Bearer ", "");
                Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken).getBody();
                return claims.get("mail", String.class);
            } catch (Exception e) {
                throw new RuntimeException("Error al extraer el correo electrónico del token", e);
            }
    }

    @Override
    public List<Event> findByUserId(String userId) {
        return eventDao.findEventByUserId(userId);
    }

    @Override
    public String getCausa(String id) {
        Optional<Event> eventOptional = eventDao.findById(id);

        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            return causasHandler.evaluarCausas(event);
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id);
        }
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        Event existingEvent = eventDao.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));

        eventDao.deleteById(id);
    }

    @Override
    public Event editEvent(String id, Event editedEvent) {
        Event existEvent = eventDao.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));

        existEvent.setComment(editedEvent.getComment());

        return eventDao.save(existEvent);
    }

    @Override
    public void delete(Event event) {
        eventDao.delete(event);
    }
}

