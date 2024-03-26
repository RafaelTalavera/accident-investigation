package com.axiomasoluciones.accidentinvestigation.utils;

import org.springframework.stereotype.Component;

import java.text.Normalizer;

@Component
public class NormalizeText {

    public String normalize(String text) {
        // Normalizar el texto removiendo acentos y convirtiendo a minúsculas
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toUpperCase();
    }
}
