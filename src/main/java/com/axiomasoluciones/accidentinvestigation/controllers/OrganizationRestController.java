package com.axiomasoluciones.accidentinvestigation.controllers;

import com.axiomasoluciones.accidentinvestigation.dto.request.OrganizationRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.response.OrganizationResponseDTO;
import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.entity.Organization;
import com.axiomasoluciones.accidentinvestigation.services.IOrganizationService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/organization")
public class OrganizationRestController {

    @Autowired
    private IOrganizationService organizationService;


    @GetMapping
    public ResponseEntity<List<OrganizationResponseDTO>> getAll(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String userEmail = organizationService.extractUserEmailFromToken(token);
        List<Organization> organizations = organizationService.findByUserId(userEmail);
        if (!((List<?>) organizations).isEmpty()){
            List<OrganizationResponseDTO> organizationResponseDTOS = organizations.stream()
                    .map(OrganizationResponseDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(organizationResponseDTOS, HttpStatus.OK);
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
        }
    }


    @PreAuthorize("permitAll")
    @PostMapping
    public ResponseEntity<?> createOrganization(
            @RequestBody OrganizationRequestDTO data, HttpServletRequest request){
        try {
            String token = request.getHeader("Authorization");
            String userEmail = organizationService.extractUserEmailFromToken(token);
            Organization newOrganization = new Organization(data);
            newOrganization.setUserId(userEmail);
            organizationService.save(newOrganization);
            OrganizationResponseDTO organizationResponseDTO = new OrganizationResponseDTO(newOrganization);
            return new ResponseEntity<>(organizationResponseDTO, HttpStatus.OK);
        } catch (DuplicateKeyException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("error", "DuplicateKeyException");
            errorResponse.put("message", "Error: Ya existe una organización con el mismo nombre");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("error", "Internal Server Error");
            errorResponse.put("message", "Error interno del servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
