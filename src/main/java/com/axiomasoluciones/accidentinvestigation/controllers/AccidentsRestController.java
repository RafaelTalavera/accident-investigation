package com.axiomasoluciones.accidentinvestigation.controllers;

import com.axiomasoluciones.accidentinvestigation.dto.request.AccidentsRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.response.AccidentsResponseDTO;
import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.entity.Accidents;
import com.axiomasoluciones.accidentinvestigation.models.entity.AccidentMonthlySummary;
import com.axiomasoluciones.accidentinvestigation.services.IAccidentsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/accidents")
public class AccidentsRestController {

    @Autowired
    private IAccidentsService service;


    @GetMapping
    public ResponseEntity<List<AccidentsResponseDTO>> getAll() {
        List<Accidents> accidents = service.findAll();
        if (!accidents.isEmpty()) {
            List<AccidentsResponseDTO> accidentsResponseDTOS = accidents.stream()
                    .map(accident -> {
                        long lostDay = service.calcularLostDay(accident); // Calcula los días perdidos
                        return new AccidentsResponseDTO(accident, lostDay);
                    })
                    .collect(Collectors.toList());
            return new ResponseEntity<>(accidentsResponseDTOS, HttpStatus.OK);
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
        }
    }



    @GetMapping("/list")
    public ResponseEntity<List<AccidentsResponseDTO>> getAll(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String userEmail = service.extractUserEmailFromToken(token);

            List<Accidents> accidents = service.findAll();

            if (!accidents.isEmpty()) {
                List<AccidentsResponseDTO> accidentsResponseDTOS = accidents.stream()
                        .map(accident -> {
                            long lostDay = service.calcularLostDay(accident); // Calcula los días perdidos
                            return new AccidentsResponseDTO(accident, lostDay);
                        })
                        .collect(Collectors.toList());
                return new ResponseEntity<>(accidentsResponseDTOS, HttpStatus.OK);
            } else {
                throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<AccidentsResponseDTO> createAccidents
            (@RequestBody AccidentsRequestDTO data, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String userEmail = service.extractUserEmailFromToken(token);

        Accidents newAccidents = new Accidents(data);
        newAccidents.setUserId(userEmail);

        service.save(newAccidents);
        AccidentsResponseDTO accidetsResponseDTO = new AccidentsResponseDTO(newAccidents);
        return new ResponseEntity<>(accidetsResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/summary")
    public ResponseEntity<List<AccidentMonthlySummary>> getMonthlyAccidentSummary(@RequestParam String nameOrganization) {
        List<AccidentMonthlySummary> monthlySummary = service.getMonthlyAccidentSummary(nameOrganization);
        return new ResponseEntity<>(monthlySummary, HttpStatus.OK);
    }

    @PutMapping("/{id}/dateAlta")
    public Accidents editedAccidents(@PathVariable String id, @RequestBody Accidents editedAccidents){
        return service.editAccidents(id, editedAccidents);
    }

}



