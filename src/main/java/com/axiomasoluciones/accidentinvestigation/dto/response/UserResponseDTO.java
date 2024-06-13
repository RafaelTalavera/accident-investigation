package com.axiomasoluciones.accidentinvestigation.dto.response;

import com.axiomasoluciones.accidentinvestigation.models.entity.User;

import com.axiomasoluciones.accidentinvestigation.models.entity.util.enums.security.Role;
import org.springframework.data.mongodb.core.index.Indexed;

public record UserResponseDTO(
        String id,
        String username,
        String password,
        @Indexed(unique = true)
        String email,
        String phone,
        String fullname,
        String firebase,

        Role role
) {
    public UserResponseDTO(User user){
        this(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getPhone(),
                user.getFullname(),
                user.getFirebase(),
                Role.ADMINISTRATOR);
    }
}