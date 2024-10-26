package com.example.Java26_Team1_FinalProject.controllers;

import com.example.Java26_Team1_FinalProject.entities.Ente;
import com.example.Java26_Team1_FinalProject.entities.Servizi;
import com.example.Java26_Team1_FinalProject.services.ServiziServices;
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
}


