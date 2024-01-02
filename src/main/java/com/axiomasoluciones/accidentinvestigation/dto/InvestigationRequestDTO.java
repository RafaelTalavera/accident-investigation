package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.dto.util.*;

public record InvestigationRequestDTO(
        EventRequestDTO eventRequestDTO,
        OrganizationalRequestDTO organizationalRequestDTO,
        WorkerRequestDTO workerRequestDTO,
        WorkPlaceRequestDTO workPlaceRequestDTO,
        WorkEquimentRequestDTO workEquimentRequestDTO

) {
}
