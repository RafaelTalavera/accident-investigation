package com.axiomasoluciones.accidentinvestigation.controllers;


import com.axiomasoluciones.accidentinvestigation.dto.WorkPlaceRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.WorkPlaceResponseDTO;
import com.axiomasoluciones.accidentinvestigation.dto.WorkerResponseDTO;
import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.entity.WorkPlace;
import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;
import com.axiomasoluciones.accidentinvestigation.models.service.IWorkerPlaceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/workPlace")
public class WorkPlaceRestController {

    @Autowired
    private IWorkerPlaceService service;

    @GetMapping
    public ResponseEntity<List<WorkPlaceResponseDTO>> getAll() {
        List<WorkPlace> workPlace = service.findAll();
        if (!workPlace.isEmpty()) {
            List<WorkPlaceResponseDTO> workPlaceResponseDTOs = workPlace.stream()
                    .map(WorkPlaceResponseDTO::new)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(workPlaceResponseDTOs, HttpStatus.OK);
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<WorkPlaceResponseDTO> getById(@PathVariable Long id) {
        Optional<WorkPlace> optionalWorkPlace = service.findById(id);

        if (optionalWorkPlace.isPresent()) {
            WorkPlace workPlace = optionalWorkPlace.get();
            WorkPlaceResponseDTO workPlaceDTO = new WorkPlaceResponseDTO(workPlace);
            return new ResponseEntity<>(workPlaceDTO, HttpStatus.OK);
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<>("Registro eliminado correctamente", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WorkPlaceResponseDTO> create(@RequestBody WorkPlace newWorkPlace) {
        WorkPlace createdWorkPlace = service.save(newWorkPlace);
        WorkPlaceResponseDTO workPlaceResponseDTO = new WorkPlaceResponseDTO(createdWorkPlace);
        return new ResponseEntity<>(workPlaceResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkPlaceResponseDTO> editWorkPlace(@PathVariable Long id, @RequestBody WorkPlace editedWorkPlace) {
        WorkPlace editedResult = service.editWorkPlace(id, editedWorkPlace);
        WorkPlaceResponseDTO workPlaceDTO = new WorkPlaceResponseDTO(editedResult);
        return new ResponseEntity<>(workPlaceDTO, HttpStatus.OK);
    }
}

