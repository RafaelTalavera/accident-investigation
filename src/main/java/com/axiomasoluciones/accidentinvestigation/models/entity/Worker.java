package com.axiomasoluciones.accidentinvestigation.models.entity;




import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.WorkOccasion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Worker implements Serializable {

    private LocalDate birth;
    private LocalDate entry;
    private WorkOccasion workOccasion;
    private Long hoursWorked;
    private LocalDate trainingDate;
    //La persona ya estuvo accidentes
    private Boolean accidentHistory;
    //Fecha de Inspeccion
    private LocalDate dateAccidentHistory;
    //Fue un acto voluntario
    private Boolean volunteer;

    @Serial
    private static final long serialVersionUID = 1L;



}
