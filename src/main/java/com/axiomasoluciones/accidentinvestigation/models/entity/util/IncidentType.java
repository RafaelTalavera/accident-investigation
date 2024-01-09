package com.axiomasoluciones.accidentinvestigation.models.entity.util;

public enum IncidentType {

    ATRAPADO_POR("Atrapado por"),
    CAIDA_DISTINTO_NIVEL("Caída a distinto nivel"),
    CAIDA_MISMO_NIVEL("Caída al mismo nivel"),
    CONTACTO_CALOR("Contacto con Calor"),
    CONTACTO_ELECTRICIDAD("Contacto con Electricidad"),
    CONTACTO_QUIMICOS("Contacto con Químicos"),
    CONTACTO_FRIO("Contacto con frío"),
    CONTACTO_CORTANTE("Contacto con elemento cortante"),
    CONTACTO_PUNZANTE("Contacto con elemento punzante"),
    EXPOSICION_GASES("Exposición a gases"),
    EXPOSICION_RADIACTIVIDAD("Exposición a radiación"),
    GOLPEADO_CONTRA("Golpeado contra"),
    GOLPEADO_POR("Golpeado por"),
    PROYECCION_FLUIDOS("Proyección de fluidos"),
    PROYECCION_PARTICULAS("Proyección de partículas");

    private final String descripcion;

    IncidentType(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
