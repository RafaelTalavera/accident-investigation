package com.axiomasoluciones.accidentinvestigation.models.service;

import com.axiomasoluciones.accidentinvestigation.dto.WorkerRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.WorkerResponseDTO;
import com.axiomasoluciones.accidentinvestigation.models.entity.WorkPlace;
import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;
import java.util.List;
import java.util.Optional;

public interface IWorkerService {

    public List<Worker> findAll();

    Optional<Worker> findById(Long id);

    Worker save(Worker worker);

    public void deleteById(Long id);

    Worker editWorker(Long id, Worker editedWorker);

}
