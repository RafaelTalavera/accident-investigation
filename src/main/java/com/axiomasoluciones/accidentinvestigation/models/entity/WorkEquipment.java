package com.axiomasoluciones.accidentinvestigation.models.entity;

import com.axiomasoluciones.accidentinvestigation.dto.util.WorkEquimentRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.util.WorkEquipmentResponseDTO;
import com.axiomasoluciones.accidentinvestigation.models.entity.util.Energy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WorkEquipment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String workEquipmentFailsDetalis;
    private Boolean workEquipmentFails;

    private Energy energy;

    private String defenseFailedDetails;
    private Boolean defenseFailed;

    private String correctUseEquimantDetails;
    private Boolean correctUseEquimant;

    @OneToMany(mappedBy = "workEquipment", cascade = CascadeType.ALL)
    private List<Event> events;

    public WorkEquipment(WorkEquimentRequestDTO workEquimentRequestDTO){
        this.name = workEquimentRequestDTO.name();
        this.workEquipmentFailsDetalis = workEquimentRequestDTO.workEquipmentFailsDetalis();
        this.workEquipmentFails = workEquimentRequestDTO.workEquipmentFails();
        this.energy = workEquimentRequestDTO.energy();
        this.defenseFailed = workEquimentRequestDTO.defenseFailed();
        this.correctUseEquimantDetails = workEquimentRequestDTO.correctUseEquimantDetails();
        this.correctUseEquimant = workEquimentRequestDTO.correctUseEquimant();
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
