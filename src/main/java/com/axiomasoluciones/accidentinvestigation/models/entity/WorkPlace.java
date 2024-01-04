package com.axiomasoluciones.accidentinvestigation.models.entity;

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
    private String adverseWeatherDetalis;
    private Boolean adverseWeather;
    private String lightingDetails;
    private Boolean lighting;
    private LocalDate inspectionDate;
    private Boolean inspection;


    @Serial
    private static final long serialVersionUID = 1L;



}
