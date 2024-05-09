package com.axiomasoluciones.accidentinvestigation.dto.response;

import com.axiomasoluciones.accidentinvestigation.models.entity.User;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.security.Role;

public record UserResponseDTO(
        String id,
        String username,
        String password,
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
