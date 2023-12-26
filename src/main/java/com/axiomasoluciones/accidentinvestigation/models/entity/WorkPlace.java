package com.axiomasoluciones.accidentinvestigation.models.entity;


import com.axiomasoluciones.accidentinvestigation.dto.WorkPlaceRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.WorkPlaceResponseDTO;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Sector;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
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


    @OneToMany(mappedBy = "workPlace", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Worker> workers;


    @OneToMany(mappedBy = "workPlace", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Investigation> investigations;


    @OneToMany(mappedBy = "workPlace", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Event> events;

    public WorkPlace(WorkPlaceRequestDTO workPlaceRequestDTO){
        this.name = workPlaceRequestDTO.name();
        this.sector = workPlaceRequestDTO.sector();
        this.investigations = investigations;
        this.workers = workers;
        this.events = events;
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
