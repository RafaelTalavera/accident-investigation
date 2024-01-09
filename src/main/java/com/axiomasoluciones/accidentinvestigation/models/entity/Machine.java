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
    private Boolean workEquipmentFails;
    private Boolean defense;
    private String defenseIntegrity;
    private String correctUseEquimant;
    private String locked;

    @Serial
    private static final long serialVersionUID = 1L;
}
