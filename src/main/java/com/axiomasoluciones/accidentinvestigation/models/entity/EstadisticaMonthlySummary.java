package com.axiomasoluciones.accidentinvestigation.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadisticaMonthlySummary {
    private String organization;
    private int year;
    private int month;
    private int totalHours;
}



