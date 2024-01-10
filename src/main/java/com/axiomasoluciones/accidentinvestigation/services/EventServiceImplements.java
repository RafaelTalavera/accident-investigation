package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IEventDao;
import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.services.logica.Case1;
import com.axiomasoluciones.accidentinvestigation.services.logica.Case2;
import com.axiomasoluciones.accidentinvestigation.services.logica.Case3;
import com.axiomasoluciones.accidentinvestigation.services.logica.Case4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    public void deleteById(String id) {
        Event existingEvent = eventDao.findById(id)
                        .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));

        eventDao.deleteById(id);
    }

    @Override
    public Event editEvent(String id, Event editedEvent) {
        Event existEvent = eventDao.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));

        existEvent.setDateEvent(editedEvent.getDateEvent());
        existEvent.setDescription(editedEvent.getDescription());
        existEvent.setSeverity(editedEvent.getSeverity());
        existEvent.setPoSeverity(editedEvent.getPoSeverity());
        existEvent.setImagen(editedEvent.getImagen());
        existEvent.setAditionalImagen(editedEvent.getAditionalImagen());

        return eventDao.save(existEvent);
    }

    @Override
    public void delete(Event event) {
        eventDao.delete(event);}


    @Override
    @Transactional(readOnly = true)
    public String getAntiguedadMessageById(String id) {
        Event event = eventDao.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));

        return getAntiguedadMessage(event);
    }

    @Override
    @Transactional(readOnly = true)
    public String getCase1ById(String id) {
        Event event = eventDao.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));
        return getCase1Message(event);
    }

    private String getCase1Message(Event event) {
        if (event.getWorker() != null && event.getWorker().getEntry() != null) {
            LocalDate entryDate = event.getWorker().getEntry();
            LocalDate currentDate = event.getDateEvent();

            long monthsDifferenceEntry = ChronoUnit.MONTHS.between(entryDate, currentDate);


            Case1 caseInstance1 = new Case1();
            Case2 caseInstance2 = new Case2();
            Case3 caseInstance3 = new Case3();
            Case4 caseInstance4 = new Case4();

            String case1 = caseInstance1.case1(event);
            String case2 = caseInstance2.case2(event);
            String case3 = caseInstance3.case3(event);
            String case4 = caseInstance4.case4(event);



            // Llamar a los métodos específicos y obtener los resultados

            if (!case1.equals("case1")) {
                return case1;
            }

            if (!case2.equals("case2")) {
                return case2;
            }

            if (!case3.equals("case3")) {
                return case3;
            }

            if (!case4.equals("case4")) {
                return case4;
            }
            
            return "No se cumplió";
        } else {
            return "No ingresó antigüedad para este worker";
        }
    }






    private String getAntiguedadMessage(Event event) {
        if (event.getWorker() != null && event.getWorker().getEntry() != null) {
            LocalDate entryDate = event.getWorker().getEntry();
            LocalDate currentDate = event.getDateEvent();

            long monthsDifference = entryDate.until(currentDate).toTotalMonths();

            if (monthsDifference < 3) {
                return "Antigüedad menor a tres meses";
            } else {
                return "Antigüedad mayor a tres meses";
            }
        } else {
            return "No ingresó antigüedad para este worker";
        }
    }

    public String getAntiguedadMessageForEvent(Event event) {
        return getAntiguedadMessage(event);
    }

}
