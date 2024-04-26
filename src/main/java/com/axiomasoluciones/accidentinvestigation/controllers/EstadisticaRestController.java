package com.axiomasoluciones.accidentinvestigation.controllers;

import com.axiomasoluciones.accidentinvestigation.dto.request.EstadisticaRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.response.EstadisticaResponseDTO;
import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.entity.AccidentMonthlySummary;
import com.axiomasoluciones.accidentinvestigation.models.entity.Accidents;
import com.axiomasoluciones.accidentinvestigation.models.entity.Estadistica;
import com.axiomasoluciones.accidentinvestigation.models.entity.EstadisticaMonthlySummary;
import com.axiomasoluciones.accidentinvestigation.services.IEstadisticaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/statistcs")
public class EstadisticaRestController {

    @Autowired
    private IEstadisticaService service;

    @GetMapping
    public ResponseEntity<List<EstadisticaResponseDTO>> getAll() {
        List<Estadistica> statistics = service.findAll();
        if (!statistics.isEmpty()) {
            List<EstadisticaResponseDTO> statisticsResponseDTOS = statistics
                    .stream().map(EstadisticaResponseDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(statisticsResponseDTOS, HttpStatus.OK);
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<EstadisticaResponseDTO>> getAll(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String userEmail = service.extractUserEmailFromToken(token);
            List<Estadistica> estadisticas = service.findByUserId(userEmail);
            if (!estadisticas.isEmpty()) {
                List<EstadisticaResponseDTO> estadisticaResponseDTOS = estadisticas.stream()
                        .map(EstadisticaResponseDTO::new).collect(Collectors.toList());
                return new ResponseEntity<>(estadisticaResponseDTOS, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<EstadisticaResponseDTO> createEstadistica(
            @RequestBody EstadisticaRequestDTO data, HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        String userEmail = service.extractUserEmailFromToken(token);
        Estadistica newEstadistica = new Estadistica(data);
        newEstadistica.setUserId(userEmail);
        service.save(newEstadistica);
        EstadisticaResponseDTO estadisticaResponseDTO = new EstadisticaResponseDTO(newEstadistica);
        return new ResponseEntity<>(estadisticaResponseDTO, HttpStatus.CREATED);
    }


    @GetMapping("/summary")
    public ResponseEntity<List<EstadisticaMonthlySummary>> getMonthlyAccidentSummary(@RequestParam String nameOrganization) {
        List<EstadisticaMonthlySummary> monthlySummary = service.getMonthlyEstadisticaSummary(nameOrganization);
        return new ResponseEntity<>(monthlySummary, HttpStatus.OK);
    }


}
