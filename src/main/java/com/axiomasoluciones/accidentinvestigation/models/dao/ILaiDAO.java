package com.axiomasoluciones.accidentinvestigation.models.dao;

import com.axiomasoluciones.accidentinvestigation.models.entity.Lai;;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ILaiDAO extends MongoRepository<Lai, String> {
    @Query("{'userId':  {$regex : ?0, $options: 'i'}}")
    List<Lai> findByUserId(String userId);
}
