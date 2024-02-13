/*package com.axiomasoluciones.accidentinvestigation.services.logica;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.Weather.GRANIZO;
import static com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.WorkOccasion.TAREAS_PROPIAS_RUTINA;

public class Case11 {

    public String case11(Event event) {
        LocalDate currentDate = event.getDateEvent();
        if (ChronoUnit.MONTHS.between(event.getWorker().getEntry(), currentDate) < 6 && event.getWorker().getWorkOccasion().equals(TAREAS_PROPIAS_RUTINA)) {
            ChronoUnit.MONTHS.between(event.getWorker().getTrainingDate(), currentDate);
        }// && event.getWorker().getHoursWorked() < 8
//&& event.getWorker().getVolunteer()
//MedioAmbiente adecuado
// && !event.getWorkPlace().getInside()
//&& event.getWorkPlace().getInspection()
// && ChronoUnit.MONTHS.between(event.getWorkPlace().getInspectionDate(), currentDate) < 2
//Metodo exigencia bajo
// && event.getMethod().getExpectedBehavior()
//Maquina Ingeniería adecuada
//Actividad Tensión Física o Fisiológica baja
        return "case11";
    }
}
*/