package com.axiomasoluciones.accidentinvestigation.models.entity;


import com.axiomasoluciones.accidentinvestigation.models.entity.util.Energy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Machine implements Serializable {

    private String name;
    private Energy energy;

    //El equipo tenia alguna falla
    private Boolean workEquipmentFails;
    //El equipo tenia protecciones a partes energizadas
    private Boolean defense;
    // Cual era el estado de las protecciones
    private Boolean defenseIntegrity;
    // El equipo estaba siendo usado para lo que fue diseñado
    private Boolean correctUseEquimant;
    //El equipo tenia posibilidad de bloquear la energia
    private Boolean locked;

    @Serial
    private static final long serialVersionUID = 1L;
}
