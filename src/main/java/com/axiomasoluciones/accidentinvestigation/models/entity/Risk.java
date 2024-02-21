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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Risk implements Serializable  {

    @Id
    private String id;
    private String proceso;
    private String puestoTrabajo;
    private Integer nTrabajadores;
    private String fuente;
    private String actividad;
    private String description;
    private String incidentesPotenciales;
    private String medidaControl;
    private String probabilidad;
    private String severidad;
    private String evaluacion;
    private String nivelRiesgo;
    private String newControl;
    private String userId;

    @Serial
    private static final long serialVersionUID = 1L;
public Risk(RiskRequestDTO riskRequestDTO){
    this.proceso = riskRequestDTO.proceso();
    this.puestoTrabajo = riskRequestDTO.puestoTrabajo();
    this.nTrabajadores = riskRequestDTO.nTrabajadores();
    this.fuente = riskRequestDTO.fuente();
    this.actividad = riskRequestDTO.actividad();
    this.description = riskRequestDTO.description();
    this.incidentesPotenciales = riskRequestDTO.incidentesPotenciales();
    this.medidaControl = riskRequestDTO.medidaControl();
    this.probabilidad = riskRequestDTO.probabilidad();
    this.severidad = riskRequestDTO.severidad();
    this.evaluacion = riskRequestDTO.evaluacion();
    this.nivelRiesgo = riskRequestDTO.nivelRiesgo();
    this.newControl = riskRequestDTO.newControl();
    this.userId = riskRequestDTO.userId();
}

}
