package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IEventDao;
import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.services.logica.*;

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
            Case5 caseInstance5 = new Case5();
            Case6 caseInstance6 = new Case6();
            Case7 caseInstance7 = new Case7();
            Case8 caseInstance8 = new Case8();
            Case9 caseInstance9 = new Case9();
            Case10 caseInstance10 = new Case10();
            Case11 caseInstance11 = new Case11();
            Case12 caseInstance12 = new Case12();
            Case13 caseInstance13 = new Case13();
            Case14 caseInstance14 = new Case14();
            Case15 caseInstance15 = new Case15();
            Case16 caseInstance16 = new Case16();
            Case17 caseInstance17 = new Case17();
            Case18 caseInstance18 = new Case18();
            Case19 caseInstance19 = new Case19();
            Case20 caseInstance20 = new Case20();






            String case1 = caseInstance1.case1(event);
            String case2 = caseInstance2.case2(event);
            String case3 = caseInstance3.case3(event);
            String case4 = caseInstance4.case4(event);
            String case5 = caseInstance5.case5(event);
            String case6 = caseInstance6.case6(event);
            String case7 = caseInstance7.case7(event);
            String case8 = caseInstance8.case8(event);
            String case9 = caseInstance9.case9(event);
            String case10 = caseInstance10.case10(event);
            String case11 = caseInstance11.case11(event);
            String case12 = caseInstance12.case12(event);
            String case13= caseInstance13.case13(event);
            String case14= caseInstance14.case14(event);
            String case15= caseInstance15.case15(event);
            String case16= caseInstance16.case16(event);
            String case17= caseInstance17.case17(event);
            String case18= caseInstance18.case18(event);
            String case19= caseInstance19.case19(event);
            String case20= caseInstance20.case20(event);




            if (!case1.equals("case1")) {return case1;}
            if (!case2.equals("case2")) {return case2;}
            if (!case3.equals("case3")) {return case3;}
            if (!case4.equals("case4")) {return case4;}
            if (!case5.equals("case5")) {return case5;}
            if (!case6.equals("case6")) {return case6;}
            if (!case7.equals("case7")) {return case7;}
            if (!case8.equals("case8")) {return case8;}
            if (!case9.equals("case9")) {return case9;}
            if (!case10.equals("case10")) {return case10;}
            if (!case11.equals("case11")) {return case11;}
            if (!case12.equals("case12")) {return case12;}
            if (!case13.equals("case13")) {return case13;}
            if (!case14.equals("case14")) {return case14;}
            if (!case15.equals("case15")) {return case15;}
            if (!case16.equals("case16")) {return case16;}
            if (!case17.equals("case17")) {return case17;}
            if (!case18.equals("case18")) {return case18;}
            if (!case19.equals("case19")) {return case19;}
            if (!case20.equals("case20")) {return case20;}



            
            return "No corresponde a ni un caso evaluado";
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
