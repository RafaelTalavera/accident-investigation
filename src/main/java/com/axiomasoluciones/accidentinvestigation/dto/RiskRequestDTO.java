package com.axiomasoluciones.accidentinvestigation.dto;

import java.time.LocalDate;

public record RiskRequestDTO(
        String organization,
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
        LocalDate dateOfRevision,
        String userId
) {
}
