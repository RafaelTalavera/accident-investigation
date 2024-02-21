package com.axiomasoluciones.accidentinvestigation.dto;

public record RiskRequestDTO(
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
}
