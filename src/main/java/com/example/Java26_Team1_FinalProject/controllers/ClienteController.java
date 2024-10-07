package com.example.Java26_Team1_FinalProject.controllers;

import com.example.Java26_Team1_FinalProject.entities.Cliente;
import com.example.Java26_Team1_FinalProject.entities.Prenotazione;
import com.example.Java26_Team1_FinalProject.entities.Recensione;
import com.example.Java26_Team1_FinalProject.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller per la gestione delle operazioni CRUD per l'entità Cliente.
 * Usa annotazioni Swagger per fornire documentazione dettagliata delle API.
 */
@RestController
@RequestMapping("/client")
@Tag(name = "Cliente Controller", description = "Gestisce le operazioni CRUD per i clienti")
public class ClienteController {

    // Inietta il servizio ClienteService per accedere ai metodi che gestiscono le operazioni del cliente
    @Autowired
    private ClienteService clienteService;

    /**
     * Restituisce una lista di tutti i clienti presenti nel database.
     *
     * @return una lista di oggetti Cliente o un errore.
     */
    @GetMapping("/all-clients")
    @Operation(summary = "Lista di tutti i clienti", description = "Restituisce una lista di tutti i clienti")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutti i clienti sono stati mostrati correttamente")
    })
    public ResponseEntity<List<Cliente>> listaClienti() {
        return ResponseEntity.ok(clienteService.getAllClienti());
    }

    /**
     * Trova un cliente tramite l'ID.
     * Restituisce un cliente se l'ID esiste, altrimenti restituisce errore 404.
     *
     * @param id è l'ID del cliente da cercare
     * @return il cliente trovato o una risposta 404
     */
    @GetMapping("/client/{id}")
    @Operation(summary = "Trova il cliente per ID", description = "Restituisce un cliente tramite il suo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ID corretto, cliente trovato"),
            @ApiResponse(responseCode = "404", description = "ID errato, cliente non trovato")
    })
    public ResponseEntity<Cliente> findClienteById(@PathVariable Long id) {
        Optional<Cliente> optionalCliente = clienteService.getClienteById(id);
        if (optionalCliente.isPresent()) {
            return ResponseEntity.ok(optionalCliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Crea un nuovo cliente e lo salva nel database.
     * Restituisce il cliente creato con 201 (Created).
     *
     * @param cliente l'oggetto Cliente da creare e salvare.
     * @return il cliente creato e una risposta con codice 201 (Created).
     */
    @PostMapping("/new-client")
    @Operation(summary = "Crea un nuovo cliente", description = "Aggiunge un nuovo cliente nel database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creato correttamente e salvato nel database"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        //salva nel DB un nuovo cliente
        Cliente nuovoCliente = clienteService.createCliente(cliente);
        //la responce sarà 201 CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovoCliente);
    }


    /**
     * Aggiorna un cliente esistente tramite il suo ID.
     * Se il cliente viene trovato, aggiorna le sue informazioni, altrimenti restituisce 404.
     *
     * @param id  l'ID del cliente da aggiornare.
     * @param cliente il cliente con le nuove informazioni da aggiornare.
     * @return il cliente aggiornato restituisce 200 , se il cliente non è stato trovato la risposta sarà 404.
     */
    @PutMapping("/modify/{id}")
    @Operation(summary = "Aggiorna un cliente", description = "Aggiorna le informazioni di un cliente esistente dato il suo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente aggiornato correttamente"),
            @ApiResponse(responseCode = "404", description = "ID fornito non valido, cliente non trovato")
    })
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> optionalCliente = clienteService.updateCliente(id, cliente);
        // Se il cliente esiste, restituisce il cliente aggiornato
        if (optionalCliente.isPresent()) {
            return ResponseEntity.ok(optionalCliente.get());
        }
        // Restituisce 404 se il cliente non è stato trovato
        return ResponseEntity.notFound().build();
    }


    /**
     * Elimina un cliente dal database tramite il suo ID.
     * Se l'eliminazione è avvenuta con successo, restituisce 204 (No Content).
     * Se il cliente non esiste, restituisce 404.
     *
     * @param id l'ID del cliente da eliminare
     * @return una risposta con 204 (No Content) se l'eliminazione è riuscita, o 404 se non è stato trovato.
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Elimina un cliente", description = "Elimina un cliente dal database dato il suo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente trovato tramite ID ed eliminato correttamente"),
            @ApiResponse(responseCode = "404", description = "ID non presente nel database")
    })
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        boolean deletedClienteById = clienteService.deleteClienteById(id);
        // Restituisce 204 No Content se l'eliminazione ha avuto successo
        if (deletedClienteById) {
            return ResponseEntity.noContent().build();
        } else {
            // Restituisce 404 Not Found se il cliente non è stato trovato
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Aggiunge una carta di pagamento al cliente specificato.
     *
     * @param idCliente l'ID del cliente a cui aggiungere la carta di pagamento
     * @param carta la carta di pagamento da aggiungere
     * @return ResponseEntity con lo stato dell'operazione (200 OK se aggiunta con successo, 404 Not Found se il cliente non esiste)
     */
    @PostMapping("/{idCliente}/carte-di-pagamento")
    @Operation(summary = "aggiunge una carta di pagamento", description = "aggiunge una carta di pagamento alla lista delle carte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "la carta è stata aggiunta correttamente"),
            @ApiResponse(responseCode = "404", description = "ID cliente non trovato, carta non aggiunta")
    })
    public ResponseEntity<Cliente> addCartaDiPagamento(@PathVariable Long idCliente, @RequestBody String carta){
        Optional<Cliente> clienteOpt = clienteService.addCartaDiPagamento(idCliente,carta);
        if (clienteOpt.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteOpt.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Rimuove una carta di pagamento dal cliente specificato.
     *
     * @param idCliente l'ID del cliente da cui rimuovere la carta di pagamento
     * @param carta la carta di pagamento da rimuovere
     * @return ResponseEntity con lo stato dell'operazione (200 OK se rimossa con successo, 404 Not Found se il cliente non esiste)
     */
    @DeleteMapping("/{idCliente}/carte-di-pagamento")
    @Operation(summary = "rimuove le carte di pagamento", description = "rimuove la carta di pagamento dalla lista delle carte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "la carta è stata rimossa correttamente"),
            @ApiResponse(responseCode = "404", description = "ID cliente non trovato, carta non rimossa")
    })
    public ResponseEntity<Void> removeCartaDiPagamento(@PathVariable Long idCliente, @RequestBody String carta) {
        boolean isRemoved = clienteService.deleteCartaDiPagamento(idCliente, carta);
        if (isRemoved) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found se la carta non è stata trovata o il cliente non esiste
        }
    }

    /**
     * Aggiunge una prenotazione al cliente specificato.
     *
     * @param idCliente l'ID del cliente al quale aggiungere la prenotazione
     * @param prenotazione la prenotazione da aggiungere
     * @return ResponseEntity con il cliente aggiornato e lo stato dell'operazione (200 OK se aggiunta con successo, 404 Not Found se il cliente non esiste)
     */
    @PostMapping("/{idCliente}/prenotazioni/{idAlbergo}")
    @Operation(summary = "aggiunge una prenotazione", description = "aggiunge una prenotazione alla lista delle prenotazioni")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "la prenotazione è stata aggiunta correttamente"),
            @ApiResponse(responseCode = "404", description = "ID cliente non trovato, prenotazione non aggiunta")
    })
    public ResponseEntity<Prenotazione> addPrenotazione(@PathVariable Long idCliente, @PathVariable Long idAlbergo, @RequestBody Prenotazione prenotazione) {
        Optional<Prenotazione> addedPrenotazione = clienteService.addPrenotazione(idCliente, idAlbergo, prenotazione);
        if (addedPrenotazione.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(addedPrenotazione.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Rimuove una prenotazione specificatoa
     *
     * @param idPrenotazione l'ID della prenotazione da rimuovere
     * @return ResponseEntity con lo stato dell'operazione (200 OK se rimossa con successo, 404 Not Found se la prenotazione non esiste)
     */
    @DeleteMapping("/prenotazioni/{idPrenotazione}")
    @Operation(summary = "rimuove una prenotazione", description = "rimuove una prenotazione dalla lista delle prenotazioni")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "la prenotazione è stata rimossa correttamente"),
            @ApiResponse(responseCode = "404", description = "ID prenotazione non trovato")
    })
    public ResponseEntity<Void> removePrenotazione(@PathVariable Long idPrenotazione) {
        boolean isRemoved = clienteService.removePrenotazione(idPrenotazione);
        if (isRemoved) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found se la prenotazione non è stata trovata
        }
    }
}
