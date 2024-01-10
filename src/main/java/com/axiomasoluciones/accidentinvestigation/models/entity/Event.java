package com.axiomasoluciones.accidentinvestigation.models.entity;

import com.axiomasoluciones.accidentinvestigation.dto.EventRequestDTO;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.BodyPart;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.IncidentType;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Injury;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Severity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Event implements Serializable {

    @Id
    private String id;

    @Column(name="fecha")
    private LocalDate dateEvent;

    private String description;
    private Severity severity;
    private Severity poSeverity;
    private BodyPart bodyPart;
    private Injury injury;
    private IncidentType incidenType;
    private String imagen;
    private String aditionalImagen;

    private Worker worker;
    private WorkPlace workPlace;
    private Method method;
    private Activity activity;
    private Machine machine;

    public Event(EventRequestDTO eventRequestDTO){
        this.dateEvent = eventRequestDTO.dateEvent();
        this.description = eventRequestDTO.description();
        this.severity = eventRequestDTO.severity();
        this.poSeverity = eventRequestDTO.poSeverity();
        this.injury = eventRequestDTO.injury();
        this.incidenType = eventRequestDTO.incidenType();
        this.imagen = eventRequestDTO.imagen();
        this.aditionalImagen = eventRequestDTO.aditionalImagen();
        this.worker = eventRequestDTO.worker();
        this.method = eventRequestDTO.method();
        this.workPlace = eventRequestDTO.workPlace();
        this.machine=  eventRequestDTO.machine();
        this.activity = eventRequestDTO.activity();
        this.machine = eventRequestDTO.machine();
    }

    @Serial
    private static final long serialVersionUID = 1L;

}
