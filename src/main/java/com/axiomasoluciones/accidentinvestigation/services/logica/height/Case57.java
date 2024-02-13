/*package com.axiomasoluciones.accidentinvestigation.services.logica.height;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.Weather.GRANIZO;
import static com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.WorkOccasion.TAREAS_PROPIAS_RUTINA;

public class Case57 {

    public String case57(Event event) {
        LocalDate currentDate = event.getDateEvent();
        if (
            //habilidades entrenamiento bajo
                ChronoUnit.MONTHS.between(event.getWorker().getEntry(), currentDate) < 6
                        && event.getWorker().getWorkOccasion().equals(TAREAS_PROPIAS_RUTINA)
                   //     && event.getWorker().getHoursWorked() < 8
                        && ChronoUnit.MONTHS.between(event.getWorker().getTrainingDate(), currentDate) < 6
                        && !event.getWorker().getAccidentHistory()
                    //    && event.getWorker().getVolunteer()
                        //MedioAmbiente adecuado
                   //     && !event.getWorkPlace().getInside()
                        && event.getWorkPlace().getWeather().equals(GRANIZO)
                        && !event.getWorkPlace().getLighting().equalsIgnoreCase("alto")
                        && event.getWorkPlace().getNoise().equalsIgnoreCase("bajo")
                   //     && event.getWorkPlace().getInspection()
                    //    && ChronoUnit.MONTHS.between(event.getWorkPlace().getInspectionDate(), currentDate) < 2

                        //Metodo exigencia bajo
                        && event.getMethod().getEppDesignated()
                        && event.getMethod().getEppUseds()
                        && event.getMethod().getAuthorization()
                        && !event.getMethod().getAuthorizationWork() // no autorizado
                        && event.getMethod().getPts()
                        && event.getMethod().getPtsApplied()
                        && event.getMethod().getRisk()
                        && !event.getMethod().getChange()
                        && event.getMethod().getExpectedBehavior()
                        //Maquina Ingeniería adecuada
                        && !event.getMachine().getWorkEquipmentFails()
                        && event.getMachine().getDefense()
                        && event.getMachine().getDefenseIntegrity()
                        && event.getMachine().getCorrectUseEquimant()
                        && event.getMachine().getLocked()
                        //Actividad Tensión Física o Fisiológica baja
                        && event.getActivity().getStrength().equalsIgnoreCase("bajo")
                        && event.getActivity().getMobility().equalsIgnoreCase("bajo")
                        && event.getActivity().getAttention().equalsIgnoreCase("bajo")
                        && event.getActivity().getRepetition().equalsIgnoreCase("bajo")
                        && event.getActivity().getPrecision().equalsIgnoreCase("bajo")
                        && event.getActivity().getHeight()
                        && !event.getActivity().getConfinedSpace()
                        && event.getActivity().getLocked()
                        && event.getActivity().getLokedUsed()

        ) {
            return "Caso 57 TRABAJO EN ALTURA: habilidades/entrenamiento Baja: La persona tiene: " + ChronoUnit.MONTHS.between(event.getWorker().getEntry(), currentDate) +
                    " de antiguedad " + "-----Tiempo transcurrido de la capacitacion en meses: " + ChronoUnit.MONTHS.between(event.getWorker().getTrainingDate(), currentDate)
                    + "Uso de Bloqueo: " + event.getActivity().getLokedUsed() +
                    " Requeria bloqueo: " + event.getActivity().getLocked() +
                    " Espacio confinado: " + event.getActivity().getConfinedSpace() +
                    " Trabajo en Altura: " + event.getActivity().getHeight() +
                 //   " Trabajo inside: " + event.getWorkPlace().getInside() +
                    " Clima: " + event.getWorkPlace().getWeather() +
                    " Luz : " + event.getWorkPlace().getLighting() +
                    " El trabajador tenia autorizacion: " + event.getMethod().getAuthorizationWork();
        }
        return "case57";
    }
}


 */