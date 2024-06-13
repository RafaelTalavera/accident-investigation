package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;

import java.util.List;
import java.util.Optional;

public interface  IWorkerService {

    public List<Worker> findAll();

    Optional<Worker> findById(String id);

    Worker save(Worker risk);

    public void deleteById(String id);

    Worker editWorker(String id, Worker editedWorker);

    String extractUserEmailFromToken(String token);

    List<Worker> findByUserId(String userId);

    List<Worker> findDistinctOrganizationByUserId(String userId);

    List<Worker> findWorkByUserIdAndNameOrganization(String userId, String nameOrganization);

    Optional<Worker> findByFingerPrint(byte[] fingerPrint);
}
