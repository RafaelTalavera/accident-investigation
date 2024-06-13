package com.axiomasoluciones.accidentinvestigation.models.entity;


import com.axiomasoluciones.accidentinvestigation.dto.request.WorkerRequestDTO;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "workers")
public class Worker {

    @Id
    private String id;
    private String organizationId;
    private String nameOrganization;
    private String name;
    private String lastname;
    private String dni;
    private byte[] fingerPrint;
    private String userId;

    public Worker(WorkerRequestDTO workerRequestDTO){
        this.organizationId = workerRequestDTO.organizationId();
        this.nameOrganization = workerRequestDTO.nameOrganization();
        this.name = workerRequestDTO.name();
        this.lastname = workerRequestDTO.lastname();
        this.dni = workerRequestDTO.dni();
        this.fingerPrint = workerRequestDTO.fingerPrint();
        this.userId = workerRequestDTO.userId();

    }
}
