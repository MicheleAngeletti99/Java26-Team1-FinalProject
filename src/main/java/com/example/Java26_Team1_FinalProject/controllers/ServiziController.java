package com.example.Java26_Team1_FinalProject.controllers;

import com.example.Java26_Team1_FinalProject.entities.Ente;
import com.example.Java26_Team1_FinalProject.entities.Servizi;
import com.example.Java26_Team1_FinalProject.services.ServiziServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servizi")
public class ServiziController {
    // parameters
    @Autowired
    private ServiziServices services;


    // METODO CRUD
    // POST
    @PostMapping("/new-servizio")
    public ResponseEntity<Servizi> newServizio(@RequestBody Servizi servizio) {
        Servizi newServizio = services.createService(servizio);
        return ResponseEntity.ok(newServizio);
    }

    //GET  by ID
    // using an optional in the controller because you're checking whether the entity was found
    // if not found without the optional it will give you a null
    @GetMapping("/{id}")
    public ResponseEntity<Servizi> getServizio(@PathVariable Long id) {
        Optional<Servizi> serviziOptional = services.visualizzaServizio(id);
        if (serviziOptional.isPresent()) {
            return ResponseEntity.ok(serviziOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    // GET
    @GetMapping("/elenco-servizi")
    public ResponseEntity<List<Servizi>> elencoServizi() {
        return ResponseEntity.ok(services.elencoServizi());
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Servizi> modificareServizio(@PathVariable Long id,@RequestBody Servizi servizio) {
        Optional<Servizi> serviziOptional = services.modificareServizi(id, servizio);
        if (serviziOptional.isPresent()) {
            return ResponseEntity.ok(serviziOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServizio(@PathVariable Long id) {
        services.eliminareServizio(id);
        return ResponseEntity.ok().build();
    }

    // metodi per le relazione

    // aggiungere relazione tra prenotazione e servizio
    @PutMapping("/add-relazione-prenotazione/{idServizio}/{idPrenotazione}")
    public ResponseEntity<String> addRelazionePrenotazione(@PathVariable Long idServizio, @PathVariable Long idPrenotazione) {
        String addPrenotazione = services.addPrenotazione(idPrenotazione, idServizio);
        return ResponseEntity.ok(addPrenotazione);
    }

    // rimuovere relazione tra prenotazione e servizio
    @PutMapping("/remove-relazione-prenotazione/{idServizio}/{idPrenotazione}")
    public ResponseEntity<String> removeRelazionePrenotazione(@PathVariable Long idServizio, @PathVariable Long idPrenotazione) {
        String removedPrenotazione = services.removePrenotazioneById(idServizio, idPrenotazione);
        return ResponseEntity.ok(removedPrenotazione);
    }

    // metodi per creare una relazione tra Ente e servizio
    @PostMapping("/create-servizio-ente/{idEnte}")
    public ResponseEntity<Servizi> addRelazioneServizioEnte(@PathVariable Long idEnte, @RequestBody Servizi servizio) {
        Optional<Servizi> serviziOptional = services.createFromEnte(idEnte, servizio);
        if (serviziOptional.isPresent()) {
            return ResponseEntity.ok(serviziOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // metodo per creare una relazione tra albergo e servizio
    @PostMapping("/create-servizio-albergo/{idAlbergo}")
    public ResponseEntity<Servizi> addRelazioneServizioAlbergo(@PathVariable Long idAlbergo, @RequestBody Servizi servizio) {
        Optional<Servizi> serviziOptional = services.createFromAlbergo(idAlbergo, servizio);
        if (serviziOptional.isPresent()) {
            return ResponseEntity.ok(serviziOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Metodi per la lettura dei dati

    @Operation(summary = "Cerca i servizi con un nome.", description = "Quando gli si dà un nome cerca tutti i servizi il cui nome contiene quello dato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "I servizi sono stati trovati correttamente.")
    })
    @GetMapping("/nome")
    public ResponseEntity<List<Servizi>> readByNome(@RequestParam String nome) {
        List<Servizi> servizi = services.readByNome(nome);
        return ResponseEntity.ok().body(servizi);
    }

    @Operation(summary = "Cerca i servizi di una prenotazione.", description = "Quando gli si dà un id di una prenotazione, anche di una eliminata (disattivata), cerca tutti i servizi di quella prenotazione, anche quelli eliminati (disattivati).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "I servizi sono stati trovati correttamente."),
            @ApiResponse(responseCode = "404", description = "L'id della prenotazione non esiste nel database.")
    })
    @GetMapping("/prenotazione/{idPrenotazione}")
    public ResponseEntity<List<Servizi>> readByPrenotazione(@PathVariable Long idPrenotazione) {
        Optional<List<Servizi>> optionalServizi = services.readByPrenotazione(idPrenotazione);
        // controllo che il metodo del service sia andato a buon fine
        if (optionalServizi.isPresent()) {
            return ResponseEntity.ok().body(optionalServizi.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Cerca i servizi di un albergo.", description = "Quando gli si dà un id di un albergo cerca tutti i servizi di quell'albergo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "I servizi sono stati trovati correttamente."),
            @ApiResponse(responseCode = "404", description = "L'albergo non è nel database.")
    })
    @GetMapping("/albergo/{idAlbergo}")
    public ResponseEntity<List<Servizi>> readByAlbergo(@PathVariable Long idAlbergo) {
        Optional<List<Servizi>> optionalServizi = services.readByAlbergo(idAlbergo);
        // controllo che il metodo del service sia andato a buon fine
        if (optionalServizi.isPresent()) {
            return ResponseEntity.ok().body(optionalServizi.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Cerca i servizi di un ente.", description = "Quando gli si dà un id di un ente cerca tutti i servizi di quell'ente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "I servizi sono stati trovati correttamente."),
            @ApiResponse(responseCode = "404", description = "L'ente non è nel database.")
    })
    @GetMapping("/ente/{idEnte}")
    public ResponseEntity<List<Servizi>> readByEnte(@PathVariable Long idEnte) {
        Optional<List<Servizi>> optionalServizi = services.readByEnte(idEnte);
        // controllo che il metodo del service sia andato a buon fine
        if (optionalServizi.isPresent()) {
            return ResponseEntity.ok().body(optionalServizi.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


