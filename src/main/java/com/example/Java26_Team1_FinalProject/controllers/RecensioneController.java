package com.example.Java26_Team1_FinalProject.controllers;
import com.example.Java26_Team1_FinalProject.entities.Recensione;
import com.example.Java26_Team1_FinalProject.services.RecensioneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller per la gestione delle operazioni CRUD per l'entità Recensione.
 * Usa annotazioni Swagger per fornire documentazione dettagliata delle API.
 */
@RestController
@RequestMapping("/recensione")
public class RecensioneController {

    @Autowired
    private RecensioneService recensioneService;
    /**
     * Restituisce una lista di tutte le recensioni presenti nel database.
     *
     * @return una lista di oggetti recensioni o un errore.
     */
    @GetMapping("/all-recensioni")
    @Operation(summary = "Lista di tutte le recensioni", description = "Restituisce una lista di tutte le recensioni")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutte le receensioni sono state mostrate correttamente")
    })
    public ResponseEntity<List<Recensione>> listaRecensioni() {
        return ResponseEntity.ok(recensioneService.elencoRecensioni());
    }


    /**
     * Trova una recensione tramite l'ID.
     * Restituisce una recensione se l'ID esiste, altrimenti restituisce errore 404.
     *
     * @param id è l'ID della recensione da cercare
     * @return la recensionee trovata o una risposta 404
     */
    @GetMapping("/recensione/{id}")
    @Operation(summary = "Trova la recensione per ID", description = "Restituisce una recensione tramite il suo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ID corretto, recensione trovata"),
            @ApiResponse(responseCode = "404", description = "ID errato, recensione non trovata")
    })
    public ResponseEntity<Recensione> findRecensioneById(@PathVariable Long id) {
        Optional<Recensione> optionalRecensione = recensioneService.getRecensioniById(id);
        if (optionalRecensione.isPresent()) {
            return ResponseEntity.ok(optionalRecensione.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Crea una nuova recensione e la salva nel database.
     * Restituisce la recensione creata con 201 (Created).
     *
     * @param recensione l'oggetto Cliente da creare e salvare.
     * @return la recensione creata e una risposta con codice 201 (Created).
     */
    @PostMapping("{idPrenotazione}/new-recensione")
    @Operation(summary = "Crea una nuova recensione", description = "Aggiunge una nuova recensione nel database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recensione creata correttamente e salvata nel database"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    public ResponseEntity<Recensione> createRecensione(@PathVariable Long idPrenotazione, @RequestBody Recensione recensione) {
        //salva nel DB una nuova recensione
        Optional<Recensione> nuovaRecensione = recensioneService.aggiungiRecensione(idPrenotazione,recensione);
        if (nuovaRecensione.isPresent()) {
            //la responce sarà 201 CREATED
            return ResponseEntity.status(HttpStatus.CREATED).body(nuovaRecensione.get());
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Aggiorna la recensione esistente tramite il suo ID.
     * Se la recensione viene trovata, aggiorna le sue informazioni, altrimenti restituisce 404.
     *
     * @param id  l'ID della recensione da aggiornare.
     * @param recensione la recensione con le nuove informazioni da aggiornare.
     * @return la recensione aggiornata restituisce 200 , se la recensione non è stato trovato la risposta sarà 404.
     */
    @PutMapping("/modify/{id}")
    @Operation(summary = "Aggiorna una recensione", description = "Aggiorna le informazioni di una recensione esistente dato il suo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recensione aggiornata correttamente"),
            @ApiResponse(responseCode = "404", description = "ID fornito non valido, recensione non trovata")
    })
    public ResponseEntity<Recensione> updateRecensione(@PathVariable Long id, @RequestBody Recensione recensione) {
        Optional<Recensione> optionalRecensione = recensioneService.modificareRecensioni(id, recensione);
        // Se la recensione esiste, restituisce la recensione aggiornata
        if (optionalRecensione.isPresent()) {
            return ResponseEntity.ok(optionalRecensione.get());
        }
        // Restituisce 404 se la recensione non è stato trovato
        return ResponseEntity.notFound().build();
    }
    /**
     * Elimina una recensione dal database tramite il suo ID.
     * Se l'eliminazione è avvenuta con successo, restituisce 204 (No Content).
     * Se la recensione non esiste, restituisce 404.
     *
     * @param id l'ID della recensione da eliminare
     * @return una risposta con 204 (No Content) se l'eliminazione è riuscita, o 404 se non è stato trovato.
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Elimina una recensione", description = "Elimina una recensione dal database dato il suo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Recensione trovata tramite ID ed eliminato correttamente"),
            @ApiResponse(responseCode = "404", description = "ID non presente nel database")
    })
    public ResponseEntity<Void> deleteRecensione(@PathVariable Long id) {
        boolean deletedRecensioneById = recensioneService.deleteRecensioneById(id);
        // Restituisce 204 No Content se l'eliminazione ha avuto successo
        if (deletedRecensioneById) {
            return ResponseEntity.noContent().build();
        } else {
            // Restituisce 404 Not Found se la recensione non è stata trovata
            return ResponseEntity.notFound().build();
        }
    }


    }



