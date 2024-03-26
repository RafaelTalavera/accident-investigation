package com.axiomasoluciones.accidentinvestigation.controllers;

import com.axiomasoluciones.accidentinvestigation.dto.LaiRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.LaiResponseDTO;
import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.entity.Lai;
import com.axiomasoluciones.accidentinvestigation.services.ILaiSevice;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/lai")
public class LaiRestControllor {

    @Autowired
    private ILaiSevice laiSevice;

    @GetMapping
    public ResponseEntity<List<LaiResponseDTO>> getAll() {
        List<Lai> lais = laiSevice.findAll();
        if (!((List<?>) lais).isEmpty()) {
            List<LaiResponseDTO> laiResponseDTOS = lais.stream().map(LaiResponseDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(laiResponseDTOS, HttpStatus.OK);
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<LaiResponseDTO>> getAll(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String userEmail = laiSevice.extractUserEmailFromToken(token);
            List<Lai> lais = laiSevice.findByUserId(userEmail);
            if (!lais.isEmpty()) {
                List<LaiResponseDTO> laiResponseDTOS = lais.stream().map(LaiResponseDTO::new).collect(Collectors.toList());
                return new ResponseEntity<>(laiResponseDTOS, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<LaiResponseDTO> createLai(
            @RequestBody LaiRequestDTO data, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String userEmail = laiSevice.extractUserEmailFromToken(token);

        Lai newLai = new Lai(data);
        newLai.setUserId(userEmail);

        laiSevice.save(newLai);
        LaiResponseDTO laiResponseDTO = new LaiResponseDTO(newLai);
        return new ResponseEntity<>(laiResponseDTO, HttpStatus.CREATED);
    }

}

