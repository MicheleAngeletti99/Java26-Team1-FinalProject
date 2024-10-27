package com.example.Java26_Team1_FinalProject.controllers;

import com.example.Java26_Team1_FinalProject.entities.Albergo;
import com.example.Java26_Team1_FinalProject.services.AlbergoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Albergo")
public class AlbergoController {
    // parametro
    @Autowired
    private AlbergoService albergoService;

    // GET per prendere lenco dei alberghi
    @GetMapping("/elencoalberghi")
    @Operation(summary = "Lista di tutti gli alberghi", description = "Restituisce una lista di tutti gli alberghi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutti gli alberghi sono stati mostrati correttamente")
    })
    public ResponseEntity<List<Albergo>> elencoAlberghi(){
        return ResponseEntity.ok(albergoService.elencoAlberghi());
    }

    // GET per prendere un albergo tramite ID
    @GetMapping("/albergo/{id}")
    @Operation(summary = "Trova l'albergo per ID", description = "Restituisce un albergo tramite il suo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ID corretto, albergo trovato"),
            @ApiResponse(responseCode = "404", description = "ID errato, albergo non trovato")
    })
    public ResponseEntity<Albergo> getAlbergoTramiteId(@PathVariable Long id){
        Optional<Albergo> albergoOptional = albergoService.getAlbergoById(id);
        if(albergoOptional.isPresent()) {

            return ResponseEntity.ok(albergoOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    // POST per creare un nuovo albergo
    @PostMapping("/newalbergo")
    @Operation(summary = "Crea un nuovo albergo", description = "Aggiunge un nuovo albergo nel database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Albergo creato correttamente e salvato nel database"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    public ResponseEntity<Albergo> newAlbergo(@RequestBody Albergo albergo){
        Albergo newAlbergo = albergoService.aggiungiAlbergo(albergo);
        return ResponseEntity.ok(newAlbergo);
    }

    // PUT per modificare
    @PutMapping("/modificaalbergo/{id}")
    @Operation(summary = "Aggiorna un albergo", description = "Aggiorna le informazioni di un albergo esistente dato il suo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Albergo aggiornato correttamente"),
            @ApiResponse(responseCode = "404", description = "ID fornito non valido, albergo non trovato")
    })
    public ResponseEntity<Albergo> modificaalbergo(@PathVariable Long id, @RequestBody Albergo albergo){
        Optional<Albergo> optionalAlbergo = albergoService.modificareAlbergo(id,albergo);
        if(optionalAlbergo.isPresent()){
            return ResponseEntity.ok(optionalAlbergo.get());
        }
        return ResponseEntity.notFound().build();
    }
    //DELETE per eliminare
    @DeleteMapping("/eliminareAlbergo/{id}")
    @Operation(summary = "Elimina un albergo", description = "Elimina un albergo dal database dato il suo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Albergo trovato tramite ID ed eliminato correttamente"),
            @ApiResponse(responseCode = "404", description = "ID non presente nel database")
    })
    public ResponseEntity<Void> eliminareAlbergo(@PathVariable Long id){
        boolean rimuoviAlbergo = albergoService.rimuovereAlbergo(id);
          if(rimuoviAlbergo){
              return ResponseEntity.noContent().build();
          }
          return ResponseEntity.notFound().build();
    }

    // Metodi per la lettura dei dati

    @Operation(summary = "Cerca gli alberghi con un nome.", description = "Quando gli si dà un nome cerca gli alberghi il cui nome contiene quello dato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gli alberghi sono stati trovati correttamente."),
    })
    @GetMapping("/nome")
    public ResponseEntity<List<Albergo>> readByNome(@RequestParam String nome) {
        List<Albergo> alberghi = albergoService.readByNome(nome);
        return ResponseEntity.ok(alberghi);
    }

    @Operation(summary = "Cerca gli alberghi di una città.", description = "Quando gli si dà una città cerca gli alberghi di quella città.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gli alberghi sono stati trovati correttamente."),
    })
    @GetMapping("/citta")
    public ResponseEntity<List<Albergo>> readByCitta(@RequestParam String citta) {
        List<Albergo> alberghi = albergoService.readByCitta(citta);
        return ResponseEntity.ok(alberghi);
    }
}
