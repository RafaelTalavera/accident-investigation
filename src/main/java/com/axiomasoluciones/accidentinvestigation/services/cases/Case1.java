package com.axiomasoluciones.accidentinvestigation.services.cases;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.HoursWorked;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.WorkOccasion;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


@Service
public class Case1 {

  public String case1(Event event){

     if (
             event.getEntry()                 // true: tienne más de 6 meses
             && event.getWorkOccasion().equals(WorkOccasion.TAREAS_RUTINARIAS)
             && event.getHoursWorked().equals(HoursWorked.MENOS_8)
             && !event.getAccidentHistory()   // false: no hubo accidentes previos
             && event.getAuthorization()      // true : el trabajo requeria autorización
             && event.getAuthorizationWork()  // true: el trabajador tenia autorización
             && event.getPts()                // true: Existia un Pts
             && event.getPtsApplied()         // true: El trabajador aplico el pts
             && !event.getMachine()           // false: No se uso maquina

     ){
         // Crear un objeto JSON con la hipótesis
         JSONObject jsonHipotesis = new JSONObject();
         jsonHipotesis.put("Personales", "Factores personales: No se identificaron posibles causas según la información ingresdada");
         jsonHipotesis.put("Maquina", " Maquina: Según la información ingresada no se utilizo una maquina en al momento de ocurrido el evento ");
         jsonHipotesis.put("Metodo", " Método: Según la información ingresada, se puede inferir que el método de trabajo seguro presenta debilidades. Esto se fundamenta en que, a pesar de cumplirse con el estándar de trabajo seguro, de igual manera, el evento ocurrió. ");

         // Devolver la representación en cadena del objeto JSON
         return jsonHipotesis.toString();
     }

      return "caso1";
  }

}
