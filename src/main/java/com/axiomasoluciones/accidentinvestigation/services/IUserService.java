package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.dto.UserRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.UserResponseDTO;
import com.axiomasoluciones.accidentinvestigation.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public List<User> findAll();

    Optional<User> findById(String id);

    User createUser(User user);

    public void delete(User user);
}
