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
public class WorkEquipment implements Serializable {

    private String name;
    private String workEquipmentFailsDetalis;
    private Boolean workEquipmentFails;
    private Energy energy;

    private String defenseFailedDetails;
    private Boolean defenseFailed;

    private String correctUseEquimantDetails;
    private Boolean correctUseEquimant;


    @Serial
    private static final long serialVersionUID = 1L;
}
