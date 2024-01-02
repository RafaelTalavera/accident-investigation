package com.axiomasoluciones.accidentinvestigation.dto.util;
import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.MentalCap;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.MentalTension;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.PhysicalTension;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.PhysicsCap;
import java.time.LocalDate;
import java.util.Date;

public record WorkerResponseDTO(
        Long id,
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
        MentalTension mentalTension)
{
    public WorkerResponseDTO(Worker worker){
        this(worker.getId(),
                worker.getFullName(),
                worker.getBirth(),
                worker.getEntry(),
                worker.getWorkOccasionDetails(),
                worker.getWorkOccasion(),
                worker.getExperienceDetails(),
                worker.getExperience(),
                worker.getFatiguedDetails(),
                worker.getFatigued(),
                worker.getTrainingDetails(),
                worker.getTraining(),
                worker.getTrainingDate(),
                worker.getPhysicsCapDetails(),
                worker.getPhysicsCap(),
                worker.getMentalCapDetails(),
                worker.getMentalCap(),
                worker.getPhysicalTensionDetails(),
                worker.getPhysicalTension(),
                worker.getMentalTensionDetails(),
                worker.getMentalTension());
    }
}
