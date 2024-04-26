package com.axiomasoluciones.accidentinvestigation.models.entity;

import com.axiomasoluciones.accidentinvestigation.dto.request.ExtinguisherRequestDTO;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Extinguisher implements Serializable  {

    @Id
    private String id;
    private LocalDate date;
    private String organizationId;
    private String nameOrganization;
    private String sector;
    private String extId;
    private String tipo;
    private Double kg;
    private LocalDate vencimiento;
    private Boolean access;
    private Boolean signaling;
    private Boolean presion;
    private String observaciones;
    private Boolean enabled;
    private String userId;

    @Serial
    private static final long serialVersionUID = 1L;

    public Extinguisher(ExtinguisherRequestDTO extinguisherRequestDTO){
        this.date = extinguisherRequestDTO.date();
        this.organizationId = extinguisherRequestDTO.organizationId();
        this.nameOrganization = extinguisherRequestDTO.nameOrganization();
        this.sector = extinguisherRequestDTO.sector();
        this.extId = extinguisherRequestDTO.extId();
        this.tipo = extinguisherRequestDTO.tipo();
        this.kg = extinguisherRequestDTO.kg();
        this.vencimiento = extinguisherRequestDTO.vencimiento();
        this.access = extinguisherRequestDTO.access();
        this.signaling = extinguisherRequestDTO.signaling();
        this.presion = extinguisherRequestDTO.presion();
        this.observaciones = extinguisherRequestDTO.observaciones();
        this.enabled = extinguisherRequestDTO.enabled();
        this.userId = extinguisherRequestDTO.userId();

    }

    public boolean estaVigente() {
        LocalDate fechaActual = LocalDate.now();
        return vencimiento.isAfter(fechaActual);
    }

    public int calcularDiferenciaEnDias() {
        LocalDate fechaActual = LocalDate.now();
        return (int) ChronoUnit.DAYS.between(fechaActual, vencimiento);
    }

    public boolean isEnabled() {
        return enabled;
    }
}
