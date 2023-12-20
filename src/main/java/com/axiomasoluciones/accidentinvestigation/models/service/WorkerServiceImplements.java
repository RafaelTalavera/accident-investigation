package com.axiomasoluciones.accidentinvestigation.models.service;

import com.axiomasoluciones.accidentinvestigation.dto.WorkerRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.WorkerResponseDTO;
import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IWorkerDao;
import com.axiomasoluciones.accidentinvestigation.models.entity.WorkPlace;
import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class WorkerServiceImplements implements IWorkerService {

    @Autowired
    private IWorkerDao workerDao;

    @Autowired
    private IWorkerPlaceService workPlaceService;

    @Override
    @Transactional(readOnly = true)
    public List<Worker> findAll() {
        return (List<Worker>) workerDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Worker> findById(Long id) {
        return workerDao.findById(id);
    }

    @Override
    @Transactional
    public Worker save(Worker worker) {
        return workerDao.save(worker);
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        Worker existingWorker = workerDao.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));
        workerDao.delete(existingWorker);
    }

    @Override
    @Transactional
    public Worker editWorker(Long id, Worker editedWorker) {
        Worker existingWorker = workerDao.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));

        existingWorker.setFullName(editedWorker.getFullName());
        existingWorker.setBirth(editedWorker.getBirth());
        existingWorker.setAddress(editedWorker.getAddress());
        existingWorker.setEntry(editedWorker.getEntry());
        existingWorker.setWorkPlace(editedWorker.getWorkPlace());
        existingWorker.setEvents(editedWorker.getEvents());

        return workerDao.save(existingWorker);
    }



}
