package com.axiomasoluciones.accidentinvestigation.models.entity;

import com.axiomasoluciones.accidentinvestigation.dto.EventRequestDTO;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.enums.*;

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
    private String title;
    private LocalDate dateEvent;
    private Severity severity;
    private BodyPart bodyPart;
    private Injury injury;

    private Boolean entry;
    private WorkOccasion workOccasion;
    private HoursWorked hoursWorked;
    private Boolean accidentHistory;

    private Boolean authorization;
    private Boolean authorizationWork;
    private Boolean pts;
    private Boolean ptsApplied;

    private Boolean machine;
    private Energy energy;
    private Boolean lockedRequired;
    private Boolean lockedUsed;
    private Boolean fails;
    private String userId;
    private String comment = "sin comentario";

    public Event(EventRequestDTO eventRequestDTO){

        this.title = eventRequestDTO.title();
        this.dateEvent = eventRequestDTO.dateEvent();
        this.severity = eventRequestDTO.severity();
        this.bodyPart = eventRequestDTO.bodyPart();
        this.injury = eventRequestDTO.injury();
        this.entry = eventRequestDTO.entry();
        this.workOccasion = eventRequestDTO.workOccasion();
        this.hoursWorked = eventRequestDTO.hoursWorked();
        this.accidentHistory = eventRequestDTO.accidentHistory();
        this.authorization = eventRequestDTO.authorization();
        this.authorizationWork = eventRequestDTO.authorizationWork();
        this.pts = eventRequestDTO.pts();
        this.ptsApplied = eventRequestDTO.ptsApplied();
        this.machine = eventRequestDTO.machine();
        this.energy = eventRequestDTO.energy();
        this.lockedRequired = eventRequestDTO.lockedRequired();
        this.lockedUsed = eventRequestDTO.lockedUsed();
        this.fails = eventRequestDTO.fails();
        this.userId = eventRequestDTO.userId();
        this.comment = eventRequestDTO.comment();

    }

    @Serial
    private static final long serialVersionUID = 1L;

}
