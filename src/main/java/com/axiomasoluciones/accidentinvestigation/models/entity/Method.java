package com.axiomasoluciones.accidentinvestigation.models.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Method {

    //Existian Epp asigados
    private Boolean eppDesignated;
    //Se estaban usando
    private Boolean eppUseds;
    //La terea requeria utorizacion
    private Boolean authorization;
    //El trabajador estaba autorizado
    private Boolean authorizationWork;
    //La terea tenia Pts
    private Boolean pts;
    //La persona aplico el pts
    private Boolean ptsApplied;
    //Riesgo evaluado
    private Boolean risk;
    //Existio algún cambio en el metodo en el ultimo tiempo
    private Boolean change;

    //La persona actuo como se esperaba
    private Boolean expectedBehavior;

}