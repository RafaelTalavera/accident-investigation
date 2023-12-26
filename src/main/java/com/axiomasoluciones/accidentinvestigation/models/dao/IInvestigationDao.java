package com.axiomasoluciones.accidentinvestigation.models.dao;

import com.axiomasoluciones.accidentinvestigation.models.entity.Investigation;
import org.springframework.data.repository.CrudRepository;

public interface IInvestigationDao extends CrudRepository<Investigation, Long> {
}
