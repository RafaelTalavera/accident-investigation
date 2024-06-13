package com.axiomasoluciones.accidentinvestigation.dto.request;

public record WorkerRequestDTO(

        String organizationId,
        String nameOrganization,
        String name,
        String lastname,
        String dni,
        byte[] fingerPrint,
        String userId
        ) {
}
