package com.axiomasoluciones.accidentinvestigation.services.logica.tensionFisica;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Case5AntiMaSeisTenFiMediaOchoTraFuerzaMeMovilidadMe {
    public String case5(Event event) {
        LocalDate entryDate = event.getWorker().getEntry();
        LocalDate currentDate = event.getDateEvent();
        if (
            //habilidades / entrenamiento
                event.getWorker().getEntry() != null
                        && ChronoUnit.MONTHS.between(event.getWorker().getEntry(), currentDate) > 6
                        && event.getWorker().getTrainingDate() != null
                        && ChronoUnit.MONTHS.between(event.getWorker().getTrainingDate(), currentDate) < 6
                        && !event.getWorker().getAccidentHistory()
                        //Estándares de trabajo adecuado
                        && event.getMethod().getEppUseds() != null
                        && event.getMethod().getEppUseds() //verdadero tenian asignado EPP
                        && event.getMethod().getAuthorization() != null
                        && event.getMethod().getAuthorization() //verdadero requeria autorizacion
                        && event.getMethod().getAuthorizationWork() != null
                        && event.getMethod().getAuthorizationWork() //verdadero el trabajador tenia atorizacion
                        && event.getMethod().getPts() != null
                        && event.getMethod().getPts() // verdadero tenia procedimiento de trabajo seguro
                        && event.getMethod().getPtsApplied() != null
                        && event.getMethod().getPtsApplied() // verdadero aplico el procedimiento
                        && event.getMethod().getExpectedBehavior() != null
                        && event.getMethod().getExpectedBehavior() // verdadero actuo como se esperaba

                        //Tensión Física o Fisiológica Media
                        && event.getWorker().getHoursWorked() != null
                        && event.getWorker().getHoursWorked() > 8
                        && event.getActivity().getStrength() != null
                        && event.getActivity().getStrength().equalsIgnoreCase("media")
                        && event.getActivity().getMobility() != null
                        && event.getActivity().getMobility().equalsIgnoreCase("media")


                        //Tensión mental o psicológica baja
                        && event.getActivity().getAttention() != null
                        && event.getActivity().getAttention().equalsIgnoreCase("baja")
                        && event.getActivity().getRepetition() != null
                        && event.getActivity().getRepetition().equalsIgnoreCase("baja")
                        && event.getActivity().getPrecision() != null
                        && event.getActivity().getPrecision().equalsIgnoreCase("baja")
                        && event.getActivity().getHeight() != null
                        && !event.getActivity().getHeight()//falso--no es un trabajo en altura
                        && event.getActivity().getConfinedSpace() != null
                        && !event.getActivity().getConfinedSpace()//falso -- no es un trabajo en espacio confinado
                        && event.getActivity().getLocked() != null
                        && !event.getActivity().getLocked()//falso -- no requiere bloqueo
                        //no analizo si realizo el bloqueo porque no lo requeria

                        //Ingeniería adecuada
                        && event.getMachine().getWorkEquipmentFails() != null
                        && !event.getMachine().getWorkEquipmentFails() //Falso el quipo no presenta fallas
                        && event.getMachine().getDefense() != null
                        && event.getMachine().getDefense() //true el equipo tiene defensas
                        && event.getMachine().getDefenseIntegrity() != null
                        && event.getMachine().getDefenseIntegrity() //true las defensas estaban integras
                        && event.getMachine().getCorrectUseEquimant() != null
                        && event.getMachine().getCorrectUseEquimant() //true el equipo estaba siendo usado en forma correcta
                        && event.getMachine().getLocked() != null
                        && event.getMachine().getLocked() //true el quipo aislada la energia

                        //MedioAmbiente
                        && event.getWorkPlace().getInside() != null
                        && event.getWorkPlace().getInside()
                        //cuando analizo inside no analizo wather. Porque estoy en el interior y wather analiza las condiciones exteriores
                        && event.getWorkPlace().getLighting() != null
                        && !event.getWorkPlace().getLighting().equalsIgnoreCase("alto")
                        && event.getWorkPlace().getNoise() != null
                        && !event.getWorkPlace().getNoise().equalsIgnoreCase("alto")
                        && event.getWorkPlace().getInspection() != null
                        && event.getWorkPlace().getInspection()
                        && ChronoUnit.MONTHS.between(event.getWorkPlace().getInspectionDate(), currentDate) < 2) {
            return "Tensión Física o Fisiológica MEDIA: "  + event.getWorker().getHoursWorked() +" horas trabajadas "
                    +"-----Tipo de fuerza requerida: "+ event.getActivity().getStrength() +
                    " -----Tipo de movilidad: " + event.getActivity().getMobility();
        }
        return "case5";
    }
}
