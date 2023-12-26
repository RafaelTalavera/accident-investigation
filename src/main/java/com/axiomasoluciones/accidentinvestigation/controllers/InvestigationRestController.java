package com.axiomasoluciones.accidentinvestigation.controllers;

import com.axiomasoluciones.accidentinvestigation.dto.InvestigationRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.InvestigationResponseDTO;
import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.models.entity.Investigation;
import com.axiomasoluciones.accidentinvestigation.models.entity.WorkPlace;
import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;
import com.axiomasoluciones.accidentinvestigation.models.service.IEventService;
import com.axiomasoluciones.accidentinvestigation.models.service.IInvestigationService;
import com.axiomasoluciones.accidentinvestigation.models.service.IWorkPlaceService;
import com.axiomasoluciones.accidentinvestigation.models.service.IWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/investigation")
public class InvestigationRestController {

    @Autowired
    private IInvestigationService investigationService;

    @Autowired
    private IWorkerService workerService;

    @Autowired
    private IWorkPlaceService workPlaceService;

    @Autowired
    private IEventService eventService;

    @GetMapping
    public ResponseEntity<List<InvestigationResponseDTO>> getAll(){
        List<Investigation> investigations = investigationService.findAll();
        if (!investigations.isEmpty()){
            List<InvestigationResponseDTO> ivestigacionResponseDTOS = investigations.stream()
                    .map(InvestigationResponseDTO::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(ivestigacionResponseDTOS, HttpStatus.OK);
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestigationResponseDTO> getById(@PathVariable Long id){
        Optional<Investigation> optionalInvestigation = investigationService.findById(id);
        if(optionalInvestigation.isPresent()){
            Investigation investigation = optionalInvestigation.get();
            InvestigationResponseDTO ivestigacionResponseDTO = new InvestigationResponseDTO(investigation);
            return new ResponseEntity<>(ivestigacionResponseDTO, HttpStatus.OK);
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInvestigation(@PathVariable Long id){
        investigationService.deleteByIdEvent(id);
        return new ResponseEntity<>("Registro eliminado correctamente", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InvestigationResponseDTO> createInvestigation(@RequestBody InvestigationRequestDTO investigationRequestDTO) {
        Optional<Worker> oWorker = workerService.findById(investigationRequestDTO.workerId());
        Optional<WorkPlace> oWorkPlace = workPlaceService.findById(investigationRequestDTO.workPlaceId());
        Optional<Event> oEvent = eventService.findById(investigationRequestDTO.eventId());

        if (oWorker.isEmpty() || oWorkPlace.isEmpty() || oEvent.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        Investigation newInvestigation = new Investigation(investigationRequestDTO);
        newInvestigation.setEvent(oEvent.get());
        newInvestigation.setWorker(oWorker.get());
        newInvestigation.setWorkPlace(oWorkPlace.get());


        investigationService.save(newInvestigation);
        InvestigationResponseDTO investigationResponseDTO = new InvestigationResponseDTO(newInvestigation);

        return new ResponseEntity<>(investigationResponseDTO, HttpStatus.CREATED);
    }
    }



