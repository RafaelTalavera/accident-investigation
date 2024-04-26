package com.axiomasoluciones.accidentinvestigation.models.entity;


import com.axiomasoluciones.accidentinvestigation.dto.request.OrganizationRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Organization implements Serializable {
    @Id
    private String id;
    private String name;
    private String tipoOrga;
    private String sector; //a que se dedica la empresa
    private Integer nEmpleados;
    private Double superficie;
    private String userId;

    public Organization(OrganizationRequestDTO organizationRequestDTO) {
        this.name = organizationRequestDTO.name();
        this.tipoOrga = organizationRequestDTO.tipoOrga();
        this.sector = organizationRequestDTO.sector();
        this.nEmpleados = organizationRequestDTO.nEmpleados();
        this.superficie = organizationRequestDTO.superficie();
        this.userId = organizationRequestDTO.userId();

    }

    @Serial
    private static final long serialVersionUID = 1L;
}
