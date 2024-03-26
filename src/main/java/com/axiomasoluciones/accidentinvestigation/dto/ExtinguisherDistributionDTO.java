package com.axiomasoluciones.accidentinvestigation.dto;



public record ExtinguisherDistributionDTO(
        String sector,
        int total,
        int vigentes,
        int vencidos,
        int habilitados,
        int deshabilitados

) {}
