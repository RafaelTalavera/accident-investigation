package com.axiomasoluciones.accidentinvestigation.controllers;

import com.axiomasoluciones.accidentinvestigation.dto.request.LaiRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.response.LaiResponseDTO;

import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.entity.Lai;

import com.axiomasoluciones.accidentinvestigation.services.ILaiSevice;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/lai")
public class LaiRestControllor {

    @Autowired
    private ILaiSevice service;

    @GetMapping
    public ResponseEntity<List<LaiResponseDTO>> getAll() {
        List<Lai> lais = service.findAll();
        if (!((List<?>) lais).isEmpty()) {
            List<LaiResponseDTO> laiResponseDTOS = lais.stream().map(LaiResponseDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(laiResponseDTOS, HttpStatus.OK);
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<LaiResponseDTO>> getAll(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String userEmail = service.extractUserEmailFromToken(token);
            List<Lai> lais = service.findByUserId(userEmail);
            if (!lais.isEmpty()) {
                List<LaiResponseDTO> laiResponseDTOS = lais.stream().map(LaiResponseDTO::new).collect(Collectors.toList());
                return new ResponseEntity<>(laiResponseDTOS, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("permitAll")
    @GetMapping("/organization/{name}")
    public ResponseEntity<List<LaiResponseDTO>> getLaisByUserAndOrganization(
            HttpServletRequest request,
            @PathVariable("name") String nameOrganization) {
        try {
            String token = request.getHeader("Authorization");
            String userId = service.extractUserEmailFromToken(token);

            List<Lai> lais = service.findLaiByUserIdAndNameOrganization(userId, nameOrganization);
            if (!lais.isEmpty()) {

                List<LaiResponseDTO> laiResponseDTOS = lais.stream()
                        .map(LaiResponseDTO::new)
                        .collect(Collectors.toList());
                return new ResponseEntity<>(laiResponseDTOS, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<LaiResponseDTO> createLai(
            @RequestBody LaiRequestDTO data, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String userEmail = service.extractUserEmailFromToken(token);

        Lai newLai = new Lai(data);
        newLai.setUserId(userEmail);

        service.save(newLai);
        LaiResponseDTO laiResponseDTO = new LaiResponseDTO(newLai);
        return new ResponseEntity<>(laiResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<LaiResponseDTO> editLai(@PathVariable String id,
                                                  @RequestBody LaiRequestDTO requestDTO
            , HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String userEmail = service.extractUserEmailFromToken(token);


        try {
            Lai editLai = new Lai();
            editLai.setUserId(userEmail);
            editLai.setActivity(requestDTO.activity());
            editLai.setDescription(requestDTO.description());
            editLai.setFrequency(requestDTO.frequency());
            editLai.setDamage(requestDTO.damage());
            editLai.setStateHolder(requestDTO.stateHolder());
            editLai.setLegislation(requestDTO.legislation());
            editLai.setDescriptionOfControl(requestDTO.descriptionOfControl());
            editLai.setDateOfRevision(requestDTO.dateOfRevision());

            Lai updateLai = service.editLai(id, editLai);
            LaiResponseDTO responseDTO = new LaiResponseDTO(updateLai);
            return ResponseEntity.ok(responseDTO);
        } catch (RegistroNoEncontradoException e) {
            return ResponseEntity.notFound().build();

        }
    }

    @GetMapping("/countTypeOfControl")
    public ResponseEntity<Map<String, Integer>> counttypeOfControlByOrganizationAndArea(
            @RequestParam String nameOrganization,
            @RequestParam String area, HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        String userEmail = service.extractUserEmailFromToken(token);
        try {
            Map<String, Integer> countMap = service.countTypeOfControlByNameOrganizationAndArea(nameOrganization, area);

            return new ResponseEntity<>(countMap, HttpStatus.OK);
        } catch (RegistroNoEncontradoException e) {
            return ResponseEntity.notFound().build();

        }
    }

    @GetMapping("/countMeaningfulness")
    public ResponseEntity<Map<String, Map<String, Integer>>> countNameOrganizationAndMeaningfulnessByArea(
            @RequestParam String nameOrganization,
            @RequestParam String area, HttpServletRequest request
    ) {

        String token = request.getHeader("Authorization");
        String userEmail = service.extractUserEmailFromToken(token);
        try {
            Map<String, Map<String, Integer>> countMap = service.countMeaningfulnessByNameOrganizationAndArea(nameOrganization, area);
            return new ResponseEntity<>(countMap, HttpStatus.OK);
        } catch (RegistroNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nameOrganizations")
    public ResponseEntity<List<String>> getDistinctNameOrganizations(HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        String userId = service.extractUserEmailFromToken(token);

        try {
            List<String> organizations = service.findDistinctOrganizationByUserId(userId)
                    .stream()
                    .map(Lai::getNameOrganization)
                    .distinct()
                    .collect(Collectors.toList());
            return new ResponseEntity<>(organizations, HttpStatus.OK);
        } catch (RegistroNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/areas/{nameOrganization}")
    public ResponseEntity<List<String>> getDistinctAreasByNameOrganization(
            @PathVariable String nameOrganization,
            HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String userEmail = service.extractUserEmailFromToken(token);
        try {
            List<Lai> lais = service.findDistinctAreaByNameOrganization(nameOrganization);

            List<String> distinctAreas = lais.stream()
                    .map(Lai::getArea)
                    .distinct()
                    .collect(Collectors.toList());
            return new ResponseEntity<>(distinctAreas, HttpStatus.OK);

        } catch (RegistroNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

