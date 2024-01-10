package com.axiomasoluciones.accidentinvestigation.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Activity implements Serializable {

    //PhysicsCap
    //La tarea requeria fuerza
    private String strength;
    //La tarea requeria atención
    private String attention;
    //Tarea repetitiva
    private String repetition;
    //Que movilidad requeria la tarea
    private String mobility;
    //Que nivel de presición requeria la terea
    private String precision;
    //Trabajo en Altura
    private Boolean height;
    //Trabajo en espacio confinadi
    private Boolean confinedSpace;

    //La actividad requeria bloqueo de equipo
    private Boolean locked;

    //La realizo el bloqueo
    private Boolean lokedUsed;



    private static final long serialVersionUID = 1L;

}
