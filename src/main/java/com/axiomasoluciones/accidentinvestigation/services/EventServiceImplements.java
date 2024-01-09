package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IEventDao;
import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
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


            // bloque if para verificar si la persona tiene menos de 6 meses de antigüedad
            if (event.getWorker().getExperience() != null
                    && event.getWorker().getExperience() < 6
                    && event.getWorker().getHoursWorked() != null
                    && event.getWorker().getHoursWorked() < 8
                    && event.getWorker().getTrainingDate() != null
                    && ChronoUnit.MONTHS.between(event.getWorker().getTrainingDate(), currentDate) < 6
                    && !event.getWorker().getAccidentHistory()
                    && event.getMethod().getEppUseds() != null
                    && event.getMethod().getEppUseds()
                    && event.getMethod().getAuthorization() != null
                    && event.getMethod().getAuthorization()
                    && event.getMethod().getAuthorizationWork() != null
                    && event.getMethod().getAuthorizationWork()
                    && event.getMethod().getPts() != null
                    && event.getMethod().getPts()
                    && event.getMethod().getPtsApplied() != null
                    && event.getMethod().getPtsApplied()
                    && event.getMethod().getExpectedBehavior()!= null
                    && event.getMethod().getExpectedBehavior()
                    && event.getActivity().getStrength() != null
                    && event.getActivity().getStrength().equalsIgnoreCase("baja")
                    && event.getActivity().getAttention()!= null
                    && event.getActivity().getAttention().equalsIgnoreCase("baja")
                    && event.getActivity().getMobility()!= null
                    && event.getActivity().getMobility().equalsIgnoreCase("baja")
                    && event.getActivity().getPrecision()!= null
                    && event.getActivity().getPrecision().equalsIgnoreCase("baja")
                    && event.getActivity().getRepetition()!= null
                    && event.getActivity().getRepetition().equalsIgnoreCase("baja")
                    && event.getActivity().getHeight()!= null
                    && !event.getActivity().getHeight()
                    && event.getActivity().getConfinedSpace() != null
                    && ! event.getActivity().getConfinedSpace()
                    && event.getActivity().getLocked()!= null
                    && !event.getActivity().getLocked()
            ) {


                return "Experiencia: " + event.getWorker().getExperience() + " meses"+
                        " --Horas trabajadas: " + event.getWorker().getHoursWorked() +
                        " --Accidentes: " + event.getWorker().getAccidentHistory() +
                        " --Tiempo Ultima capacitacion: " + ChronoUnit.MONTHS.between(event.getWorker().getTrainingDate(), currentDate) +
                        " --la terea requeria autorización: " + event.getMethod().getAuthorization() +
                        " --El trabajador estaba autorizado: " + event.getMethod().getAuthorizationWork() +
                        " --EPP asignados: " + event.getMethod().getEppDesignated() +
                        " --Uso los EPP: " + event.getMethod().getEppUseds() +
                        " --La terea tiene PTS: " + event.getMethod().getPts() +
                        " --El trabajador aplico el PTS: " + event.getMethod().getPtsApplied()
                        + " caso 1 nada falla solo la antiguedad es menor a 6 meses";


            }
            // Bloque original para verificar si la persona cumple con otras condiciones
            else if (monthsDifferenceEntry > 6
                    && event.getWorker().getExperience() != null
                    && event.getWorker().getExperience() > 6
                    && event.getWorker().getHoursWorked() != null
                    && event.getWorker().getHoursWorked() > 6
                    && event.getWorker().getTrainingDate() != null
                    && ChronoUnit.MONTHS.between(event.getWorker().getTrainingDate(), currentDate) < 6
                    && event.getWorker().getAccidentHistory()
                    && event.getMethod().getAuthorization() != null
                    && event.getMethod().getAuthorization()
                    && event.getMethod().getAuthorizationWork() != null
                    && !event.getMethod().getAuthorizationWork()) {

                return "La persona era consciente de lo que hacía e infringió una norma de seguridad";
            } else {
                return "No se cumplió";
            }
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
