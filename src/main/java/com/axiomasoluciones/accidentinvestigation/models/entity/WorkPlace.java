package com.axiomasoluciones.accidentinvestigation.models.entity;


import com.axiomasoluciones.accidentinvestigation.dto.util.WorkPlaceRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WorkPlace implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String sector;

    private String adverseWeatherDetalis;
    private Boolean adverseWeather;

    private String lightingDetails;
    private Boolean lighting;

    private LocalDate inspectionDate;
    private Boolean inspection;

    @OneToMany(mappedBy = "workPlace", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Event> events;

    public WorkPlace(WorkPlaceRequestDTO workPlaceRequestDTO){
        this.name = workPlaceRequestDTO.name();
        this.sector = workPlaceRequestDTO.sector();
        this.adverseWeatherDetalis = workPlaceRequestDTO.adverseWeatherDetalis();
        this.adverseWeather = workPlaceRequestDTO.adverseWeather();
        this.lightingDetails = workPlaceRequestDTO.lightingDetails();
        this.lighting = workPlaceRequestDTO.lighting();
        this.inspectionDate = workPlaceRequestDTO.inspectionDate();
        this.inspection = workPlaceRequestDTO.inspection();

    }

    @Serial
    private static final long serialVersionUID = 1L;



}
