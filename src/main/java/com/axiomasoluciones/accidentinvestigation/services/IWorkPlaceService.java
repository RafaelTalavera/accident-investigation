package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.models.entity.WorkPlace;


import java.util.List;
import java.util.Optional;

public interface IWorkPlaceService {
    public List<WorkPlace> findAll();

    Optional<WorkPlace> findById(Long id);

    WorkPlace save(WorkPlace workPlace);

    public void deleteById(Long id);

    WorkPlace editWorkPlace(Long id, WorkPlace editedWorkPlace);

    void delete(WorkPlace workPlace);
}
