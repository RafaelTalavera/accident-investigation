package com.axiomasoluciones.accidentinvestigation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor

public class ConsumoDTO {
    private String nameOrganization;
    private String combustible;
    private String unidad;
    private LocalDateTime yearMonth;
    private double totalConsumo;

    public ConsumoDTO(String nameOrganization, String combustible, String unidad, LocalDateTime yearMonth, double totalConsumo) {
        this.nameOrganization = nameOrganization;
        this.combustible = combustible;
        this.unidad = unidad;
        this.yearMonth = yearMonth;
        this.totalConsumo = totalConsumo;
    }
}