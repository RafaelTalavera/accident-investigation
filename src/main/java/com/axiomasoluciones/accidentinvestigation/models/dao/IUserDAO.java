package com.axiomasoluciones.accidentinvestigation.models.dao;

import com.axiomasoluciones.accidentinvestigation.models.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IUserDAO extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    Optional<User> findUserByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
