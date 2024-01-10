package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IEventDao;
import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.services.logica.tensionFisica.*;
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


            Case1AntiMeSeisTenFiBaja caseInstance1 = new Case1AntiMeSeisTenFiBaja();
            Case2AntiMaSeisTenFiBaja caseInstance2 = new Case2AntiMaSeisTenFiBaja();
            Case3AntiMaSeisTenFiBajaMaOchoTrabajadas caseInstance3 = new Case3AntiMaSeisTenFiBajaMaOchoTrabajadas();
            Case4AntiMaSeisTenFiMediaMaOchoTraFuerMedia caseInstance4 = new Case4AntiMaSeisTenFiMediaMaOchoTraFuerMedia();
            Case5AntiMaSeisTenFiMediaOchoTraFuerzaMeMovilidadMe caseInstance5 = new Case5AntiMaSeisTenFiMediaOchoTraFuerzaMeMovilidadMe();
            Case6AntiMaSeisTenFiMediaOchoTraFuerzaAltaMovilidadAlta caseInstance6 = new Case6AntiMaSeisTenFiMediaOchoTraFuerzaAltaMovilidadAlta();

            String case1 = caseInstance1.case1(event);
            String case2 = caseInstance2.case2(event);
            String case3 = caseInstance3.case3(event);
            String case4 = caseInstance4.case4(event);
            String case5 = caseInstance5.case5(event);
            String case6 = caseInstance6.case6(event);


            

            if (!case1.equals("case1")) {return case1;}
            if (!case2.equals("case2")) {return case2;}
            if (!case3.equals("case3")) {return case3;}
            if (!case4.equals("case4")) {return case4;}
            if (!case5.equals("case5")) {return case5;}
            if (!case6.equals("case6")) {return case6;}
            
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
