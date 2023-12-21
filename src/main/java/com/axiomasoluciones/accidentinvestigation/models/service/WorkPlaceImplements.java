package com.axiomasoluciones.accidentinvestigation.models.service;

import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IWorkPlaceDao;
import com.axiomasoluciones.accidentinvestigation.models.entity.WorkPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkPlaceImplements implements IWorkPlaceService {

    @Autowired
    private IWorkPlaceDao workPlaceDao;

    @Override
    public List<WorkPlace> findAll() {
        return (List<WorkPlace>) workPlaceDao.findAll();
    }

    @Override
    public Optional<WorkPlace> findById(Long id) {
        return workPlaceDao.findById(id);
    }

    @Override
    public WorkPlace save(WorkPlace workPlace) {
        return workPlaceDao.save(workPlace);
    }

    @Override
    public void deleteById(Long id) {
        WorkPlace existingWorker = workPlaceDao.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));
        workPlaceDao.delete(existingWorker);
    }

    @Override
    public WorkPlace editWorkPlace(Long id, WorkPlace editedWorkPlace) {
        WorkPlace existingWorkPlace = workPlaceDao.findById(id).orElseThrow(
                () -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));

        // Realizar las modificaciones en la entidad existente
        existingWorkPlace.setName(editedWorkPlace.getName());
        existingWorkPlace.setSector(editedWorkPlace.getSector());
        existingWorkPlace.setWorkers(editedWorkPlace.getWorkers());
        existingWorkPlace.setInvestigations(editedWorkPlace.getInvestigations());

        // Guardar la entidad modificada
        return workPlaceDao.save(existingWorkPlace);
    }
}
