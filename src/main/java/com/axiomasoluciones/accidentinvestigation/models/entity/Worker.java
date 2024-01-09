package com.axiomasoluciones.accidentinvestigation.models.entity;




import com.axiomasoluciones.accidentinvestigation.models.entity.util.*;

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

    private LocalDate birth;
    private LocalDate entry;
    private WorkOccasion workOccasion;
    private Long experience;
    private Long hoursWorked;
    private LocalDate trainingDate;
    private Boolean accidentHistory;
    private LocalDate dateAccidentHistory;

    @Serial
    private static final long serialVersionUID = 1L;



}
