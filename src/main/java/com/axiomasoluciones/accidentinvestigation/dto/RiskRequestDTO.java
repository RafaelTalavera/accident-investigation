package com.axiomasoluciones.accidentinvestigation.dto;

import java.time.LocalDate;

public record RiskRequestDTO(
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
}
