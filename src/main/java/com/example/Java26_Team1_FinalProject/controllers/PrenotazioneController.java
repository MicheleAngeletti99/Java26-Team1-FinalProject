package com.example.Java26_Team1_FinalProject.controllers;

import com.example.Java26_Team1_FinalProject.entities.Prenotazione;
import com.example.Java26_Team1_FinalProject.services.PrenotazioneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Operation(summary = "Salva una prenotazione", description = "Quando gli si dà una prenotazione crea un record con i suoi dati nel database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La prenotazione è stata aggiunta correttamente al database"),
            @ApiResponse(responseCode = "400", description = "La prenotazione data non è valida")
    })
    @PostMapping("/create")
    public ResponseEntity<Prenotazione> create(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "La prenotazione da salvare nel database") @RequestBody Prenotazione prenotazione) {
        // questo controllo serve o già sta sul "required = true" di @RequestBody?
        if (prenotazione == null) {
            return ResponseEntity.badRequest().build();
        }
        Prenotazione responsePrenotazione = prenotazioneService.create(prenotazione);
        return ResponseEntity.ok().body(responsePrenotazione);
    }

    @Operation(summary = "Legge tutte le prenotazioni", description = "Restituisce tutte le prenotazioni presenti nel database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le prenotazioni sono state lette correttamente")
    })
    @GetMapping("/readAll")
    public ResponseEntity<List<Prenotazione>> readAll() {
        List<Prenotazione> prenotazioni = prenotazioneService.readAll();
        return ResponseEntity.ok().body(prenotazioni);
    }

    @Operation(summary = "Legge una prenotazione", description = "Quando gli si dà un id restituisce la prenotazione nel database con l'id corrispondente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La prenotazione è stata trovata e restituita correttamente"),
            @ApiResponse(responseCode = "404", description = "L'id non è tra quelli delle prenotazioni nel database")
    })
    @GetMapping("/readOne/{id}")
    public ResponseEntity<Prenotazione> readById(@Parameter(name = "id", description = "id della prenotazione da trovare e restituire") @PathVariable Long id) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneService.readById(id);
        // controllo se il metodo del service è andato a buon fine
        if (optionalPrenotazione.isPresent()) {
            return ResponseEntity.ok().body(optionalPrenotazione.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Aggiorna una prenotazione", description = "Quando gli si dà un id e una prenotazione usa i dati della prenotazione ricevuta per aggiornare la prenotazione nel database con l'id corrispondente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La prenotazione è stata trovata e aggiornata correttamente"),
            @ApiResponse(responseCode = "404", description = "L'id non è tra quelli delle prenotazioni nel database")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Prenotazione> updateById(
            @Parameter(name = "id", description = "id della prenotazione da aggiornare") @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "la prenotazione con i dati che servono per aggiornare") @RequestBody Prenotazione prenotazione
    ) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneService.updateById(id, prenotazione);
        // controllo se il metodo del service è andato a buon fine
        if (optionalPrenotazione.isPresent()) {
            return ResponseEntity.ok().body(optionalPrenotazione.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Cancella una prenotazione", description = "Quando gli si dà un id elimina dal database la prenotazione con l'id corrispondente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La prenotazione è stata trovata ed eliminata correttamente"),
            @ApiResponse(responseCode = "404", description = "L'id non è tra quelli delle prenotazioni nel database")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@Parameter(name = "id", description = "id della prenotazione da eliminare") @PathVariable Long id) {
        boolean wasThere = prenotazioneService.deleteById(id);
        // controllo se il metodo del service è andato a buon fine
        if (wasThere) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
