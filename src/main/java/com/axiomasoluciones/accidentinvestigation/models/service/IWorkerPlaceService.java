package com.axiomasoluciones.accidentinvestigation.models.service;

import com.axiomasoluciones.accidentinvestigation.models.entity.WorkPlace;


import java.util.List;
import java.util.Optional;

public interface IWorkerPlaceService {
    public List<WorkPlace> findAll();

    Optional<WorkPlace> findById(Long id);

    WorkPlace save(WorkPlace workPlace);

    public void deleteById(Long id);

    WorkPlace editWorkPlace(Long id, WorkPlace editedWorkPlace);
}
