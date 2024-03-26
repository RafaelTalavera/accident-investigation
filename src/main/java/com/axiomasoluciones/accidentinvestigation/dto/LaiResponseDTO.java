package com.axiomasoluciones.accidentinvestigation.dto;

import com.axiomasoluciones.accidentinvestigation.models.entity.Lai;

import java.time.LocalDate;

public record LaiResponseDTO(
        String id,
        LocalDate date,
        //  String organization,
        String area,
        String tipo,
        String activity,
        String aspect,
        String impact,
        String temporality,
        String description,
        String cycle,
        int frequency,
        int damage,
        String stateHolder,
        String legislation,
        String meaningfulness,
        String typeOfControl,
        String descriptionOfControl,
        String dateOfRevision,
        String userId

) {
    public LaiResponseDTO(Lai lai) {
        this(
                lai.getId(),
                lai.getDate(),
                // lai.getOrganization(),
                lai.getArea(),
                lai.getTipo(),
                lai.getActivity(),
                lai.getAspect(),
                lai.getImpact(),
                lai.getTemporality(),
                lai.getDescription(),
                lai.getCycle(),
                lai.getFrequency(),
                lai.getDamage(),
                lai.getStateHolder(),
                lai.getLegislation(),
                lai.getMeaningfulness(),
                lai.getTypeOfControl(),
                lai.getDescriptionOfControl(),
                lai.getDateOfRevision(),
                lai.getUserId()
        );

    }
}
