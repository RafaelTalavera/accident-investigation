package com.axiomasoluciones.accidentinvestigation.models.entity;

import com.axiomasoluciones.accidentinvestigation.models.entity.util.BodyParts;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Severity;

import java.util.Date;
import java.util.List;

public class event {
    private Long id;
    private Date date;
    private String description;
    private Severity severity;
    private List<BodyParts> bodyParts;
    private Injury injury;


}
