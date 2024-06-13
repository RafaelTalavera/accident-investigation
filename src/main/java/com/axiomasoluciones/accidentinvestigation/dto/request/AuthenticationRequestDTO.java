package com.axiomasoluciones.accidentinvestigation.dto.request;

public record AuthenticationRequestDTO(
        String username,
        String mail,
        String password
) {
}