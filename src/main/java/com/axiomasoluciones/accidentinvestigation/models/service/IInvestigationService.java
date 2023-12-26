package com.axiomasoluciones.accidentinvestigation.models.service;

import com.axiomasoluciones.accidentinvestigation.models.entity.Investigation;

import java.util.List;
import java.util.Optional;

public interface IInvestigationService {

    public List<Investigation> findAll();

    Optional<Investigation> findById(Long id);

    Investigation save(Investigation investigation);

    public void deleteByIdEvent(Long id);

    Investigation editEvent(Long id, Investigation editedEvent);
}
