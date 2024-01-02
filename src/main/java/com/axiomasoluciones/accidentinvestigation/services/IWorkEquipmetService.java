package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.models.entity.WorkEquipment;

import java.util.List;
import java.util.Optional;

public interface IWorkEquipmetService {

    public List<WorkEquipment> findAll();

    Optional<WorkEquipment> findById(Long id);

    WorkEquipment save(WorkEquipment workEquipment);

    public void deleteById(Long id);

    void delete(WorkEquipment workEquipment);
}
