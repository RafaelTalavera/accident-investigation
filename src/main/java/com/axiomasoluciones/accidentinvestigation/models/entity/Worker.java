package com.axiomasoluciones.accidentinvestigation.models.entity;

import com.axiomasoluciones.accidentinvestigation.dto.util.WorkerRequestDTO;


import com.axiomasoluciones.accidentinvestigation.models.entity.util.MentalTension;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.PhysicalTension;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.PhysicsCap;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.MentalCap;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Worker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private LocalDate birth;
    private LocalDate entry;

    private String workOccasionDetails;
    private Boolean workOccasion;

    private String experienceDetails;
    private Boolean experience;

    private String fatiguedDetails;
    private Boolean fatigued;

    private String trainingDetails;
    private Boolean training;
    private Date trainingDate;

    private String physicsCapDetails;
    private PhysicsCap physicsCap;

    private String mentalCapDetails;
    private MentalCap mentalCap;

    private String physicalTensionDetails;
    private PhysicalTension physicalTension;

    private String mentalTensionDetails;
    private MentalTension mentalTension;

    @OneToMany(mappedBy = "worker", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Event> events;

    public Worker(WorkerRequestDTO workerRequestDTO){
        this.fullName = workerRequestDTO.fullName();
        this.birth = workerRequestDTO.birth();
        this.entry = workerRequestDTO.entry();
        this.workOccasionDetails = workerRequestDTO.workOccasionDetails();
        this.workOccasion = workerRequestDTO.workOccasion();
        this.experienceDetails = workerRequestDTO.experienceDetails();
        this.experience = workerRequestDTO.experience();
        this.fatiguedDetails = workerRequestDTO.fatiguedDetails();
        this.fatigued = workerRequestDTO.fatigued();
        this.trainingDetails = workerRequestDTO.trainingDetails();
        this.training = workerRequestDTO.training();
        this.trainingDate = workerRequestDTO.trainingDate();
        this.physicsCapDetails = workerRequestDTO.physicsCapDetails();
        this.physicsCap = workerRequestDTO.physicsCap();
        this.mentalCapDetails = workerRequestDTO.mentalCapDetails();
        this.physicalTensionDetails = workerRequestDTO.physicalTensionDetails();
        this.physicalTension = workerRequestDTO.physicalTension();

    }

    @Serial
    private static final long serialVersionUID = 1L;


}
