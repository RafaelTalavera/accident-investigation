package com.axiomasoluciones.accidentinvestigation.controllers;

import com.axiomasoluciones.accidentinvestigation.dto.RiskRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.RiskResponseDTO;
import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.entity.Risk;
import com.axiomasoluciones.accidentinvestigation.services.IRiskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/risk")
public class RiskController {

    @Autowired
    private IRiskService riskService;

    @GetMapping
    public ResponseEntity<List<RiskResponseDTO>> getAll() {
        List<Risk> risks = riskService.findAll();
        if (!((List<?>) risks).isEmpty()) {
            List<RiskResponseDTO> riskReponseDTOS = risks.stream()
                    .map(RiskResponseDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(riskReponseDTOS, HttpStatus.OK);
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<RiskResponseDTO>> getAll(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String userEmail = riskService.extractUserEmailFromToken(token);
            List<Risk> risks = riskService.findByUserId(userEmail);
            if (!risks.isEmpty()) {
                List<RiskResponseDTO> riskResponseDTOS = risks.stream()
                        .map(RiskResponseDTO::new).collect(Collectors.toList());
                return new ResponseEntity<>(riskResponseDTOS, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("permitAll")
    @PostMapping
    public ResponseEntity<RiskResponseDTO> createRisk(
            @RequestBody RiskRequestDTO data , HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String userEmail = riskService.extractUserEmailFromToken(token);

        Risk newRisk = new Risk(data);
        newRisk.setUserId(userEmail);

        riskService.save(newRisk);
        RiskResponseDTO riskResponseDTO = new RiskResponseDTO(newRisk);
        return new ResponseEntity<>(riskResponseDTO, HttpStatus.CREATED);
    }
}
