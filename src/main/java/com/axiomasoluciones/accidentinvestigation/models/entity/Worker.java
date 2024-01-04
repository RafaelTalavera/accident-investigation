package com.axiomasoluciones.accidentinvestigation.models.entity;




import com.axiomasoluciones.accidentinvestigation.models.entity.util.MentalTension;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.PhysicalTension;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.PhysicsCap;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.MentalCap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Worker implements Serializable {

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


    @Serial
    private static final long serialVersionUID = 1L;



}
