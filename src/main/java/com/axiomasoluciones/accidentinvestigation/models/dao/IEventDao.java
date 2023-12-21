package com.axiomasoluciones.accidentinvestigation.models.dao;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import org.springframework.data.repository.CrudRepository;

public interface IEventDao extends CrudRepository<Event, Long> {
}
