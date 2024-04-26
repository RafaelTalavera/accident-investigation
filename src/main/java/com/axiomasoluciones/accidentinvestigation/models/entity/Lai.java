package com.axiomasoluciones.accidentinvestigation.models.entity;

import com.axiomasoluciones.accidentinvestigation.dto.request.LaiRequestDTO;
import jakarta.persistence.Id;
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
public class Lai implements Serializable {
    @Id
    private String id;
    private LocalDate date;
    private String organizationId;
    private String nameOrganization;
    private String area;
    private String tipo;
    private String activity;
    private String aspect;
    private String impact;
    private String temporality;
    private String description;
    private String cycle;
    private int frequency;
    private int damage;
    private String stateHolder;
    private String legislation;
    private String meaningfulness;
    private String typeOfControl;
    private String descriptionOfControl;
    private LocalDate dateOfRevision;
    private String userId;

    @Serial
    private static final long serialVersionUID = 1L;

    public Lai (LaiRequestDTO laiRequestDTO){
        this.date = laiRequestDTO.date();
        this.organizationId = laiRequestDTO.organizationId();
        this.nameOrganization = laiRequestDTO.nameOrganization();
        this.area = laiRequestDTO.area();
        this.tipo = laiRequestDTO.tipo();
        this.activity = laiRequestDTO.activity();
        this.aspect = laiRequestDTO.aspect();
        this.impact = laiRequestDTO.impact();
        this.temporality = laiRequestDTO.temporality();
        this.description = laiRequestDTO.description();
        this.cycle = laiRequestDTO.cycle();
        this.frequency = laiRequestDTO.frequency();
        this.damage = laiRequestDTO.damage();
        this.stateHolder = laiRequestDTO.stateHolder();
        this.legislation = laiRequestDTO.legislation();
        this.meaningfulness = laiRequestDTO.meaningfulness();
        this.typeOfControl = laiRequestDTO.typeOfControl();
        this.descriptionOfControl = laiRequestDTO.descriptionOfControl();
        this.dateOfRevision = laiRequestDTO.dateOfRevision() != null ? laiRequestDTO.dateOfRevision() : LocalDate.now();
        this.userId = laiRequestDTO.userId();
    }
}
