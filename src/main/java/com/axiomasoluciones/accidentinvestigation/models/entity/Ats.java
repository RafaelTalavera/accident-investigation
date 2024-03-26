package com.axiomasoluciones.accidentinvestigation.models.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Ats implements Serializable {

    @Id
    private String id;
    private String company;
    private String area;
    private String puesto;
    private String lastName;
    private LocalDate date;
    private String tarea;  //tarea que se va a realizar
    private String site;  //lugar donde se realiza en ats
    private String elavoracion; //quien realizo el ats
    private String aprobacion; //quien aprueba el ats
    private String revision;   //quien lo reviso
    private String ambiente;  // datos del ambiente de trabajo
    private String herramientas;
    private String material;
    private String epp;
    private String job;  // trabajo de riesgo
    private String documentacion; // Aut - ats - analisis
    private String tipo; //rutinario o no rutinario
    private String pasos;
    private String potencial;
    private String riesgos;
    private String control;

}
