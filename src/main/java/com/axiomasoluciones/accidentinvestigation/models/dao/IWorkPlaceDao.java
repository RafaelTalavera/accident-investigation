package com.axiomasoluciones.accidentinvestigation.models.dao;

import com.axiomasoluciones.accidentinvestigation.models.entity.WorkPlace;
import org.springframework.data.repository.CrudRepository;

public interface IWorkPlaceDao extends CrudRepository<WorkPlace, Long> {
}
