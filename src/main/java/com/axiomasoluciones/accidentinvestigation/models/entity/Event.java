package com.axiomasoluciones.accidentinvestigation.models.entity;

import com.axiomasoluciones.accidentinvestigation.dto.util.EventRequestDTO;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.BodyPart;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.IncidentType;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Injury;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Severity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

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
    private BodyPart bodyPart;
    private Injury injury;
    private IncidentType incidenType;
    private String imagen;
    private String aditionalImagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_Place_id")
    private WorkPlace workPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "organizational_id")
    private Organizational organizationals;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "workEquipment")
    private WorkEquipment workEquipment;

    public Event(EventRequestDTO eventRequestDTO){
        this.dateEvent = eventRequestDTO.dateEvent();
        this.description = eventRequestDTO.description();
        this.severity = eventRequestDTO.severity();
        this.poSeverity = eventRequestDTO.poSeverity();
        this.injury = eventRequestDTO.injury();
        this.incidenType = eventRequestDTO.incidenType();
        this.imagen = eventRequestDTO.imagen();
        this.aditionalImagen = eventRequestDTO.aditionalImagen();
        this.worker = worker;
        this.organizationals = organizationals;
        this.workPlace = workPlace;
        this.workEquipment=  workEquipment;

    }

    @Serial
    private static final long serialVersionUID = 1L;

}
