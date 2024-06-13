package com.axiomasoluciones.accidentinvestigation.dto.request;


import com.axiomasoluciones.accidentinvestigation.models.entity.util.enums.security.Role;
import org.springframework.data.mongodb.core.index.Indexed;

public record UserRequestDTO(
        String username,
        String password,
        @Indexed(unique = true)
        String email,
        String phone,
        String fullname,
        String firebase,
        Role role

) {

    public UserRequestDTO(UserRequestDTO userRequestDTO) {
        this(
                userRequestDTO.username(),
                userRequestDTO.email(),
                userRequestDTO.password(),
                userRequestDTO.phone(),
                userRequestDTO.fullname(),
                userRequestDTO.firebase(),
                Role.ADMINISTRATOR
        );
    }

}