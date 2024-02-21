package com.axiomasoluciones.accidentinvestigation.services.cases;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.enums.Energy;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.enums.HoursWorked;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.enums.WorkOccasion;
import org.json.JSONObject;

public class Case8 {

    public String case8(Event event) {

        if (
                         event.getEntry()                 // true: tiene más de 6 meses
                        && event.getWorkOccasion().equals(WorkOccasion.TAREAS_RUTINARIAS)
                        && event.getHoursWorked().equals(HoursWorked.MENOS_8)
                        && !event.getAccidentHistory()    // false: no hubo accidentes previos
                        && event.getAuthorization()       // true : el trabajo requeria autorización
                        && event.getAuthorizationWork()   // true: el trabajador tenia autorización
                        && event.getPts()                 // true: Existia un Pts
                        && event.getPtsApplied()          // true: El trabajador aplico el pts
                        && event.getMachine()             // true: Uso maquina

                        && !event.getLockedRequired()     // false: NO requerido el bloqueo
                        && event.getLockedUsed()          // true: se realizo el bloque
                        && !event.getFails()              // false: No presenta falla
                        && event.getEnergy().equals(Energy.DESCONOCIDA)


        ) {
            // Crear un objeto JSON con la hipótesis
            JSONObject jsonHipotesis = new JSONObject();
            jsonHipotesis.put("Personales", "Factores personales: Según la información relevada, se cumplió parcialmente con el procedimiento e instrucciones de trabajo. Esto se fundamenta en que el bloqueo no era requerido y se podía realizar; sin embargo, se desconocía la energía del equipo. Esta incongruencia pudo estar relacionada con la inconsistencia y la decisión del operador al realizar un bloqueo de energía sin que fuera necesario.");
            jsonHipotesis.put("Maquina", "Máquina: Según el relevamiento, la energía es: DESCONOCIDA , lo provoca que no sea posible bloquerla correctamente.  Adicionalmente, usted seleccionó que era posible hacer el bloqueo de energía, que esta no era requerido para la tarea que se realizaba. Esto representa una grave inconsistencia, teniendo en cuenta que la energía es desconocida.");
            jsonHipotesis.put("Metodo", "Método: Según la información ingresada, se puede inferir que el método de trabajo seguro presenta debilidades. Esto se fundamenta en que, aunque el método no solicitaba realizar un bloqueo de energía y la energía utilizada era desconocida, el operador llevó a cabo el procedimiento de bloqueo. El método debe contemplar claramente las energías intervinientes y la manera correcta de bloquearlas.");

            // Devolver la representación en cadena del objeto JSON
            return jsonHipotesis.toString();
        }
        JSONObject jsonHipotesis = new JSONObject();
        jsonHipotesis.put("Caso 8 ", "Salio del if");


        return "caso8";
    }
}
