package com.axiomasoluciones.accidentinvestigation.models.entity;

import com.axiomasoluciones.accidentinvestigation.dto.WorkerRequestDTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Worker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private LocalDate birth;
    private String address;
    private LocalDate entry;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_place_id")
    private WorkPlace workPlace;

    @OneToMany(mappedBy = "worker", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Event> events;

    @OneToMany(mappedBy = "worker", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Investigation> investigations;

    public Worker(WorkerRequestDTO workerRequestDTO){
        this.fullName = workerRequestDTO.fullName();
        this.birth = workerRequestDTO.birth();
        this.entry = workerRequestDTO.entry();
        this.address = workerRequestDTO.address();
        this.events = workerRequestDTO.events();
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
