package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.Risk;

import java.time.LocalDate;

public record RiskResponseDTO(
        String id,
        String puesto,
        String area,
        String sector,
        String tarea,
        String fuente,
        String incidentesPotenciales,
        String consecuencia,
        String tActividad,
        String probabilidad,
        String severidad,
        String evaluacion,
        String clasificaRiesgo,
        String ClasificaMC,
        String medidaControl,
        String newControl,
        LocalDate date,
        String userId
) {
    public RiskResponseDTO(Risk risk) {
        this(
                risk.getId(),
                risk.getPuesto(),
                risk.getArea(),
                risk.getSector(),
                risk.getTarea(),
                risk.getFuente(),
                risk.getIncidentesPotenciales(),
                risk.getConsecuencia(),
                risk.getTActividad(),
                risk.getProbabilidad(),
                risk.getSeveridad(),
                risk.getEvaluacion(),
                risk.getClasificaRiesgo(),
                risk.getClasificaMC(),
                risk.getMedidaControl(),
                risk.getNewControl(),
                risk.getDate(),
                risk.getUserId()
        );
    }
}
