package com.axiomasoluciones.accidentinvestigation.models.entity;

import com.axiomasoluciones.accidentinvestigation.dto.EventRequestDTO;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.BodyParts;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Injury;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Severity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="fecha")
    private LocalDateTime dateEvent;
    private String description;
    private Severity severity;
    private Severity poSeverity;
    private List<BodyParts> bodyPartsList;
    private List<Injury> injuriesList;
    private String imagen;
    private String aditionalImagen;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_Place_id")
    private WorkPlace workPlace;

    @JsonIgnore
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Investigation> investigations;

    public Event(EventRequestDTO eventRequestDTO){
        this.dateEvent = eventRequestDTO.dateEvent();
        this.description = eventRequestDTO.description();
        this.severity = eventRequestDTO.severity();
        this.poSeverity = eventRequestDTO.poSeverity();
        this.bodyPartsList = eventRequestDTO.bodyPartsList();
        this.injuriesList = eventRequestDTO.injuriesList();
        this.imagen = eventRequestDTO.imagen();
        this.aditionalImagen = eventRequestDTO.editionalImagen();
        this.worker = worker;
        this.workPlace = workPlace;


    }

    @Serial
    private static final long serialVersionUID = 1L;



}
