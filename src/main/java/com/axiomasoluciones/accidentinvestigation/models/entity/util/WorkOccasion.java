package com.axiomasoluciones.accidentinvestigation.models.entity.util;

public enum WorkOccasion {

    TAREAS_AJENAS("Tareas ajenas al puesto de trabajo"),
    TAREAS_PROPIAS_RUTINA("Tareas propias del puesto de trabajo dentro de rutina"),
    TAREAS_PROPIAS_FUERA_RUTINA("Tareas propias del puesto de trabajo fuera de rutina"),
    TRASLADO_ENTRE_LUGARES("Trasladarse de un lugar a otro dentro del ámbito de trabajo");

    private final String descripcion;

    WorkOccasion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
