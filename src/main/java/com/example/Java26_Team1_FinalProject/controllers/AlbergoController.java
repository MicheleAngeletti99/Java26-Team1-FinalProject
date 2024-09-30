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
    public ResponseEntity<List<Albergo>> elencoAlberghi(){
        return ResponseEntity.ok(albergoService.elencoAlberghi());
    }

    // GET per prendere un albergo tramite ID
    @GetMapping("/albergo/{id}")
    public ResponseEntity<Albergo> getAlbergoTramiteId(@PathVariable Long id){
        Optional<Albergo> albergoOptional = albergoService.getAlbergoById(id);
        if(albergoOptional.isPresent()) {

            return ResponseEntity.ok(albergoOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    // POST per creare un nuovo albergo
    @PostMapping("/newalbergo")
    public ResponseEntity<Albergo> newAlbergo(@RequestBody Albergo albergo){
        Albergo newAlbergo = albergoService.aggiungiAlbergo(albergo);
        return ResponseEntity.ok(newAlbergo);
    }

    // PUT per modificare
    @PutMapping("/modificaalbergo/{id}")
    public ResponseEntity<Albergo> modificaalbergo(@PathVariable Long id, @RequestBody Albergo albergo){
        Optional<Albergo> optionalAlbergo = albergoService.modificareAlbergo(id,albergo);
        if(optionalAlbergo.isPresent()){
            return ResponseEntity.ok(optionalAlbergo.get());
        }
        return ResponseEntity.notFound().build();
    }
    //DELETE per eliminare
    @DeleteMapping("/eliminareAlbergo/{id}")
    public ResponseEntity<Void> eliminareAlbergo(@PathVariable Long id){
        boolean rimuoviAlbergo = albergoService.rimuovereAlbergo(id);
          if(rimuoviAlbergo){
              return ResponseEntity.noContent().build();
          }
          return ResponseEntity.notFound().build();
    }

    // Post per aggiungere un Prenotazione dalla lista specificato dal Albergo
    @PostMapping("/prenotazione/{idAlbergo}/{idPrenotazione}")
    @Operation(summary = "aggiunge una prenotazione", description = "aggiunge una prenotazione alla lista delle prenotazioni")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "la prenotazione è stata aggiunta correttamente"),
            @ApiResponse(responseCode = "404", description = "ID albergo non trovato, prenotazione non aggiunta")
    })
    public ResponseEntity<String> aggiungerePrenotazione(@PathVariable Long idAlbergo, @PathVariable Long idPrenotazione){
        albergoService.addPrenotazione(idAlbergo,idPrenotazione);
        return ResponseEntity.ok().build();
    }
    //DELETE per eliminare un Prenotazione dalla lista
    @DeleteMapping("/prenotazione/{idAlbergo}/{idPrenotazione}")
    @Operation(summary = "rimuove una prenotazione", description = "rimuove una prenotazione dalla lista delle prenotazioni")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "la prenotazione è stata rimossa correttamente"),
            @ApiResponse(responseCode = "404", description = "ID albergo non trovato, prenotazione non rimossa")
    })
    public ResponseEntity<String> eliminarePrenotazione(@PathVariable Long idAlbergo,@PathVariable Long idPrenotazione ){
       albergoService.removePrenotazione(idAlbergo, idPrenotazione);
        return ResponseEntity.ok().build();
    }

    // POST per aggiungere un servizio alla lista specificato dal Albergo
    @PostMapping("/servizi/{idAlbergo}/{servizio}")
    public ResponseEntity<String> aggiungiServizi(@PathVariable Long  idAlbergo,@PathVariable Integer  servizio){
       boolean addServizio =  albergoService.aggiungiServizio(idAlbergo,servizio);
       if(addServizio){
           return ResponseEntity.ok().build();
       }
        return ResponseEntity.status(404).body("operazione ERRATO");
    }
    // DELETE per rimuovere un servizio  specificato dal Albergo
    @DeleteMapping("/servizi/{idAlbergo}/{servizio}")
    public ResponseEntity<String>  rimuoviServizio(@PathVariable Long idAlbergo,@PathVariable Integer servizio){
        boolean rimuoviServizio = albergoService.eliminaServizio(idAlbergo,servizio);
        if(rimuoviServizio){
            return  ResponseEntity.ok().build();
        }
        return ResponseEntity.status(404).build();
    }
}
