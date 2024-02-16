package com.axiomasoluciones.accidentinvestigation.controllers;

import com.axiomasoluciones.accidentinvestigation.dto.EventRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.EventResponseDTO;
import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.entity.*;
import com.axiomasoluciones.accidentinvestigation.services.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/list")
    public ResponseEntity<List<EventResponseDTO>> getAll(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String userEmail = eventService.extractUserEmailFromToken(token);

            List<Event> events = eventService.findByUserId(userEmail);

            if (!events.isEmpty()) {
                List<EventResponseDTO> eventResponseDTOS = events.stream()
                        .map(EventResponseDTO::new).collect(Collectors.toList());
                return new ResponseEntity<>(eventResponseDTOS, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/causa")
    public ResponseEntity<String> getCausaById(@PathVariable String id) {
        try {
            String causa = eventService.getCausa(id);
            return new ResponseEntity<>(causa, HttpStatus.OK);
        } catch (RegistroNoEncontradoException e) {
            return new ResponseEntity<>("Registro no encontrado: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno al obtener la causa del evento: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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



    @PreAuthorize("permitAll")
        @PostMapping
        public ResponseEntity<EventResponseDTO> createEvent
                (@RequestBody EventRequestDTO data, HttpServletRequest request ){
            String token = request.getHeader("Authorization");
            String userEmail = eventService.extractUserEmailFromToken(token);

            Event newEvent = new Event(data);
            newEvent.setUserId(userEmail);

            eventService.save(newEvent);
            EventResponseDTO eventResponseDTO = new EventResponseDTO(newEvent);
            return new ResponseEntity<>(eventResponseDTO, HttpStatus.CREATED);
        }


    }





