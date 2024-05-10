package com.axiomasoluciones.accidentinvestigation.controllers;

import com.axiomasoluciones.accidentinvestigation.dto.request.UserRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.response.UserResponseDTO;
import com.axiomasoluciones.accidentinvestigation.models.entity.User;
import com.axiomasoluciones.accidentinvestigation.services.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private IUserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO data){

        User newUser = new User(data);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        service.createUser(newUser);
        UserResponseDTO userResponseDTO = new UserResponseDTO(newUser);

        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/firebase")
    public ResponseEntity<UserResponseDTO> createFirebase(@RequestBody UserRequestDTO data,
                                                          HttpServletRequest request){

        // Obtener el correo electrónico del usuario desde el token JWT
        String token = request.getHeader("Authorization");
        String email = service.extractUserEmailFromToken(token);


        // Buscar al usuario por su correo electrónico
        User user = service.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el correo electrónico: " + email));

        // Guardar el token de Firebase en el usuario
        user.setFirebase(data.firebase());

        // Actualizar el usuario en la base de datos
        User updatedUser = service.editeUser(user.getId(), user);


        // Crear la respuesta DTO
        UserResponseDTO responseDTO = new UserResponseDTO(updatedUser);

        return ResponseEntity.ok(responseDTO);
    }

}
