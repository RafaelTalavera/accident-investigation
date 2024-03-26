package com.axiomasoluciones.accidentinvestigation.controllers;


import com.axiomasoluciones.accidentinvestigation.dto.ExtinguisherDistributionDTO;
import com.axiomasoluciones.accidentinvestigation.dto.ExtinguisherRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.ExtinguisherResponseDTO;
import com.axiomasoluciones.accidentinvestigation.models.entity.Extinguisher;
import com.axiomasoluciones.accidentinvestigation.services.IExtinguisherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/extinguishers")
public class ExtinguisherRestController {

    @Autowired
    private IExtinguisherService service;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> obtenerTodosLosExtinguishers() {
        List<Extinguisher> listaExtinguishers = service.findAll();

        if (!listaExtinguishers.isEmpty()) {
            List<Map<String, Object>> responseList = listaExtinguishers.stream()
                    .map(extinguisher -> {
                        Map<String, Object> response = new HashMap<>();
                        response.put("id", extinguisher.getId());

                        if (extinguisher.getDate() != null) {
                            response.put("date", extinguisher.getDate().toString());
                        } else {
                            response.put("date", null);
                        }
                        response.put("empresa", extinguisher.getEmpresa());
                        response.put("tipo", extinguisher.getTipo());
                        response.put("sector", extinguisher.getSector());

                        if (extinguisher.getExtId() != null) {
                            response.put("extId", extinguisher.getExtId());
                        } else {
                            response.put("extId", null);
                        }

                        if (extinguisher.getVencimiento() != null) {
                            response.put("vencimiento", extinguisher.getVencimiento().toString());
                        } else {
                            response.put("vencimiento", null);
                        }
                        response.put("kg", extinguisher.getKg());
                        response.put("access", extinguisher.getAccess());
                        response.put("signaling", extinguisher.getSignaling());
                        response.put("presion", extinguisher.getPresion());
                        response.put("vigente", extinguisher.estaVigente());
                        response.put("diferenciaEnDias", extinguisher.calcularDiferenciaEnDias());
                        response.put("observaciones", extinguisher.getObservaciones());
                        response.put("enabled", extinguisher.getEnabled());
                        response.put("userId", extinguisher.getUserId());
                        return response;
                    })
                    .collect(Collectors.toList());

            return new ResponseEntity<>(responseList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/changeEnabled")
    public ResponseEntity<ExtinguisherResponseDTO> changeEnabled(
            @PathVariable String id, @RequestBody Extinguisher extinguisher) {
        try {
            Optional<Extinguisher> optionalExtinguisher = service.findById(id);
            if (optionalExtinguisher.isPresent()) {
                Extinguisher existingExtinguisher = optionalExtinguisher.get();
                existingExtinguisher.setEnabled(extinguisher.getEnabled());
                service.save(existingExtinguisher);
                ExtinguisherResponseDTO responseDTO = new ExtinguisherResponseDTO("El extintor con ID " + id + " se modificó correctamente");
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                ExtinguisherResponseDTO responseDTO = new ExtinguisherResponseDTO("El extintor con ID " + id + " no se encontró.");
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ExtinguisherResponseDTO responseDTO = new ExtinguisherResponseDTO("Ocurrió un error interno al modificar el campo enabled del extintor.");
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ExtinguisherResponseDTO> createExtinguishers
            (@RequestBody ExtinguisherRequestDTO requestDTO, HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String userEmail = service.extractUserEmailFromToken(token);


            Extinguisher newExtinguisher = new Extinguisher(requestDTO);
            newExtinguisher.setUserId(userEmail);

            service.save(newExtinguisher);
            ExtinguisherResponseDTO extinguisherResponseDTO = new ExtinguisherResponseDTO(newExtinguisher);
            return new ResponseEntity<>(extinguisherResponseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<ExtinguisherResponseDTO> editExtinguisher(
            @PathVariable String id, @RequestBody ExtinguisherRequestDTO requestDTO, HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String userEmail = service.extractUserEmailFromToken(token);

            Optional<Extinguisher> optionalExtinguisher = service.findById(id);
            if (optionalExtinguisher.isPresent()) {
                Extinguisher existingExtinguisher = optionalExtinguisher.get();

                // Actualizar los campos del extintor existente con los valores proporcionados en la solicitud
                existingExtinguisher.setDate(requestDTO.date());
                existingExtinguisher.setEmpresa(requestDTO.empresa());
                existingExtinguisher.setSector(requestDTO.sector());
                existingExtinguisher.setExtId(requestDTO.extId());
                existingExtinguisher.setTipo(requestDTO.tipo());
                existingExtinguisher.setKg(requestDTO.kg());
                existingExtinguisher.setVencimiento(requestDTO.vencimiento());
                existingExtinguisher.setAccess(requestDTO.access());
                existingExtinguisher.setSignaling(requestDTO.signaling());
                existingExtinguisher.setPresion(requestDTO.presion());
                existingExtinguisher.setObservaciones(requestDTO.observaciones());
                existingExtinguisher.setEnabled(requestDTO.enabled());
                existingExtinguisher.setUserId(userEmail);

                service.save(existingExtinguisher);
                ExtinguisherResponseDTO responseDTO = new ExtinguisherResponseDTO(existingExtinguisher);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                ExtinguisherResponseDTO responseDTO = new ExtinguisherResponseDTO("El extintor con ID " + id + " no se encontró.");
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ExtinguisherResponseDTO responseDTO = new ExtinguisherResponseDTO("Ocurrió un error interno al modificar el extintor.");
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{empresa}")
    public ResponseEntity<List<ExtinguisherDistributionDTO>> getExtinguisherDistributionByEmpresa(
            @PathVariable String empresa) {


        List<Extinguisher> extinguishersByEmpresa = service.findExtinguisherByEmpresa(empresa);
        if (extinguishersByEmpresa.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Map<String, ExtinguisherDistributionDTO> distributionMap = new HashMap<>();
        for (Extinguisher extinguisher : extinguishersByEmpresa) {
            String sector = extinguisher.getSector();
            ExtinguisherDistributionDTO distributionDTO = distributionMap.getOrDefault(sector, new ExtinguisherDistributionDTO(sector, 0, 0, 0, 0, 0));
            // Crear un nuevo registro con los valores actualizados
            distributionDTO = new ExtinguisherDistributionDTO(
                    distributionDTO.sector(),
                    distributionDTO.total() + 1,
                    extinguisher.estaVigente() ? distributionDTO.vigentes() + 1 : distributionDTO.vigentes(),
                    extinguisher.estaVigente() ? distributionDTO.vencidos() + 1 : distributionDTO.vencidos(),
                    extinguisher.isEnabled() ? distributionDTO.habilitados() + 1 : distributionDTO.habilitados(),
                    extinguisher.isEnabled() ? distributionDTO.deshabilitados() : distributionDTO.deshabilitados() + 1
            );
            distributionMap.put(sector, distributionDTO);
        }

        List<ExtinguisherDistributionDTO> distributionList = new ArrayList<>(distributionMap.values());
        return ResponseEntity.ok(distributionList);
    }

    @GetMapping("/companies")
    public ResponseEntity<List<String>> getAllCompanies() {
        List<String> companies = service.findAllCompanies();
        if (companies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(companies);
    }

}
