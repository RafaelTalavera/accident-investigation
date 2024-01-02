package com.axiomasoluciones.accidentinvestigation.dto.util;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.models.entity.WorkPlace;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.MentalCap;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.MentalTension;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.PhysicalTension;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.PhysicsCap;

import java.time.LocalDate;

import java.util.Date;
import java.util.List;

public record WorkerRequestDTO(
        String fullName,
        LocalDate birth,
        LocalDate entry,
        String workOccasionDetails,
        Boolean workOccasion,
        String experienceDetails,
        Boolean experience,
        String fatiguedDetails,
        Boolean fatigued,
        String trainingDetails,
        Boolean training,
        Date trainingDate,

        String physicsCapDetails,
        PhysicsCap physicsCap,

        String mentalCapDetails,
        MentalCap mentalCap,

        String physicalTensionDetails,
        PhysicalTension physicalTension,

        String mentalTensionDetails,
        MentalTension mentalTension,

        Long workPlaceId,
        Long eventId

) {
}
