package com.example.Java26_Team1_FinalProject.controllers;


import com.example.Java26_Team1_FinalProject.entities.CartaDiPagamento;
import com.example.Java26_Team1_FinalProject.services.CartaDiPagamentoService;
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
 * Controller REST per gestire le operazioni CRUD relative alle Carte di Pagamento.
 */
@RestController
@RequestMapping("/carte-di-pagamento")
public class CartaDiPagamentoController {

    @Autowired
    private CartaDiPagamentoService cartaDiPagamentoService;

    /**
     * Restituisce tutte le carte di pagamento.
     *
     * @return una lista di tutte le carte di pagamento presenti nel sistema
     */
    @Operation(summary = "Recupera tutte le carte di pagamento.",
            description = "Restituisce una lista completa di tutte le carte di pagamento presenti nel sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione avvenuta con successo.")
    })
    @GetMapping("/all-carte")
    public ResponseEntity<List<CartaDiPagamento>> getAllCarte() {
        return ResponseEntity.ok(cartaDiPagamentoService.getAllCarte());
    }

    /**
     * Recupera una carta di pagamento specifica tramite il suo ID.
     *
     * @param id ID della carta di pagamento da cercare
     * @return la carta di pagamento corrispondente o un errore 404 se non trovata
     */
    @Operation(summary = "Trova una carta di pagamento per ID.",
            description = "Restituisce i dettagli di una carta di pagamento corrispondente al suo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carta di pagamento trovata."),
            @ApiResponse(responseCode = "404", description = "Carta di pagamento non trovata.")
    })
    @GetMapping("/{id}/carta")
    public ResponseEntity<CartaDiPagamento> findByid(@PathVariable Long id) {
        Optional<CartaDiPagamento> optionalCartaDiPagamento = cartaDiPagamentoService.findCartaById(id);
        if (optionalCartaDiPagamento.isPresent()) {
            return ResponseEntity.ok(optionalCartaDiPagamento.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Crea una nuova carta di pagamento.
     *
     * @param carta la nuova carta di pagamento da creare
     * @return la carta di pagamento creata
     */
    @Operation(summary = "Crea una nuova carta di pagamento.",
            description = "Quando viene fornita una carta di pagamento, questa viene salvata nel database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carta di pagamento creata con successo."),
            @ApiResponse(responseCode = "400", description = "Dati della carta non validi.")
    })
    @PostMapping("/create-carta")
    public ResponseEntity<CartaDiPagamento> createCarta(@RequestBody CartaDiPagamento carta) {
        CartaDiPagamento nuovaCarta = cartaDiPagamentoService.createCarta(carta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovaCarta);
    }

    /**
     * Aggiorna una carta di pagamento esistente.
     *
     * @param id ID della carta di pagamento da aggiornare
     * @param cartaDiPagamento i nuovi dati della carta di pagamento
     * @return la carta di pagamento aggiornata o un errore 404 se non trovata
     */
    @Operation(summary = "Aggiorna una carta di pagamento.",
            description = "Modifica i dettagli di una carta di pagamento esistente identificata dal suo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carta di pagamento aggiornata con successo."),
            @ApiResponse(responseCode = "404", description = "Carta di pagamento non trovata."),
            @ApiResponse(responseCode = "400", description = "Dati della carta non validi.")
    })
    @PutMapping("/modify/{id}")
    public ResponseEntity<CartaDiPagamento> updateCarta(@PathVariable Long id, @RequestBody CartaDiPagamento cartaDiPagamento) {
        Optional<CartaDiPagamento> optionalCartaDiPagamento = cartaDiPagamentoService.updateCarta(id, cartaDiPagamento);
        if (optionalCartaDiPagamento.isPresent()) {
            return ResponseEntity.ok(optionalCartaDiPagamento.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Elimina una carta di pagamento.
     *
     * @param id ID della carta di pagamento da eliminare
     * @return 204 se l'eliminazione ha successo, 404 se non trovata
     */
    @Operation(summary = "Elimina una carta di pagamento.",
            description = "Rimuove una carta di pagamento dal sistema identificata dal suo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Carta di pagamento eliminata con successo."),
            @ApiResponse(responseCode = "404", description = "Carta di pagamento non trovata.")
    })
    @DeleteMapping("/delete-carta/{id}")
    public ResponseEntity<Void> deleteCarta(@PathVariable Long id) {
        boolean deletedCartaById = cartaDiPagamentoService.deleteCarta(id);
        if (deletedCartaById) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Aggiunge una carta di pagamento al cliente specificato.
     *
     * @param idCliente l'ID del cliente a cui aggiungere la carta di pagamento
     * @param idCarta   la carta di pagamento da aggiungere
     * @return ResponseEntity con lo stato dell'operazione (200 OK se aggiunta con successo, 404 Not Found se il cliente non esiste)
     */
    @PostMapping("/{idCliente}/add-carta/{idCarta}")
    @Operation(summary = "aggiunge una carta di pagamento", description = "aggiunge una carta di pagamento alla lista delle carte di un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "la carta è stata aggiunta correttamente"),
            @ApiResponse(responseCode = "404", description = "ID cliente non trovato, carta non aggiunta")
    })
    public ResponseEntity<CartaDiPagamento> addCartaDiPagamento(@PathVariable Long idCliente, @PathVariable Long idCarta){
        //Aggiunge la carta al cliente se presente
        Optional<CartaDiPagamento> cartaDiPagamentoOptional = cartaDiPagamentoService.addToClient(idCliente, idCarta);
        if (cartaDiPagamentoOptional.isPresent()){
            return ResponseEntity.ok().body(cartaDiPagamentoOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Rimuove una carta di pagamento dal cliente specificato.
     *
     * @param idCarta la carta di pagamento da rimuovere dal cliente
     * @return ResponseEntity con lo stato dell'operazione (204 OK se rimossa con successo, 404 Not Found se il cliente non esiste)
     */@Operation(summary = "rimuove la carta di pagamento", description = "rimuove la carta di pagamento dal cliente specifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "la carta è stata rimossa correttamente"),
            @ApiResponse(responseCode = "404", description = "ID cliente non trovato, carta non rimossa")
    })
    @DeleteMapping("/delete/{idCarta}")
    public ResponseEntity<Void> deleteCartaToClient(@PathVariable Long idCarta) {
        boolean deletedCartaById = cartaDiPagamentoService.deleteCartaToClient(idCarta);
        if (deletedCartaById) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Cerca le carte di un cliente.", description = "Quando gli si dà l'id di un cliente cerca tutte le carte di pagamento di quel cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le carte sono state trovate correttamente."),
            @ApiResponse(responseCode = "404", description = "L'id del cliente non è nel database.")
    })
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<CartaDiPagamento>> readByCliente(@PathVariable Long idCliente) {
         Optional<List<CartaDiPagamento>> optionalCarte = cartaDiPagamentoService.readByCliente(idCliente);
         // controllo che il metodo del service sia andato a buon fine
        if (optionalCarte.isPresent()) {
            return ResponseEntity.ok().body(optionalCarte.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


