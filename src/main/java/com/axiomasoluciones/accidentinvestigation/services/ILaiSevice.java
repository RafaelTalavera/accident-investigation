package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.models.entity.Lai;


import java.util.List;
import java.util.Optional;

public interface ILaiSevice {

    public List<Lai> findAll();

    public Optional<Lai> findAll(String id);

    public Lai save(Lai lai);

    public void deteleById(String id);

    public Lai editLai(String id, Lai editeLai);

    String extractUserEmailFromToken(String token);

    List<Lai> findByUserId(String userId);

}
