package com.axiomasoluciones.accidentinvestigation.models.entity;

import com.axiomasoluciones.accidentinvestigation.dto.RiskRequestDTO;
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
public class Risk implements Serializable  {

    @Id
    private String id;
    private String puesto;
    private String area;
    private String sector;
    private String tarea;
    private String fuente;
    private String incidentesPotenciales;
    private String consecuencia;
    private String tActividad;
    private String probabilidad;
    private String severidad;
    private String evaluacion;
    private String clasificaRiesgo;
    private String ClasificaMC;
    private String medidaControl;
    private String newControl;
    private LocalDate date;
    private String userId;

    @Serial
    private static final long serialVersionUID = 1L;



public Risk(RiskRequestDTO riskRequestDTO){
    this.puesto = riskRequestDTO.puesto();
    this.area = riskRequestDTO.area();
    this.sector = riskRequestDTO.sector();
    this.tarea = riskRequestDTO.tarea();
    this.fuente = riskRequestDTO.fuente();
    this.incidentesPotenciales = riskRequestDTO.incidentesPotenciales();
    this.consecuencia = riskRequestDTO.consecuencia();
    this.tActividad = riskRequestDTO.tActividad();
    this.probabilidad = riskRequestDTO.probabilidad();
    this.severidad = riskRequestDTO.severidad();
    this.evaluacion = riskRequestDTO.evaluacion();
    this.clasificaRiesgo = riskRequestDTO.clasificaRiesgo();
    this.ClasificaMC = riskRequestDTO.ClasificaMC();
    this.medidaControl = riskRequestDTO.medidaControl();
    this.newControl = riskRequestDTO.newControl();
    this.date = riskRequestDTO.date();
    this.userId = riskRequestDTO.userId();
}

}
