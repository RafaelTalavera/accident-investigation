package com.axiomasoluciones.accidentinvestigation.models.entity;


import com.axiomasoluciones.accidentinvestigation.dto.request.EstadisticaRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Estadistica implements Serializable {

    @Id
    private String id;
    private String organizationId;
    private String nameOrganization;
    private int month;
    private int year;
    private int people;
    private int hours;
    private String userId;


    @Serial
    private static final long serialVersionUID = 1L;

    public Estadistica(EstadisticaRequestDTO requestDTO){
        this.organizationId = requestDTO.organizationId();
        this.nameOrganization = requestDTO.nameOrganization();
        this.month = requestDTO.month();
        this.year = requestDTO.year();
        this.people = requestDTO.people();
        this.hours = requestDTO.hours();
        this.userId = requestDTO.userId();
    }

}
