package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.HoursWorked;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.WorkOccasion;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


@Service
public class CausasService {

  public String case1(Event event){

     if (
             event.getEntry()
             && event.getWorkOccasion().equals(WorkOccasion.TAREAS_RUTINARIAS)
             && event.getHoursWorked().equals(HoursWorked.MENOS_8)
             && event.getAccidentHistory()
             && event.getAuthorization()
             && event.getAuthorizationWork()
             && event.getPts()
             && event.getPtsApplied()

     ){
         // Crear un objeto JSON con la hipótesis
         JSONObject jsonHipotesis = new JSONObject();
         jsonHipotesis.put("Personales", "Factores personales: No se identificaron posibles causas según la información ingresdada");
         jsonHipotesis.put("Maquina", " Maquina: No se identificaron posibles causas según la información ingresdada ");
         jsonHipotesis.put("Metodo", " Metodo: Segun la información ingresada se puede inferir que el metodo de trabajo seguro presenta debilidades. Esto se fundamenta en que a pesar de cumplirse con el estandar de trabajo seguro el evento ocurrio ");

         // Devolver la representación en cadena del objeto JSON
         return jsonHipotesis.toString();
     }
      JSONObject jsonHipotesis = new JSONObject();
      jsonHipotesis.put("Caso 1 ", "Salio del if");

      // Devolver la representación en cadena del objeto JSON
      return jsonHipotesis.toString();
  }


}
