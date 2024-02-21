package com.axiomasoluciones.accidentinvestigation.services.cases;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.enums.HoursWorked;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.enums.WorkOccasion;
import org.json.JSONObject;

public class Case11 {

    public String case11(Event event) {

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

                        && !event.getLockedRequired()      // true:  requerido el bloqueo
                        && !event.getLockedUsed()          // false: No se realizo el bloque






        ) {
            // Crear un objeto JSON con la hipótesis
            JSONObject jsonHipotesis = new JSONObject();
            jsonHipotesis.put("Personales", "A priori, no se encontraron posibles factores personales que pudieron influir en el evento. Es importante trabajar en la cultura preventiva, ya que la persona estaba trabajando con un equipo energizado sin la posibilidad de realizar el bloqueo del equipo.");
            jsonHipotesis.put("Maquina", "Máquina: Según el relevamiento, la energía es: " +
                    event.getEnergy()  +  " La energía identificada, según el relevamiento, no era posible bloquearla y tampoco era requerida por procedimiento."+
                    " Esto representa una debilidad, ya que las energías que interactúan con las personas que realizan las tareas es necesario aislarlas y bloquearlas.");
            jsonHipotesis.put("Metodo", " Método:Según la información ingresada, la energía que intervino en el accidente es: " +
                    event.getEnergy() + " Esta energía no era posible bloquearla, tampoco era requerida por la tarea, por ende," +
                    " no se realizó. Esto representa una debilidad en la metodología de trabajo seguro, ya que las energías " +
                    "  que interactúan con las personas deben ser aisladas y se deben establecer los mecanismos para bloquearlas.");

            // Devolver la representación en cadena del objeto JSON
            return jsonHipotesis.toString();
        }
        JSONObject jsonHipotesis = new JSONObject();
        jsonHipotesis.put("Caso 11 ", "Salio del if");


        return "caso11";
    }
}
