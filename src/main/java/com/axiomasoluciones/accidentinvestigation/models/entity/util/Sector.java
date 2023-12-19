package com.axiomasoluciones.accidentinvestigation.models.entity.util;


public enum Sector {
    MANTENIMIENTO("Mantenimiento"),
    LOGISTICA("Logística"),
    PRODUCCION("Producción"),
    CONTROL_DE_CALIDAD("Control de calidad"),
    RECURSOS_HUMANOS("Recursos Humanos");

    private final String nombre;

    Sector(String nombre) {
        if (nombre == null) {
            throw new IllegalArgumentException("Nombre no puede ser null");
        }
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
