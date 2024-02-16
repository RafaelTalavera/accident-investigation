package com.axiomasoluciones.accidentinvestigation.services.cases;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.Energy;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.HoursWorked;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.WorkOccasion;
import org.json.JSONObject;

public class Case7 {

    public String case7(Event event) {

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
                        && event.getLockedIn()            // true: es posible bloquear
                        && event.getLockedRequired()      // true: requerido el bloqueo
                        && !event.getLockedUsed()         // false: NO se realizo el bloque
                        && event.getFails()               // true: el equipo presenta falla
                        && event.getEnergy().equals(Energy.DESCONOCIDA)


        ) {
            // Crear un objeto JSON con la hipótesis
            JSONObject jsonHipotesis = new JSONObject();
            jsonHipotesis.put("Personales", "Factores personales: Según la información relevada, se incumplió un procedimiento e instrucciones de trabajo. Esto se fundamenta en que el bloqueo era requerido y se podía realizar; sin embargo, se desconocía la energía del equipo. Esta incongruencia pudo estar relacionada con el incumplimiento.");
            jsonHipotesis.put("Maquina", "Máquina: Según el relevamiento, la energía es: " + event.getEnergy() +
                    " , lo provoca que no sea posible bloquerla correctamente. " +
                    " Adicionalmente, usted seleccionó que era posible hacer el bloqueo de energía, que era requerido para la tarea que se realizaba. Esto representa una grave inconsistencia, teniendo en cuenta que la energía es desconocida." +
                    "También es importante destacar que el equipo presentaba fallas previas al evento.");
            jsonHipotesis.put("Metodo", "Método: Según la información ingresada, se puede inferir que el método de trabajo seguro presenta debilidades. Esto se fundamenta en que, a pesar de cumplirse con el estándar de trabajo seguro, de igual manera, el evento ocurrió. ");

            // Devolver la representación en cadena del objeto JSON
            return jsonHipotesis.toString();
        }
        JSONObject jsonHipotesis = new JSONObject();
        jsonHipotesis.put("Caso 7 ", "Salio del if");


        return "caso7";
    }
}
