package com.axiomasoluciones.accidentinvestigation.controllers;


import com.axiomasoluciones.accidentinvestigation.dto.EventRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.EventResponseDTO;
import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.entity.*;
import com.axiomasoluciones.accidentinvestigation.services.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/events")
    public class EventRestController {

        @Autowired
        private IEventService eventService;

        @Autowired
        private EventServiceImplements eventServiceImplements;

        @Value("${security.jwt.secret-key}")
        private String SECRET_KEY;


        @GetMapping
        public ResponseEntity<List<EventResponseDTO>> getAll(){
            List<Event> events = eventService.findAll();
            if (!((List<?>) events).isEmpty()){
                List<EventResponseDTO> eventResponseDTOS = events.stream()
                        .map(EventResponseDTO::new).collect(Collectors.toList());
                return new ResponseEntity<>(eventResponseDTOS, HttpStatus.OK);
            } else {
                throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
            }
        }

    @GetMapping("/{id}/antiguedad-message")
    public ResponseEntity<String> getAntiguedadMessageById(@PathVariable String id) {
        try {
            Optional<Event> optionalEvent = eventService.findById(id);

            if (optionalEvent.isPresent()) {
                Event event = optionalEvent.get();
                String antiguedadMessage = eventServiceImplements.getAntiguedadMessageForEvent(event);
                return ResponseEntity.ok(antiguedadMessage);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Manejar cualquier excepción y devolver un ResponseEntity con un mensaje de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el mensaje de antigüedad.");
        }
    }

        @GetMapping("/{id}")
        public ResponseEntity<EventResponseDTO> getById(@PathVariable String id){
            Optional<Event> optionalEvent = eventService.findById(id);

            if (optionalEvent.isPresent()){
                Event event = optionalEvent.get();
                EventResponseDTO eventResponseDTO = new EventResponseDTO(event);
                return new ResponseEntity<>(eventResponseDTO, HttpStatus.OK);
            } else {
                throw new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> delete(@PathVariable String id){
            eventService.deleteById(id);
            return new ResponseEntity<>("Registro eliminado correctamente", HttpStatus.OK);
        }

        @PreAuthorize("hasAuthority('SAVE_ONE_ITEMS')")
        @PostMapping
        public ResponseEntity<EventResponseDTO> createEvent
                (@RequestBody EventRequestDTO data, HttpServletRequest request ){

            String token = request.getHeader("Authorization");

            System.out.println(token);

            String userEmail = extractUserEmailFromToken(token);

            Event newEvent = new Event(data);
            newEvent.setUserId(userEmail);
            eventService.save(newEvent);
            EventResponseDTO eventResponseDTO = new EventResponseDTO(newEvent);
            return new ResponseEntity<>(eventResponseDTO, HttpStatus.CREATED);
        }

    private String extractUserEmailFromToken(String token) {
        try {
            // Remover la palabra "Bearer " del inicio del token
            String jwtToken = token.replace("Bearer ", "");

            // Decodificar el token JWT
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken).getBody();

            System.out.println(claims);
            return claims.get("mail", String.class);
        } catch (Exception e) {
            // Manejar la excepción según tus necesidades
            throw new RuntimeException("Error al extraer el correo electrónico del token", e);
        }
    }



    @GetMapping("/{id}/case1-message")
    public ResponseEntity<String> getCase1MessageById(@PathVariable String id) {
        try {
            Optional<Event> optionalEvent = eventService.findById(id);

            if (optionalEvent.isPresent()) {
                Event event = optionalEvent.get();
                String case1Message = eventServiceImplements.getCase1ById(id);
                return ResponseEntity.ok(case1Message);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle any exception and return a ResponseEntity with an error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el mensaje del caso 1.");
        }
    }

}

