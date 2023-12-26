package com.axiomasoluciones.accidentinvestigation.models.entity;


import com.axiomasoluciones.accidentinvestigation.dto.InvestigationRequestDTO;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Energy;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Investigation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String climate;
    private String workEquipment;
    private Energy energy;

    private String workOccasionDetails;
    private Boolean workOccasion;

    private String authorizationDetails;
    private Boolean authorizationWork;

    private String riskDetails;
    private Boolean risk;

    private String ptsDetails;
    private Boolean pts;

    private String trainingDetails;
    private Boolean training;

    private String expectedBehaviorDetails;
    private Boolean expectedBehavior;

    private String defenseFailedDetails;
    private Boolean defenseFailed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workPlace_id")
    private WorkPlace workPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    public Investigation(InvestigationRequestDTO investigationRequestDTO){
        this.climate = investigationRequestDTO.climate();
        this.workEquipment = investigationRequestDTO.workEquipment();
        this.energy = investigationRequestDTO.energy();
        this.workOccasionDetails = investigationRequestDTO.workOccasionDetails();
        this.workOccasion = investigationRequestDTO.workOccasion();
        this.authorizationDetails = investigationRequestDTO.authorizationDetails();
        this.authorizationWork = investigationRequestDTO.authorizationWork();
        this.riskDetails = investigationRequestDTO.riskDetails();
        this.risk = investigationRequestDTO.risk();
        this.ptsDetails = investigationRequestDTO.ptsDetails();
        this.pts = investigationRequestDTO.pts();
        this.trainingDetails = investigationRequestDTO.trainingDetails();
        this.training = investigationRequestDTO.training();
        this.expectedBehaviorDetails = investigationRequestDTO.expectedBehaviorDetails();
        this.expectedBehavior = investigationRequestDTO.expectedBehavior();
        this.defenseFailedDetails = investigationRequestDTO.defenseFailedDetails();
        this.defenseFailed = investigationRequestDTO.defenseFailed();
        this.worker = worker;
        this.workPlace = workPlace;
        this.event = event;

    }

    }



