package com.axiomasoluciones.accidentinvestigation.dto;

public record AuthenticationRequestDTO(
        String username,
        String mail,
        String password
) {
}
