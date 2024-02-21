package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.Risk;

public record RiskResponseDTO(
        String id,
        String proceso,
        String puestoTrabajo,
        Integer nTrabajadores,
        String fuente,
        String actividad,
        String description,
        String incidentesPotenciales,
        String medidaControl,
        String probabilidad,
        String severidad,
        String evaluacion,
        String nivelRiesgo,
        String newControl,
        String userId
) {
    public RiskResponseDTO(Risk risk) {
        this(
                risk.getId(),
                risk.getProceso(),
                risk.getPuestoTrabajo(),
                risk.getNTrabajadores(),
                risk.getFuente(),
                risk.getActividad(),
                risk.getDescription(),
                risk.getIncidentesPotenciales(),
                risk.getMedidaControl(),
                risk.getProbabilidad(),
                risk.getSeveridad(),
                risk.getEvaluacion(),
                risk.getNivelRiesgo(),
                risk.getNewControl(),
                risk.getUserId()
        );
    }
}
