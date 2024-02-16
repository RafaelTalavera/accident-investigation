package com.axiomasoluciones.accidentinvestigation.services.cases;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.Energy;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.HoursWorked;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.WorkOccasion;
import org.json.JSONObject;

public class Case3 {

    public String case3(Event event) {

        if (
                event.getEntry()                 // true: tiene más de 6 meses
                        && event.getWorkOccasion().equals(WorkOccasion.TAREAS_RUTINARIAS)
                        && event.getHoursWorked().equals(HoursWorked.MENOS_8)
                        && !event.getAccidentHistory()   // false: no hubo accidentes previos
                        && event.getAuthorization()      // true : el trabajo requeria autorización
                        && event.getAuthorizationWork()  // true: el trabajador tenia autorización
                        && event.getPts()                // true: Existia un Pts
                        && event.getPtsApplied()         // true: El trabajador aplico el pts
                        && event.getMachine()            // true: Uso maquina
                        && event.getLockedIn()           // true: Es posible bloquear
                        && event.getLockedRequired()     // true: Era requerido el bloqueo
                        && event.getLockedUsed()         // true: Se realizo el bloque
                        && !event.getFails()             // false: No hay fallas
                        && event.getEnergy().equals(Energy.DESCONOCIDA)



        ) {
            // Crear un objeto JSON con la hipótesis
            JSONObject jsonHipotesis = new JSONObject();
            jsonHipotesis.put("Personales", "Factores personales: No se identificaron posibles causas según la información ingresdada");
            jsonHipotesis.put("Maquina", "Máquina: Revise cuidadosamente el relevamiento." +
                    " Usted indicó que la energía del equipo utilizado es: " + event.getEnergy() +
                    " y, además, seleccionó que era posible realizar el bloqueo de energía; " +
                    "también señaló que se llevó a cabo el bloqueo." +
                    " Esto es una incoherencia, ya que no se puede bloquear una energía que no se conoce.");
            jsonHipotesis.put("Metodo", "Método: Según la información ingresada, se puede inferir que el método de trabajo seguro presenta debilidades. Esto se fundamenta en que, a pesar de cumplirse con el estándar de trabajo seguro, de igual manera, el evento ocurrió. ");

            // Devolver la representación en cadena del objeto JSON
            return jsonHipotesis.toString();
        }
        JSONObject jsonHipotesis = new JSONObject();
        jsonHipotesis.put("Caso 3 ", "Salio del if");


        return "caso3";
    }
}
