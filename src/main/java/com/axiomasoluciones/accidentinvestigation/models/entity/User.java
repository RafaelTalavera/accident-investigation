package com.axiomasoluciones.accidentinvestigation.models.entity;

import com.axiomasoluciones.accidentinvestigation.dto.request.UserRequestDTO;

import com.axiomasoluciones.accidentinvestigation.models.entity.util.enums.security.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "users")
public class User implements UserDetails {
    @Id
    private String id;
    private String username;
    private String password;

    @Indexed(unique = true)
    @Email(message = "El formato del correo electrónico no es válido")
    private String email;
    private String phone;
    private String fullname;
    private String firebase;

    //Esta anotación es para que en vez de guardar el valor ordinal traiga el nombre.
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(UserRequestDTO userRequestDTO) {
        this.username = userRequestDTO.username();
        this.email = userRequestDTO.email();
        this.password = userRequestDTO.password();
        this.phone = userRequestDTO.phone();
        this.fullname = userRequestDTO.fullname();
        this.role = userRequestDTO.role();
        this.firebase = userRequestDTO.firebase();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = role.getPermissions().stream()
                .map(permissionEmum -> new SimpleGrantedAuthority(permissionEmum.name()))
                .collect(Collectors.toList());

        return authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}