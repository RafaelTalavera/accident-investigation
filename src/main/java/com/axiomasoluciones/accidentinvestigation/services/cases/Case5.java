package com.axiomasoluciones.accidentinvestigation.services.cases;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.Energy;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.HoursWorked;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.WorkOccasion;
import org.json.JSONObject;

public class Case5 {

    public String case5(Event event) {

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
                        && !event.getLockedIn()           // false: No es posible bloquear
                        && !event.getLockedRequired()     // false:No era requerido el bloqueo
                        && !event.getLockedUsed()         // false: No se realizo el bloque
                        && event.getFails()               // true: el equipo presenta falla
                        && event.getEnergy().equals(Energy.DESCONOCIDA)



        ) {
            // Crear un objeto JSON con la hipótesis
            JSONObject jsonHipotesis = new JSONObject();
            jsonHipotesis.put("Personales", "Factores personales: No se identificaron posibles causas según la información ingresdada");
            jsonHipotesis.put("Maquina", "Máquina: Según el relevamiento, la energía es: " + event.getEnergy() +
                    " , lo provoca que no sea posible bloquerla correctamente. " +
                    " Revisa en profundidad el equipo utilizado con el fin de poder generar los mecanismos para bloquear las energías que utiliza." +
                    "También es importante destacar que el equipo presentaba fallas previas al evento.");
            jsonHipotesis.put("Metodo", "Método: Según la información ingresada, se puede inferir que el método de trabajo seguro presenta debilidades. Esto se fundamenta en que, a pesar de cumplirse con el estándar de trabajo seguro, de igual manera, el evento ocurrió. " );

            // Devolver la representación en cadena del objeto JSON
            return jsonHipotesis.toString();
        }
        JSONObject jsonHipotesis = new JSONObject();
        jsonHipotesis.put("Caso 5 ", "Salio del if");


        return "caso5";
    }
}
