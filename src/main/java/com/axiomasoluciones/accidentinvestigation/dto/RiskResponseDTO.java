package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.Risk;

import java.time.LocalDate;

public record RiskResponseDTO(
        String id,
        String puesto,
        String area,
        String tarea,
        String fuente,
        String incidentesPotenciales,
        String consecuencia,
        String tipo,
        int probabilidad,
        int gravedad,
        String evaluacion,
        String clasificaMC,
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
                risk.getTarea(),
                risk.getFuente(),
                risk.getIncidentesPotenciales(),
                risk.getConsecuencia(),
                risk.getTipo(),
                risk.getProbabilidad(),
                risk.getGravedad(),
                risk.getEvaluacion(),
                risk.getClasificaMC(),
                risk.getMedidaControl(),
                risk.getNewControl(),
                risk.getDate(),
                risk.getUserId()
        );
    }
}
