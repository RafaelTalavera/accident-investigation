package com.axiomasoluciones.accidentinvestigation.models.dao;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface IEventDao extends MongoRepository<Event, String> {
}
