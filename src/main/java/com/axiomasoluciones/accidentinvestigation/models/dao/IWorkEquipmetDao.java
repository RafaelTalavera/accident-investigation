package com.axiomasoluciones.accidentinvestigation.models.dao;

import com.axiomasoluciones.accidentinvestigation.models.entity.WorkEquipment;
import org.springframework.data.repository.CrudRepository;

public interface IWorkEquipmetDao extends CrudRepository<WorkEquipment, Long> {
}
