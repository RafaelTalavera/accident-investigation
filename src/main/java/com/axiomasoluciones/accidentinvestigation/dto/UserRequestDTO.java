package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.util.security.Role;

public record UserRequestDTO(
        String username,
        String password,
        String email,
        String phone,
        String fullname,
        Role role

) {

    public UserRequestDTO(UserRequestDTO userRequestDTO) {
        this(
                userRequestDTO.username(),
                userRequestDTO.email(),
                userRequestDTO.password(),
                userRequestDTO.phone(),
                userRequestDTO.fullname(),
                Role.ADMINISTRATOR
        );
    }

}
