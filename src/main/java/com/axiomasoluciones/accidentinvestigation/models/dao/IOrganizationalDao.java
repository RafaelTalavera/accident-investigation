package com.axiomasoluciones.accidentinvestigation.models.dao;

import com.axiomasoluciones.accidentinvestigation.models.entity.Organizational;
import org.springframework.data.repository.CrudRepository;

public interface IOrganizationalDao extends CrudRepository<Organizational, Long> {
}
