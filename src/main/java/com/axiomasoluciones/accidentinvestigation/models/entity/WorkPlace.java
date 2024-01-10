package com.axiomasoluciones.accidentinvestigation.models.entity;

import com.axiomasoluciones.accidentinvestigation.models.entity.util.Weather;
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
public class WorkPlace implements Serializable {

    private String name;
    private String sector;
    private Boolean inside;
    private Weather weather;
    private String lighting;
    private String noise;
    private LocalDate inspectionDate;
    private Boolean inspection;

    @Serial
    private static final long serialVersionUID = 1L;



}
