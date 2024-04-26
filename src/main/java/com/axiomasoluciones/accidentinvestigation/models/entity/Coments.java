package com.axiomasoluciones.accidentinvestigation.models.entity;

import com.axiomasoluciones.accidentinvestigation.dto.request.ComentsRequestDTO;
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
@Document
public class Coments {
    @Id
    private String id;
    private String texto; // Asegúrate de que este campo esté presente
    private String userId;

    public Coments(ComentsRequestDTO comentsRequestDTO){
        this.texto = comentsRequestDTO.texto();
        this.userId = comentsRequestDTO.userId();
    }

}
