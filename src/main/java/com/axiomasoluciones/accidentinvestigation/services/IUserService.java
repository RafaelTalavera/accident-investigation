package com.axiomasoluciones.accidentinvestigation.services;


import com.axiomasoluciones.accidentinvestigation.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public List<User> findAll();

    Optional<User> findById(String id);

    User createUser(User user);

    public void delete(User user);

    User editeUser(String id, User editedUser);

    String extractUserEmailFromToken(String token);

    Optional<User> findUserByEmail(String email);
}
