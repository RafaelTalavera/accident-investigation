package com.axiomasoluciones.accidentinvestigation.dto.request;



public record OrganizationRequestDTO(
        String name,
        String tipoOrga,
        String sector,
        Integer nEmpleados,
        Double superficie,
        String userId
) {
}
