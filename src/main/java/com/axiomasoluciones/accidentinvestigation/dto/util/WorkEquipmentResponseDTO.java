package com.axiomasoluciones.accidentinvestigation.dto.util;

import com.axiomasoluciones.accidentinvestigation.models.entity.WorkEquipment;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Energy;

public record WorkEquipmentResponseDTO(
        Long id,
        String name,
        String workEquipmentFailsDetalis,
        Boolean workEquipmentFails,
        Energy energy,
        Boolean defenseFailed,
        String correctUseEquimantDetails,
        Boolean correctUseEquimant



) {
    public WorkEquipmentResponseDTO(WorkEquipment workEquipment){
        this(workEquipment.getId(),
             workEquipment.getName(),
                workEquipment.getWorkEquipmentFailsDetalis(),
                workEquipment.getWorkEquipmentFails(),
                workEquipment.getEnergy(),
                workEquipment.getDefenseFailed(),
                workEquipment.getCorrectUseEquimantDetails(),
                workEquipment.getCorrectUseEquimant());


    }
}
