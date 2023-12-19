package com.axiomasoluciones.accidentinvestigation.models.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String workOccasionDetails;
    private Boolean workOccasion;

    private String authorizationDetails;
    private Boolean authorization;

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

}
