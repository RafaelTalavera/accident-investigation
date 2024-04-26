package com.axiomasoluciones.accidentinvestigation.controllers;

import com.axiomasoluciones.accidentinvestigation.dto.request.ComentsRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.response.ComentsResponseDTO;
import com.axiomasoluciones.accidentinvestigation.models.entity.Coments;
import com.axiomasoluciones.accidentinvestigation.services.IComentsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/coments")
public class ComentsRestController {

    @Autowired
    private IComentsService service;

    @PostMapping
    public ResponseEntity<ComentsResponseDTO> save(@RequestBody
            ComentsRequestDTO data, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String userEmail = service.extractUserEmailFromToken(token);

        Coments newComents = new Coments(data);
        newComents.setUserId(userEmail);
        service.save(newComents);
        ComentsResponseDTO comentsResponseDTO = new ComentsResponseDTO(newComents);

        return new ResponseEntity<>(comentsResponseDTO, HttpStatus.CREATED);
    }

    }

