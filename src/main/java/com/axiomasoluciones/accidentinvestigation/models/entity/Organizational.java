package com.axiomasoluciones.accidentinvestigation.models.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Organizational {

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


}