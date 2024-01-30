package com.axiomasoluciones.accidentinvestigation.controllers;

import com.axiomasoluciones.accidentinvestigation.dto.UserRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.UserResponseDTO;
import com.axiomasoluciones.accidentinvestigation.models.entity.User;
import com.axiomasoluciones.accidentinvestigation.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO data){

        User newUser = new User(data);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        userService.createUser(newUser);

        UserResponseDTO userResponseDTO = new UserResponseDTO(newUser);

        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);

    }

}
