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
    //fuerza
    private String strength;

    private String attention;
    private String repetition;
    private String mobility;
    private String precision;
    private Boolean height;
    private Boolean confinedSpace;

    //La actividad requeria bloqueo de equipo
    private Boolean locked;

    private static final long serialVersionUID = 1L;

}
