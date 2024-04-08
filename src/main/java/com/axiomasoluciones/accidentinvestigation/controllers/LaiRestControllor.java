package com.axiomasoluciones.accidentinvestigation.controllers;

import com.axiomasoluciones.accidentinvestigation.dto.LaiRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.LaiResponseDTO;
import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.entity.Lai;
import com.axiomasoluciones.accidentinvestigation.services.ILaiSevice;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/lai")
public class LaiRestControllor {

    @Autowired
    private ILaiSevice laiSevice;

    @GetMapping
    public ResponseEntity<List<LaiResponseDTO>> getAll() {
        List<Lai> lais = laiSevice.findAll();
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
            String userEmail = laiSevice.extractUserEmailFromToken(token);
            List<Lai> lais = laiSevice.findByUserId(userEmail);
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

    @PostMapping
    public ResponseEntity<LaiResponseDTO> createLai(
            @RequestBody LaiRequestDTO data, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String userEmail = laiSevice.extractUserEmailFromToken(token);

        Lai newLai = new Lai(data);
        newLai.setUserId(userEmail);

        laiSevice.save(newLai);
        LaiResponseDTO laiResponseDTO = new LaiResponseDTO(newLai);
        return new ResponseEntity<>(laiResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<LaiResponseDTO> editLai(@PathVariable String id,
                                                  @RequestBody LaiRequestDTO requestDTO
            , HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String userEmail = laiSevice.extractUserEmailFromToken(token);


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

            Lai updateLai = laiSevice.editLai(id, editLai);
            LaiResponseDTO responseDTO = new LaiResponseDTO(updateLai);
            return ResponseEntity.ok(responseDTO);
        } catch (RegistroNoEncontradoException e) {
            return ResponseEntity.notFound().build();

        }
    }

    @GetMapping("/countTypeOfControl")
    public ResponseEntity<Map<String, Integer>> counttypeOfControlByOrganizationAndArea(
            @RequestParam String organization,
            @RequestParam String area) {

        Map<String, Integer> countMap = laiSevice.countTypeOfControlByOrganizationAndArea(organization, area);

        return new ResponseEntity<>(countMap, HttpStatus.OK);
    }

    @GetMapping("/countMeaningfulness")
    public ResponseEntity<Map<String, Map<String, Integer>>> countOrganizationAndMeaningfulnessByArea(
            @RequestParam String organization,
            @RequestParam String area
    ) {
        Map<String, Map<String, Integer>> countMap = laiSevice.countMeaningfulnessByOrganizationAndArea(organization, area);
   return new ResponseEntity<>(countMap, HttpStatus.OK);
    }

    @GetMapping("/organizations")
    public ResponseEntity<List<String>> getDistinctOrganizations() {
        List<String> organizations = laiSevice.findDistinctOrganization()
                .stream()
                .map(Lai::getOrganization)
                .distinct()
                .collect(Collectors.toList());
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @GetMapping("/areas/{organization}")
    public ResponseEntity<List<String>> getDistinctAreasByOrganization(@PathVariable String organization) {
        List<Lai> lais = laiSevice.findDistinctAreaByOrganization(organization);

        List<String> distinctAreas = lais.stream()
                .map(Lai::getArea)
                .distinct()
                .collect(Collectors.toList());
        return new ResponseEntity<>(distinctAreas, HttpStatus.OK);
    }


}
