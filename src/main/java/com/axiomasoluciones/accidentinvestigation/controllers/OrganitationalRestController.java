package com.axiomasoluciones.accidentinvestigation.controllers;

import com.axiomasoluciones.accidentinvestigation.dto.util.OrganizationalRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.util.OrganizationalResponseDTO;
import com.axiomasoluciones.accidentinvestigation.dto.util.WorkEquipmentResponseDTO;
import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IOrganizationalDao;
import com.axiomasoluciones.accidentinvestigation.models.entity.Organizational;
import com.axiomasoluciones.accidentinvestigation.models.entity.WorkEquipment;
import com.axiomasoluciones.accidentinvestigation.services.IOrganizationalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/organitional")
public class OrganitationalRestController {

    @Autowired
    private IOrganizationalService organizationalService;

    @GetMapping
    public ResponseEntity<List<OrganizationalResponseDTO>> getall(){
        List<Organizational> organizationals = organizationalService.findAll();

        if (!organizationals.isEmpty()) {
            List<OrganizationalResponseDTO> ResponseDTOS = organizationals.stream()
                    .map(OrganizationalResponseDTO::new)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(ResponseDTOS, HttpStatus.OK);
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationalResponseDTO> getById(@PathVariable Long id) {
        Optional<Organizational> optionalOrganizational = organizationalService.findById(id);
        if (optionalOrganizational.isPresent()) {
            Organizational organizational = optionalOrganizational.get();
            OrganizationalResponseDTO organizationalResponseDTO = new OrganizationalResponseDTO(organizational);
            return new ResponseEntity<>(organizationalResponseDTO, HttpStatus.OK);
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrganitational(@PathVariable Long id) {
        organizationalService.deleteById(id);
        return new ResponseEntity<>("Registro eliminado correctamente", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrganizationalResponseDTO> createOrganizational(@RequestBody OrganizationalRequestDTO data) {
        Organizational newOrganizational = new Organizational(data);
        organizationalService.save(newOrganizational);
        OrganizationalResponseDTO organizationalResponseDTO = new OrganizationalResponseDTO(newOrganizational);
        return new ResponseEntity<>(organizationalResponseDTO, HttpStatus.CREATED);
    }


}
