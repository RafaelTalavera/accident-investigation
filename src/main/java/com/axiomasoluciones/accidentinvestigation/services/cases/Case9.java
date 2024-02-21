package com.axiomasoluciones.accidentinvestigation.services.cases;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.enums.Energy;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.enums.HoursWorked;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.enums.WorkOccasion;
import org.json.JSONObject;

public class Case9 {

    public String case9(Event event) {

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
                        && event.getFails()               // true: presenta falla
                        && event.getEnergy().equals(Energy.DESCONOCIDA)


        ) {
            // Crear un objeto JSON con la hipótesis
            JSONObject jsonHipotesis = new JSONObject();
            jsonHipotesis.put("Personales", "10 Factores personales: Según la información relevada, no se cumplió con el procedimiento de bloqueo de equipos. Esto se fundamenta en que el bloqueo era requerido y se podía realizar; sin embargo, se desconocía la energía del equipo. Esta inconsistencia, junto con las fallas previas del equipo, pudieron haber influido en la decisión del operador de no llevar a cabo el bloqueo de energía.");
            jsonHipotesis.put("Maquina", "Máquina: Según el relevamiento, la energía es DESCONOCIDA. Además, usted seleccionó que era posible hacer el bloqueo de energía, lo cual es una inconsistencia, ya que no es posible bloquear una energía que se desconoce, generando una grave inconsistencia. Adicionalmente, según lo ingresado en el formulario, el equipo presentaba fallas previas al evento.");
            jsonHipotesis.put("Metodo", "Método: Según la información ingresada, se puede inferir que el método de trabajo seguro presenta debilidades. Esto se fundamenta en que, aunque el método solicita que se realice un bloqueo de energía, la energía utilizada era desconocida, y el operador no llevó a cabo el procedimiento de bloqueo. El método debe contemplar claramente las energías intervinientes y la manera correcta de bloquearlas para evitar generar suspicacias.");

            // Devolver la representación en cadena del objeto JSON
            return jsonHipotesis.toString();
        }
        JSONObject jsonHipotesis = new JSONObject();
        jsonHipotesis.put("Caso 9 ", "Salio del if");


        return "caso9";
    }
}
