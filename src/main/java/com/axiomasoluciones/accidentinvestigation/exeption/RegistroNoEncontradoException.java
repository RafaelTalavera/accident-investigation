package com.axiomasoluciones.accidentinvestigation.exeption;

public class RegistroNoEncontradoException extends RuntimeException {
    public RegistroNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
