package com.axiomasoluciones.accidentinvestigation.models.dao;

import com.axiomasoluciones.accidentinvestigation.models.entity.Coments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IComentsDAO extends MongoRepository<Coments, String> {

    @Query("{'userId':  {$regex : ?0, $options: 'i'}}")
    List<Coments> findByUserId(String userId);

}
