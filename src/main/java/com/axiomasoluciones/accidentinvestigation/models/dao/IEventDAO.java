package com.axiomasoluciones.accidentinvestigation.models.dao;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface IEventDAO extends MongoRepository<Event, String> {

    @Query("{'userId':  {$regex : ?0, $options: 'i'}}")
    List<Event> findEventByUserId(String userId);
}
