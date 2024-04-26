package com.axiomasoluciones.accidentinvestigation.models.entity;

import com.axiomasoluciones.accidentinvestigation.dto.request.ConsumoRequestDTO;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Consumo implements Serializable {

    @Id
    private String id;
    private String organizationId;
    private String nameOrganization;
    private LocalDate date;
    private String fuente;
    private String tipoFuente;
    private String combustible;
    private String unidad;
    private Double consumo;
    private String userId;

    public Consumo(ConsumoRequestDTO consumoRequestDTO){
        this.date = consumoRequestDTO.date();
        this.organizationId = consumoRequestDTO.organizationId();
        this.nameOrganization = consumoRequestDTO.nameOrganization();
        this.fuente = consumoRequestDTO.fuente();
        this.tipoFuente = consumoRequestDTO.tipoFuente();
        this.combustible = consumoRequestDTO.combustible();
        this.consumo = consumoRequestDTO.consumo();
        this.unidad = consumoRequestDTO.unidad();
        this.userId = consumoRequestDTO.userId();
    }

    @Serial
    private static final long serialVersionUID = 1L;

    public String getMonth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
        return date.format(formatter);
    }

    public int getYear() {
        return date.getYear();
    }

}
