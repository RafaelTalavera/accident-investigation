package com.axiomasoluciones.accidentinvestigation.models.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Method {

    private Boolean eppDesignated;
    private Boolean eppUseds;

    private Boolean authorization;
    private Boolean authorizationWork;

    private Boolean pts;
    private Boolean ptsApplied;

    private Boolean expectedBehavior;

}