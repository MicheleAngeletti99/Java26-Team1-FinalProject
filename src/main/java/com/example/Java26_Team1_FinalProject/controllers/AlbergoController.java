package com.example.Java26_Team1_FinalProject.controllers;

import com.example.Java26_Team1_FinalProject.entities.Albergo;
import com.example.Java26_Team1_FinalProject.entities.Prenotazione;
import com.example.Java26_Team1_FinalProject.enums.ServizioEnum;
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

    // POST per aggiungere un servizio alla lista specificato dal Albergo
    @PostMapping("/servizi/{idAlbergo}/{servizio}")
    @Operation(summary = "Aggiunge un servizio all'albergo", description = "Aggiunge un servizio alla lista dei servizi messi a disposizione dall'albergo di cui viene fornito l'id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servizio aggiunto correttamente alla lista dell'albergo"),
            @ApiResponse(responseCode = "404", description = "ID non presente nel database")
    })
    public ResponseEntity<String> aggiungiServizi(@PathVariable Long  idAlbergo,@PathVariable Integer  servizio){
       boolean addServizio =  albergoService.aggiungiServizio(idAlbergo,servizio);
       if(addServizio){
           return ResponseEntity.ok().build();
       }
        return ResponseEntity.status(404).body("operazione ERRATO");
    }
    // DELETE per rimuovere un servizio  specificato dal Albergo
    @DeleteMapping("/servizi/{idAlbergo}/{servizio}")
    @Operation(summary = "Rimuove un servizio all'albergo", description = "Rimuove un servizio dalla lista dei servizi messi a disposizione dall'albergo di cui viene fornito l'id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servizio rimosso correttamente dalla lista dell'albergo"),
            @ApiResponse(responseCode = "404", description = "ID non presente nel database")
    })
    public ResponseEntity<String>  rimuoviServizio(@PathVariable Long idAlbergo,@PathVariable Integer servizio){
        boolean rimuoviServizio = albergoService.eliminaServizio(idAlbergo,servizio);
        if(rimuoviServizio){
            return  ResponseEntity.ok().build();
        }
        return ResponseEntity.status(404).build();
    }
}
