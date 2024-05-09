package com.axiomasoluciones.accidentinvestigation.models.entity;

import com.axiomasoluciones.accidentinvestigation.dto.request.RiskRequestDTO;
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
    private String organizationId;
    private String nameOrganization;
    private String puesto;
    private String area;
    private String tarea;
    private String incidentesPotenciales;
    private String consecuencia;
    private String tipo;
    private int probabilidad;
    private int gravedad;
    private String evaluacion;
    private String clasificaMC;
    private String medidaControl;
    private LocalDate date;
    private LocalDate dateOfRevision;
    private String userId;

    @Serial
    private static final long serialVersionUID = 1L;

public Risk(RiskRequestDTO riskRequestDTO){
    this.puesto = riskRequestDTO.puesto();
    this.organizationId = riskRequestDTO.organizationId();
    this.nameOrganization = riskRequestDTO.nameOrganization();
    this.area = riskRequestDTO.area();
    this.tarea = riskRequestDTO.tarea();
    this.incidentesPotenciales = riskRequestDTO.incidentesPotenciales();
    this.consecuencia = riskRequestDTO.consecuencia();
    this.tipo = riskRequestDTO.tipo();
    this.probabilidad = riskRequestDTO.probabilidad();
    this.gravedad = riskRequestDTO.gravedad();
    this.evaluacion = riskRequestDTO.evaluacion();
    this.clasificaMC = riskRequestDTO.clasificaMC();
    this.medidaControl = riskRequestDTO.medidaControl();
    this.date = riskRequestDTO.date();
    this.dateOfRevision = riskRequestDTO.dateOfRevision() != null ? riskRequestDTO.dateOfRevision() : LocalDate.now();
    this.userId = riskRequestDTO.userId();
}

}
