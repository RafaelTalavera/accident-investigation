package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;
import java.util.List;
import java.util.Optional;

public interface IWorkerService {

    public List<Worker> findAll();

    Optional<Worker> findById(Long id);

    Worker save(Worker worker);

    public void deleteById(Long id);

    Worker editWorker(Long id, Worker editedWorker);

    void delete(Worker worker);
}
