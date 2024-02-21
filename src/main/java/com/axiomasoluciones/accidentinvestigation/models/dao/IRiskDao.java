package com.axiomasoluciones.accidentinvestigation.models.dao;

import com.axiomasoluciones.accidentinvestigation.models.entity.Risk;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IRiskDao extends MongoRepository<Risk, String> {
    @Query("{'userId':  {$regex : ?0, $options: 'i'}}")
    List<Risk> findByUserId(String userId);
}
