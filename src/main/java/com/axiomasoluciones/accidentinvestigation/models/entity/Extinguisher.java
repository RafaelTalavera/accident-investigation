package com.axiomasoluciones.accidentinvestigation.models.entity;

import com.axiomasoluciones.accidentinvestigation.dto.ExtinguisherRequestDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Extinguisher {

    @Id
    private String id;
    private LocalDate date;
    private String empresa;
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

    public Extinguisher(ExtinguisherRequestDTO extinguisherRequestDTO){
        this.date = extinguisherRequestDTO.date();
        this.empresa = extinguisherRequestDTO.empresa();
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
