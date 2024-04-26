package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.models.entity.Coments;


import java.util.List;

public interface IComentsService {

    public Coments save(Coments coments);

    String extractUserEmailFromToken(String token);

    List<Coments> findByUserId(String userId);


}
