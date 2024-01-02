package com.axiomasoluciones.accidentinvestigation.models.entity;


import com.axiomasoluciones.accidentinvestigation.dto.util.OrganizationalRequestDTO;
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
public class Organizational {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eppDetails;
    private Boolean eppDesignated;
    private Boolean eppUsed;

    private String authorizationDetails;
    private Boolean authorizationWork;

    private String riskDetails;
    private Boolean risk;

    private String ptsDetails;
    private Boolean pts;
    private Boolean ptsApplied;

    private String changeDetails;
    private Boolean change;

    private String blockingDetails;
    private Boolean blocking;

    private String expectedBehaviorDetails;
    private Boolean expectedBehavior;

    @OneToMany(mappedBy = "organizationals", cascade = CascadeType.ALL)
    private List<Event> events;

    public Organizational(OrganizationalRequestDTO organizationalRequestDTO){
        this.eppDetails = organizationalRequestDTO.ptsDetails();
        this.eppDesignated = organizationalRequestDTO.eppDesignated();
        this.eppUsed = organizationalRequestDTO.eppUsed();
        this.authorizationDetails = organizationalRequestDTO.authorizationDetails();
        this.authorizationWork = organizationalRequestDTO.authorizationWork();
        this.riskDetails = organizationalRequestDTO.riskDetails();
        this.risk = organizationalRequestDTO.risk();
        this.ptsDetails = organizationalRequestDTO.ptsDetails();
        this.pts = organizationalRequestDTO.pts();
        this.ptsApplied = organizationalRequestDTO.ptsApplied();
        this.changeDetails = organizationalRequestDTO.changeDetails();
        this.change = organizationalRequestDTO.change();
        this.blockingDetails = organizationalRequestDTO.blockingDetails();
        this.blocking = organizationalRequestDTO.blocking();
        this.expectedBehaviorDetails = organizationalRequestDTO.expectedBehaviorDetails();
        this.expectedBehavior = organizationalRequestDTO.expectedBehavior();

    }
}