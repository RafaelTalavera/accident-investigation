package com.axiomasoluciones.accidentinvestigation.dto.response;

import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;

public record WorkerResponseDTO(
        String id,
        String organizationId,
        String nameOrganization,
        String name,
        String lastname,
        String dni,
        byte[] fingerPrint,
        String userId
) {
    public WorkerResponseDTO(Worker worker){
        this(worker.getId(),
                worker.getOrganizationId(),
                worker.getNameOrganization(),
                worker.getName(),
                worker.getLastname(),
                worker.getDni(),
                worker.getFingerPrint(),
                worker.getUserId());
    }
}
