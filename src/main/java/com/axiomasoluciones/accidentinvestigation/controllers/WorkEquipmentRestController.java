package com.axiomasoluciones.accidentinvestigation.controllers;

import com.axiomasoluciones.accidentinvestigation.dto.util.WorkEquimentRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.util.WorkEquipmentResponseDTO;
import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.entity.WorkEquipment;

import com.axiomasoluciones.accidentinvestigation.services.IWorkEquipmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/workEquipment")
public class WorkEquipmentRestController {

    @Autowired
    private IWorkEquipmetService workEquipmetService;

    @GetMapping
    public ResponseEntity<List<WorkEquipmentResponseDTO>> getAll() {
        List<WorkEquipment> workEquipments = workEquipmetService.findAll();
        if (!workEquipments.isEmpty()) {
            List<WorkEquipmentResponseDTO> workEquipmentResponseDTOS = workEquipments.stream()
                    .map(WorkEquipmentResponseDTO::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(workEquipmentResponseDTOS, HttpStatus.OK);
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkEquipmentResponseDTO> getById(@PathVariable Long id) {
        Optional<WorkEquipment> optionalWorkEquipment = workEquipmetService.findById(id);
        if (optionalWorkEquipment.isPresent()) {
            WorkEquipment workEquipment = optionalWorkEquipment.get();
            WorkEquipmentResponseDTO workEquipmentResponseDTO = new WorkEquipmentResponseDTO(workEquipment);
            return new ResponseEntity<>(workEquipmentResponseDTO, HttpStatus.OK);
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkEquipmet(@PathVariable Long id) {
        workEquipmetService.deleteById(id);
        return new ResponseEntity<>("Registro eliminado correctamente", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WorkEquipmentResponseDTO> createWorker(@RequestBody WorkEquimentRequestDTO data) {
        WorkEquipment newWorkEquipment = new WorkEquipment(data);
        workEquipmetService.save(newWorkEquipment);
        WorkEquipmentResponseDTO workEquipmentResponseDTO = new WorkEquipmentResponseDTO(newWorkEquipment);
        return new ResponseEntity<>(workEquipmentResponseDTO, HttpStatus.CREATED);
    }

}



