package com.example.Java26_Team1_FinalProject.controllers;

import com.example.Java26_Team1_FinalProject.entities.Albergo;
import com.example.Java26_Team1_FinalProject.entities.Ente;
import com.example.Java26_Team1_FinalProject.services.EnteServices;
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
    @GetMapping("/elencoEnti")
    public ResponseEntity<List<Ente>> elencoEnti(){
        return ResponseEntity.ok(enteServices.elencoEnti());
    }

    //post per creare un ente
    @PostMapping("/newEnte")
    public ResponseEntity<Ente> newEnte(@RequestBody Ente ente){
        Ente addEnte = enteServices.addEnte(ente);
        return ResponseEntity.ok(addEnte);
    }

    //delete del ente
    @DeleteMapping("/eliminareEnte/{id}")
    public ResponseEntity<Void> eliminareEnte(@PathVariable Long id){
        boolean rimuoviAlbergo = enteServices.deleteEnte(id);
        if(rimuoviAlbergo){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //modifica ente
    @PutMapping("/modificaEnte/{id}")
    public ResponseEntity<Ente> modificaEnte(@PathVariable Long id, @RequestBody Ente ente){
        Optional<Ente> enteOptional = enteServices.modificaEnte(id,ente);
        if (enteOptional.isPresent()){
            return ResponseEntity.ok(enteOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

}
