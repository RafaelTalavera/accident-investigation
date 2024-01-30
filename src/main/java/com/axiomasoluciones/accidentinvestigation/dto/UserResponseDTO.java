package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.User;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.security.Role;

public record UserResponseDTO(
        String id,
        String username,
        String password,
        String email,
        Role role
) {
    public UserResponseDTO(User user){
        this(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), Role.ADMINISTRATOR);
    }
}
