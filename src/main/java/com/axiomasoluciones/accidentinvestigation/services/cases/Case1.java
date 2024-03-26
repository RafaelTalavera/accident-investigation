package com.axiomasoluciones.accidentinvestigation.services.cases;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.enums.HoursWorked;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.enums.WorkOccasion;
import org.json.JSONObject;

public class Case1 {

    public String case1(Event event) {

        if (

            //metodo
                event.getEntry()                         // true: Antiguedad
                        && event.getAuthorization()      // true: requeria autorización
                        && event.getPts()                // true: Existia un Pts
                        && event.getWorkOccasion()

                        //Mano de obra
                        && event.getHoursWorked()
                        && event.getAccidentHistory()    // true: hubo accidentes previos
                        && event.getAuthorizationWork()  // true: el trabajador tenia autorización
                        && event.getPtsApplied()         // true: El trabajador aplico el pts
                        && event.getLockedUsed()         // true: Se realizo el bloque

                        //Maquina
                        && event.getMachine()            // true: Uso maquina
                        && event.getLockedRequired()     // true: Era requerido el bloqueo
                        && event.getFails()              // true:  Hay fallas


        ) {
            // Crear un objeto JSON con la hipótesis
            JSONObject jsonHipotesis = new JSONObject();
            jsonHipotesis.put("Metodo", "Aunque no se observaron causas directamente relacionadas con el método, se puede inferir que este presenta debilidades. Esta conclusión se basa en la premisa de que, si existe un procedimiento de trabajo seguro y el trabajador lo cumple, el evento no debería haber ocurrido. " +
                    " Es importante analizar el método de trabajo teniendo en cuenta: La lesión " + event.getInjury() + " y la enegía empleada en la tarea: " + event.getEnergy());
            jsonHipotesis.put("Personales", "No se evidencian factores que puedan estar relacionados con incumplimientos de procedimientos, fatiga o falta de experiencia en el puesto. No obstante, la persona cuenta con un historial de accidentes previos, lo que sugiere que podría ser alguien propenso a conductas riesgosas.");
            jsonHipotesis.put("Maquina", "No se identificaron causas probables" +
                    ". Según la información ingresada, la energía utilizada es: " + event.getEnergy() + " y es posible bloquearla." +
                    " Se recomienda revisar si la naturaleza de la lesión: " + event.getInjury() + " es coherente con la energía bloqueada." +
                    " Además, identificar que no existan energías residuales que no hayan sido bloqueadas.");

            // Devolver la representación en cadena del objeto JSON
            return jsonHipotesis.toString();
        }
        JSONObject jsonHipotesis = new JSONObject();
        jsonHipotesis.put("Caso 1 ", "Salio del if");


        return "caso1";
    }
}
