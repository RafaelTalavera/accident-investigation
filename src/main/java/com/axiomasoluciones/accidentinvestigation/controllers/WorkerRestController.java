package com.axiomasoluciones.accidentinvestigation.controllers;

import com.axiomasoluciones.accidentinvestigation.dto.request.WorkerRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.response.WorkerResponseDTO;
import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;
import com.axiomasoluciones.accidentinvestigation.services.IWorkerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/workers")
public class WorkerRestController {

    @Autowired
    private IWorkerService service;

    @GetMapping
    public ResponseEntity<List<WorkerResponseDTO>> getAll() {
        List<Worker> workers = service.findAll();
        if (!((List<?>) workers).isEmpty()) {
            List<WorkerResponseDTO> workerResponseDTOS = workers.stream()
                    .map(WorkerResponseDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(workerResponseDTOS, HttpStatus.OK);
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<WorkerResponseDTO>> getAll(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String userEmail = service.extractUserEmailFromToken(token);
            List<Worker> workers = service.findByUserId(userEmail);
            if (!workers.isEmpty()) {
                List<WorkerResponseDTO> workerResponseDTOS = workers.stream()
                        .map(WorkerResponseDTO::new).collect(Collectors.toList());
                return new ResponseEntity<>(workerResponseDTOS, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/organization/{name}")
    public ResponseEntity<List<WorkerResponseDTO>> getWorkerByAndOrganization(
            HttpServletRequest request,
            @PathVariable("name") String nameOrganization) {
        try {
            String token = request.getHeader("Authorization");
            String userId = service.extractUserEmailFromToken(token);

            List<Worker> workers = service.findWorkByUserIdAndNameOrganization(userId, nameOrganization);
            if (!workers.isEmpty()) {
                List<WorkerResponseDTO> workerResponseDTOS = workers.stream()
                        .map(WorkerResponseDTO::new).collect(Collectors.toList());
                return new ResponseEntity<>(workerResponseDTOS, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<WorkerResponseDTO> createWorker(
            @RequestBody WorkerRequestDTO data, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String userEmail = service.extractUserEmailFromToken(token);
        Worker newWorker = new Worker(data);
        newWorker.setUserId(userEmail);
        service.save(newWorker);
        WorkerResponseDTO workerResponseDTO = new WorkerResponseDTO(newWorker);
        return new ResponseEntity<>(workerResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<WorkerResponseDTO> editedWorker(@PathVariable String id,
                                                          @RequestBody WorkerRequestDTO requestDTO,
                                                          HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String userEmail = service.extractUserEmailFromToken(token);
        try {
            Worker editWorker = new Worker();
            editWorker.setUserId(userEmail);
            editWorker.setNameOrganization(requestDTO.nameOrganization());
            editWorker.setOrganizationId(requestDTO.organizationId());
            editWorker.setDni(requestDTO.dni());
            editWorker.setName(requestDTO.name());
            editWorker.setLastname(requestDTO.lastname());
            editWorker.setFingerPrint(requestDTO.fingerPrint());

            Worker updateWorker = service.editWorker(id, editWorker);
            WorkerResponseDTO responseDTO = new WorkerResponseDTO(updateWorker);
            return ResponseEntity.ok(responseDTO);
        } catch (RegistroNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/verificar-asistencia")
    public Optional<Worker> verificarAsistencia(@RequestBody byte[] fingerPrint) {
        return service.findByFingerPrint(fingerPrint);

    }
}
