package com.example.Java26_Team1_FinalProject.controllers;

import com.example.Java26_Team1_FinalProject.entities.Prenotazione;
import com.example.Java26_Team1_FinalProject.services.ClienteService;
import com.example.Java26_Team1_FinalProject.services.PrenotazioneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prenotazioni")
@Tag(name = "Prenotazione Controller", description = "L'interfaccia per gestire le prenotazioni.")
public class PrenotazioneController {
    // Campi
    @Autowired
    private PrenotazioneService prenotazioneService;
    @Autowired
    private ClienteService clienteService;

    // Metodi per il crud di base

    @Operation(summary = "Salva una prenotazione.", description = "Quando gli si dà una prenotazione, il cliente che fa la prenotazione e l'albergo a cui si vuole prenotare, crea un record con i suoi dati nel database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "La prenotazione è stata aggiunta correttamente al database."),
            @ApiResponse(responseCode = "400", description = "La prenotazione data non è valida."),
            @ApiResponse(responseCode = "404", description = "Il cliente o l'albergo non sono presenti nel database.")
    })
    @PostMapping("/create/{idCliente}/{idAlbergo}")
    public ResponseEntity<Prenotazione> create(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "La prenotazione da salvare nel database.") @RequestBody Prenotazione prenotazione,
                                               @Parameter(name = "idCliente", description = "L'id del cliente che fa la prenotazione.")@PathVariable Long idCliente,
                                               @Parameter(name = "idAlbergo", description = "L'id dell'albergo a cui si vuole prenotare.")@PathVariable Long idAlbergo) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneService.create(prenotazione, idCliente, idAlbergo);
        // controllo se il metodo del service è andato a buon fine
        if (optionalPrenotazione.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalPrenotazione.get());
        } else {
            return ResponseEntity.notFound().build();
        }
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
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "la prenotazione con i dati che servono per aggiornare.") @RequestBody Prenotazione prenotazione) {
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
            @ApiResponse(responseCode = "204", description = "La prenotazione è stata trovata ed eliminata correttamente."),
            @ApiResponse(responseCode = "404", description = "L'id non è tra quelli delle prenotazioni nel database.")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@Parameter(name = "id", description = "id della prenotazione da eliminare.") @PathVariable Long id) {
        prenotazioneService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Metodi per la lettura dei dati

    @Operation(summary = "Cerca le prenotazioni di un cliente.", description = "Quando gli si dà un id di un cliente, anche di uno eliminato (disattivato), cerca tutte le prenotazioni effettuate da quel cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le prenotazioni sono state trovate correttamente."),
            @ApiResponse(responseCode = "404", description = "L'id del cliente non esiste nel database.")
    })
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Prenotazione>> readByCliente(
            @Parameter(name = "idCliente", description = "id del cliente di cui si cercano le prenotazioni effettuate.") @PathVariable Long idCliente) {
        Optional<List<Prenotazione>> optionalPrenotazioni = prenotazioneService.findByCliente(idCliente);
        // controllo se il metodo del service è andato a buon fine
        if (optionalPrenotazioni.isPresent()) {
            return ResponseEntity.ok().body(optionalPrenotazioni.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Cerca le prenotazioni di un albergo.", description = "Quando gli si dà un id di un albergo, anche di uno eliminato (disattivato), cerca tutte le prenotazioni ricevute da quell'albergo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le prenotazioni sono state trovate correttamente."),
            @ApiResponse(responseCode = "404", description = "L'id dell'albergo non esiste nel database.")
    })
    @GetMapping("/albergo/{idAlbergo}")
    public ResponseEntity<List<Prenotazione>> readByAlbergo(
            @Parameter(name = "idAlbergo", description = "id dell'albergo di cui si cercano le prenotazioni ricevute") @PathVariable Long idAlbergo) {
        Optional<List<Prenotazione>> optionalPrenotazioni = prenotazioneService.findByAlbergo(idAlbergo);
        // controllo se il metodo del service è andato a buon fine
        if (optionalPrenotazioni.isPresent()) {
            return ResponseEntity.ok().body(optionalPrenotazioni.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Cerca le prenotazioni di un ente.", description = "Quando gli si dà un id di un ente, anche di uno eliminato (disattivato), cerca tutte le prenotazioni ricevute da quell'ente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le prenotazioni sono state trovate correttamente."),
            @ApiResponse(responseCode = "404", description = "L'id dell'ente non esiste nel database.")
    })
    @GetMapping("/ente/{idEnte}")
    public ResponseEntity<List<Prenotazione>> readByEnte(
            @Parameter(name = "idEnte", description = "id dell'ente di cui si cercano le prenotazioni ricevute") @PathVariable Long idEnte) {
        Optional<List<Prenotazione>> optionalPrenotazioni = prenotazioneService.findByEnte(idEnte);
        // controllo se il metodo del service è andato a buon fine
        if (optionalPrenotazioni.isPresent()) {
            return ResponseEntity.ok().body(optionalPrenotazioni.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Calcola la spesa totale di un cliente.", description = "Quando gli si dà un id di un cliente, anche di uno eliminato (disattivato), calcola la spesa totale di tutte le prenotazioni effettuate da quel cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Il totale è stato calcolato correttamente."),
            @ApiResponse(responseCode = "404", description = "L'id del cliente non esiste nel database.")
    })
    @GetMapping("/spesa-cliente/{idCliente}")
    public ResponseEntity<Double> spesaTotaleCliente(
            @Parameter(name = "idCliente", description = "id del cliente di cui si cerca la spesa totale.") @PathVariable Long idCliente) {
        Optional<Double> optionalSpesa = prenotazioneService.spesaTotaleCliente(idCliente);
        // controllo se il metodo del service è andato a buon fine
        if (optionalSpesa.isPresent()) {
            return ResponseEntity.ok().body(optionalSpesa.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Calcola il guadagno totale di un albergo.", description = "Quando gli si dà un id di un albergo, anche di uno eliminato (disattivato), calcola il guadagno totale di tutte le prenotazioni ricevute da quell'albergo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Il totale è stato calcolato correttamente."),
            @ApiResponse(responseCode = "404", description = "L'id dell'albergo non esiste nel database.")
    })
    @GetMapping("/guadagno-albergo/{idAlbergo}")
    public ResponseEntity<Double> guadagnoTotaleAlbergo(
            @Parameter(name = "idAlbergo", description = "id dell'albergo di cui si cerca il guadagno totale.") @PathVariable Long idAlbergo) {
        Optional<Double> optionalGuadagno = prenotazioneService.guadagnoTotaleAlbergo(idAlbergo);
        // controllo se il metodo del service è andato a buon fine
        if (optionalGuadagno.isPresent()) {
            return ResponseEntity.ok().body(optionalGuadagno.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Calcola il guadagno totale di un ente.", description = "Quando gli si dà un id di un ente, anche di uno eliminato (disattivato), calcola il guadagno totale di tutte le prenotazioni ricevute da quell'ente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Il totale è stato calcolato correttamente."),
            @ApiResponse(responseCode = "404", description = "L'id dell'ente non esiste nel database.")
    })
    @GetMapping("/guadagno-ente/{idEnte}")
    public ResponseEntity<Double> guadagnoTotaleEnte(
            @Parameter(name = "idEnte", description = "id dell'ente di cui si cerca il guadagno totale.") @PathVariable Long idEnte) {
        Optional<Double> optionalGuadagno = prenotazioneService.guadagnoTotaleEnte(idEnte);
        // controllo se il metodo del service è andato a buon fine
        if (optionalGuadagno.isPresent()) {
            return ResponseEntity.ok().body(optionalGuadagno.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodi per le relazioni

    @Operation(summary = "Mette in relazione una prenotazione e un ente.", description = "Quando gli si dà l'id di una prenotazione e l'id di un ente li mette in relazione senza sovrascrivere un possibile ente già in relazione con la prenotazione data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La prenotazione è in relazione con l'ente dato oppure, se presente, con l'ente con cui era già in relazione."),
            @ApiResponse(responseCode = "404", description = "L'id della prenotazione o l'id dell'ente non sono tra quelli nel database.")
    })
    @PutMapping("{idPrenotazione}/add-ente/{idEnte}")
    public ResponseEntity<Prenotazione> addToEnte(@Parameter(name = "idPrenotazione", description = "L'id della prenotazione da mettere in relazione con l'ente.") @PathVariable Long idPrenotazione,
                                                  @Parameter(name = "idEnte", description = "L'id dell'ente da mettere in relazione con la prenotazione.")@PathVariable Long idEnte) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneService.addToEnte(idPrenotazione, idEnte);
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
    @PutMapping("{idPrenotazione}/change-ente/{idEnte}")
    public ResponseEntity<Prenotazione> changeEnte(@Parameter(name = "idPrenotazione", description = "L'id della prenotazione da mettere in relazione con l'ente.") @PathVariable Long idPrenotazione,
                                                   @Parameter(name = "idEnte", description = "L'id dell'ente da mettere in relazione con la prenotazione.")@PathVariable Long idEnte) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneService.changeEnte(idPrenotazione, idEnte);
        // controllo se il metodo del service è andato a buon fine
        if (optionalPrenotazione.isPresent()) {
            return ResponseEntity.ok().body(optionalPrenotazione.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Rimuove la relazione tra ente e prenotazione", description = "Quando gli si dà l'id di una prenotazione rimuove la relazione con l'ente associato alla prenotazione stessa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La prenotazione non è più in relazione con nessun ente."),
            @ApiResponse(responseCode = "404", description = "L'id della prenotazione non è tra quelli nel database.")
    })
    @PutMapping("{idPrenotazione}/remove-ente")
    public ResponseEntity<Prenotazione> removeEnte(@Parameter(name = "idPrenotazione", description = "L'id della prenotazione da cui si vuole rimuovere una relazione con l'ente associato.")@PathVariable Long idPrenotazione) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneService.removeEnte(idPrenotazione);
        // controllo se il metodo del service è andato a buon fine
        if (optionalPrenotazione.isPresent()) {
            return ResponseEntity.ok().body(optionalPrenotazione.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
