package com.example.Java26_Team1_FinalProject.controllers;
import com.example.Java26_Team1_FinalProject.entities.Albergo;
import com.example.Java26_Team1_FinalProject.entities.Ente;
import com.example.Java26_Team1_FinalProject.services.EnteServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ente")
public class EnteController {

    @Autowired
    private EnteServices enteServices;

    //get per prendere la lsita di enti
    @GetMapping("/elenco-enti")
    @Operation(summary = "Lista di tutti gli enti", description = "Restituisce una lista di tutti gli enti")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutti gli enti sono stati mostrati correttamente")
    })
    public ResponseEntity<List<Ente>> elencoEnti(){
        return ResponseEntity.ok(enteServices.elencoEnti());
    }

    //post per creare un ente
    @PostMapping("/new-ente")
    @Operation(summary = "Crea un nuovo ente", description = "Aggiunge un nuovo ente nel database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ente creato correttamente e salvato nel database"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    public ResponseEntity<Ente> newEnte(@RequestBody Ente ente){
        Ente addEnte = enteServices.addEnte(ente);
        return ResponseEntity.ok(addEnte);
    }

    //delete del ente
    @DeleteMapping("/eliminare-ente/{id}")
    @Operation(summary = "Elimina un'ente", description = "Elimina un'ente dal database dato il suo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ente trovato tramite ID ed eliminato correttamente"),
            @ApiResponse(responseCode = "404", description = "ID non presente nel database")
    })
    public ResponseEntity<Void> eliminareEnte(@PathVariable Long id){
        boolean rimuoviAlbergo = enteServices.deleteEnte(id);
        if(rimuoviAlbergo){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //modifica ente
    @PutMapping("/modifica-ente/{id}")
    @Operation(summary = "Aggiorna un'ente", description = "Aggiorna le informazioni di un'ente esistente dato il suo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ente aggiornato correttamente"),
            @ApiResponse(responseCode = "404", description = "ID fornito non valido, ente non trovato")
    })
    public ResponseEntity<Ente> modificaEnte(@PathVariable Long id, @RequestBody Ente ente){
        Optional<Ente> enteOptional = enteServices.modificaEnte(id,ente);
        if (enteOptional.isPresent()){
            return ResponseEntity.ok(enteOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/ente/{id}")
    @Operation(summary = "Trova l'ente per ID", description = "Restituisce un'ente tramite il suo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ID corretto, ente trovato"),
            @ApiResponse(responseCode = "404", description = "ID errato, ente non trovato")
    })
    public ResponseEntity<Ente> getEnteById(@PathVariable Long id){
        Optional<Ente> enteOptional = enteServices.getEnteById(id);
        if(enteOptional.isPresent()) {
            return ResponseEntity.ok(enteOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Metodi per la lettura dei dati

    @Operation(summary = "Cerca gli enti con un nome.", description = "Quando gli si dà un nome cerca gli enti il cui nome contiene quello dato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gli enti sono stati trovati correttamente."),
    })
    @GetMapping("/nome")
    public ResponseEntity<List<Ente>> readByNome(@RequestParam String nome) {
        List<Ente> enti = enteServices.readByNome(nome);
        return ResponseEntity.ok(enti);
    }

    @Operation(summary = "Cerca gli enti di una città.", description = "Quando gli si dà una città cerca gli enti di quella città.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gli enti sono stati trovati correttamente."),
    })
    @GetMapping("/citta")
    public ResponseEntity<List<Ente>> readByCitta(@RequestParam String citta) {
        List<Ente> enti = enteServices.readByCitta(citta);
        return ResponseEntity.ok(enti);
    }
}
