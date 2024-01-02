package com.axiomasoluciones.accidentinvestigation.dto.util;

import com.axiomasoluciones.accidentinvestigation.models.entity.util.Energy;

public record WorkEquimentRequestDTO(
        String name,
        String workEquipmentFailsDetalis,
        Boolean workEquipmentFails,
        Energy energy,
        Boolean defenseFailed,
        String correctUseEquimantDetails,
        Boolean correctUseEquimant
) {
}
