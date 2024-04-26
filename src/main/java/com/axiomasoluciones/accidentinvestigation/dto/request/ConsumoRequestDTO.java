package com.axiomasoluciones.accidentinvestigation.dto.request;

import java.time.LocalDate;

public record ConsumoRequestDTO(

        LocalDate date,
        String organizationId,
        String nameOrganization,
        String fuente,
        String tipoFuente,
        String combustible,
        String unidad,
        Double consumo,
        String userId
        ) {
}
