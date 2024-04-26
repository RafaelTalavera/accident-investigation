package com.axiomasoluciones.accidentinvestigation.models.entity;

import com.axiomasoluciones.accidentinvestigation.dto.request.AccidentsRequestDTO;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Accidents {
    @Id
    private String id;
    private String organizationId;
    private String nameOrganization;
    private String puesto;
    private String area;
    private String name;
    private String severity;
    private LocalDate dateEvent;
    private int monthEvent;
    private int yearEvent;
    private LocalDate dateAlta;
    private boolean alta;
    private String userId;

    public Accidents(AccidentsRequestDTO accidentsRequestDTO){
        this.organizationId = accidentsRequestDTO.organizationId();
        this.nameOrganization = accidentsRequestDTO.nameOrganization();
        this.puesto = accidentsRequestDTO.puesto();
        this.area = accidentsRequestDTO.area();
        this.name = accidentsRequestDTO.name();
        this.severity = accidentsRequestDTO.severity();
        this.dateEvent = accidentsRequestDTO.dateEvent();
        this.dateAlta = accidentsRequestDTO.dateAlta();
        this.alta = accidentsRequestDTO.dateAlta() != null;
        this.userId = accidentsRequestDTO.userId();
        if (dateEvent != null) {
            this.monthEvent = dateEvent.getMonthValue();
        }
        if (dateEvent != null) {
            this.yearEvent = dateEvent.getYear();
        }
    }
}
