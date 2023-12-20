package com.axiomasoluciones.accidentinvestigation.models.dao;

import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;
import org.springframework.data.repository.CrudRepository;

public interface IWorkerDao extends CrudRepository<Worker, Long> {

}
