package com.axiomasoluciones.accidentinvestigation.controllers;

import com.axiomasoluciones.accidentinvestigation.dto.util.WorkerRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.util.WorkerResponseDTO;
import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.entity.WorkPlace;
import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;
import com.axiomasoluciones.accidentinvestigation.services.IWorkPlaceService;
import com.axiomasoluciones.accidentinvestigation.services.IWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/worker")
public class WorkerRestController {

    @Autowired
    private IWorkerService workerService;

    @GetMapping
    public ResponseEntity<List<WorkerResponseDTO>> getAll() {
        List<Worker> workers = workerService.findAll();
        if (!workers.isEmpty()) {
            List<WorkerResponseDTO> workerDTOs = workers.stream()
                    .map(WorkerResponseDTO::new)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(workerDTOs, HttpStatus.OK);
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<WorkerResponseDTO> getById(@PathVariable Long id) {
        Optional<Worker> optionalWorker = workerService.findById(id);

        if (optionalWorker.isPresent()) {
            Worker worker = optionalWorker.get();
            WorkerResponseDTO workerDTO = new WorkerResponseDTO(worker);
            return new ResponseEntity<>(workerDTO, HttpStatus.OK);
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorker(@PathVariable Long id) {
        workerService.deleteById(id);
        return new ResponseEntity<>("Registro eliminado correctamente", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WorkerResponseDTO> createWorker(@RequestBody WorkerRequestDTO data) {
        Worker newWorker = new Worker(data);
        workerService.save(newWorker);
        WorkerResponseDTO workerResponseDTO = new WorkerResponseDTO(newWorker);
        return new ResponseEntity<>(workerResponseDTO, HttpStatus.CREATED);
    }

}
