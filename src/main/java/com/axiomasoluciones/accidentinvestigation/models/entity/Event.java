package com.axiomasoluciones.accidentinvestigation.models.entity;

import com.axiomasoluciones.accidentinvestigation.dto.EventRequestDTO;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.persistencia.*;

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
    private LocalDate dateEvent;
    private Severity severity;
    private BodyPart bodyPart;
    private Injury injury;
    private IncidentType incidenType;

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
    private Boolean lockedIn;
    private Boolean lockedRequired;
    private Boolean lockedUsed;
    private Boolean workEquipmentFails;
    private String userId;

    public Event(EventRequestDTO eventRequestDTO){
        this.dateEvent = eventRequestDTO.dateEvent();
        this.severity = eventRequestDTO.severity();
        this.bodyPart = eventRequestDTO.bodyPart();
        this.injury = eventRequestDTO.injury();
        this.incidenType = eventRequestDTO.incidenType();
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
        this.lockedIn = eventRequestDTO.lockedIn();
        this.lockedRequired = eventRequestDTO.lockedRequired();
        this.lockedUsed = eventRequestDTO.lockedUsed();
        this.workEquipmentFails = eventRequestDTO.workEquipmentFails();
        this.userId = eventRequestDTO.userId();




    }

    @Serial
    private static final long serialVersionUID = 1L;

}
