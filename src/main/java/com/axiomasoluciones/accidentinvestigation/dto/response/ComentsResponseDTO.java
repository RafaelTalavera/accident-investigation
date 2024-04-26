package com.axiomasoluciones.accidentinvestigation.dto.response;

import com.axiomasoluciones.accidentinvestigation.models.entity.Coments;

public record ComentsResponseDTO(
        String id,
        String texto,
        String userId
) {
    public ComentsResponseDTO(Coments coments){
        this(coments.getId(),
                coments.getTexto(),
                coments.getUserId()
                );
    }
}
