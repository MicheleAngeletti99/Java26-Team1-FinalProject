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
    // Campi
    @Autowired
    private PrenotazioneService prenotazioneService;

    // Metodi per il crud di base

    @Operation(summary = "Salva una prenotazione.", description = "Quando gli si dà una prenotazione crea un record con i suoi dati nel database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La prenotazione è stata aggiunta correttamente al database."),
            @ApiResponse(responseCode = "400", description = "La prenotazione data non è valida.")
    })
    @PostMapping("/create")
    public ResponseEntity<Prenotazione> create(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "La prenotazione da salvare nel database.") @RequestBody Prenotazione prenotazione) {
        Prenotazione responsePrenotazione = prenotazioneService.create(prenotazione);
        return ResponseEntity.ok().body(responsePrenotazione);
    }

    @Operation(summary = "Legge tutte le prenotazioni.", description = "Restituisce tutte le prenotazioni presenti nel database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le prenotazioni sono state lette correttamente.")
    })
    @GetMapping("/read-all")
    public ResponseEntity<List<Prenotazione>> readAll() {
        List<Prenotazione> prenotazioni = prenotazioneService.readAll();
        return ResponseEntity.ok().body(prenotazioni);
    }

    @Operation(summary = "Legge una prenotazione.", description = "Quando gli si dà un id restituisce la prenotazione nel database con l'id corrispondente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La prenotazione è stata trovata e restituita correttamente."),
            @ApiResponse(responseCode = "404", description = "L'id non è tra quelli delle prenotazioni nel database.")
    })
    @GetMapping("/read-one/{id}")
    public ResponseEntity<Prenotazione> readById(@Parameter(name = "id", description = "id della prenotazione da trovare e restituire.") @PathVariable Long id) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneService.readById(id);
        // controllo se il metodo del service è andato a buon fine
        if (optionalPrenotazione.isPresent()) {
            return ResponseEntity.ok().body(optionalPrenotazione.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Aggiorna una prenotazione.", description = "Quando gli si dà un id e una prenotazione usa i dati della prenotazione ricevuta per aggiornare la prenotazione nel database con l'id corrispondente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La prenotazione è stata trovata e aggiornata correttamente."),
            @ApiResponse(responseCode = "404", description = "L'id non è tra quelli delle prenotazioni nel database.")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Prenotazione> updateById(
            @Parameter(name = "id", description = "id della prenotazione da aggiornare.") @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "la prenotazione con i dati che servono per aggiornare.") @RequestBody Prenotazione prenotazione
    ) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneService.updateById(id, prenotazione);
        // controllo se il metodo del service è andato a buon fine
        if (optionalPrenotazione.isPresent()) {
            return ResponseEntity.ok().body(optionalPrenotazione.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Cancella una prenotazione.", description = "Quando gli si dà un id elimina dal database la prenotazione con l'id corrispondente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La prenotazione è stata trovata ed eliminata correttamente."),
            @ApiResponse(responseCode = "404", description = "L'id non è tra quelli delle prenotazioni nel database.")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@Parameter(name = "id", description = "id della prenotazione da eliminare.") @PathVariable Long id) {
        prenotazioneService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Metodi per le liste

    @Operation(summary = "Aggiunge un servizio.", description = "Quando gli si dà l'id di una prenotazione e l'id di un servizio aggiunge il servizio alla lista di servizi contenuti nella prenotazione con l'id corrispondente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Il servizio è stato aggiunto correttamente alla lista nella prenotazione."),
            @ApiResponse(responseCode = "404", description = "L'id della prenotazione non è tra quelli delle prenotazioni nel database.")
    })
    @PostMapping("{idPrenotazione}/add-servizio/{idServizio}")
    public ResponseEntity<Prenotazione> addServizio(
            @Parameter(name = "idPrenotazione", description = "id della prenotazione a cui aggiungere il servizio.") @PathVariable Long idPrenotazione,
            @Parameter(name = "idServizio", description = "id del servizio da aggiungere alla prenotazione.") @PathVariable Integer idServizio
    ) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneService.addServizio(idPrenotazione, idServizio);
        // controllo se il metodo del service è andato a buon fine
        if (optionalPrenotazione.isPresent()) {
            return ResponseEntity.ok().body(optionalPrenotazione.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Rimuove un servizio.", description = "Quando gli si dà l'id di una prenotazione e l'id di un servizio rimuove il servizio dalla lista di servizi contenuti nella prenotazione con l'id corrispondente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Il servizio è stato rimosso correttamente dalla lista nella prenotazione."),
            @ApiResponse(responseCode = "404", description = "L'id della prenotazione non è tra quelli delle prenotazioni nel database.")
    })
    @DeleteMapping("{idPrenotazione}/remove-servizio/{idServizio}")
    public ResponseEntity<Prenotazione> removeServizio(
            @Parameter(name = "idPrenotazione", description = "id della prenotazione da cui rimuovere il servizio.") @PathVariable Long idPrenotazione,
            @Parameter(name = "idServizio", description = "id del servizio da rimuovere dalla prenotazione.") @PathVariable Integer idServizio
    ) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneService.removeServizio(idPrenotazione, idServizio);
        // controllo se il metodo del service è andato a buon fine
        if (optionalPrenotazione.isPresent()) {
            return ResponseEntity.ok().body(optionalPrenotazione.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Mette in relazione una prenotazione e un ente.", description = "Quando gli si dà l'id di una prenotazione e l'id di un ente li mette in relazione senza sovrascrivere un possibile ente già in relazione con la prenotazione data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La prenotazione è in relazione con l'ente dato oppure, se presente, con l'ente con cui era già in relazione."),
            @ApiResponse(responseCode = "404", description = "L'id della prenotazione o l'id dell'ente non sono tra quelli nel database.")
    })
    @PutMapping("{idPrenotazione}/associate-ente/{idEnte}")
    public ResponseEntity<Prenotazione> associateEnte(Long idPrenotazione, Long idEnte) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneService.associateEnte(idPrenotazione, idEnte);
        // controllo se il metodo del service è andato a buon fine
        if (optionalPrenotazione.isPresent()) {
            return ResponseEntity.ok().body(optionalPrenotazione.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Cambia l'ente in relazione con una prenotazione.", description = "Quando gli si dà l'id di una prenotazione e l'id di un ente li mette in relazione sovrascrivendo un possibile ente già in relazione con la prenotazione data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La prenotazione è in relazione con l'ente dato."),
            @ApiResponse(responseCode = "404", description = "L'id della prenotazione o l'id dell'ente non sono tra quelli nel database.")
    })
    @PutMapping("{idPrenotazione}/change-associated-ente/{idEnte}")
    public ResponseEntity<Prenotazione> changeAssociatedEnte(Long idPrenotazione, Long idEnte) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneService.changeAssociatedEnte(idPrenotazione, idEnte);
        // controllo se il metodo del service è andato a buon fine
        if (optionalPrenotazione.isPresent()) {
            return ResponseEntity.ok().body(optionalPrenotazione.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
