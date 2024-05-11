package com.axiomasoluciones.accidentinvestigation.dto.request;



public record ExtinguisherDistributionDTO(
        String nameOrganization,
        String sector,
        int total,
        int vigentes,
        int vencidos,
        int habilitados,
        int deshabilitados

) {}
