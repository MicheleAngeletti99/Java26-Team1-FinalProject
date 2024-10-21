package com.example.Java26_Team1_FinalProject.controllers;

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
    @PostMapping("/newservizio")
    public ResponseEntity<Servizi> newServizio(@RequestBody Servizi servizio){
        Servizi newServizio = services.createService(servizio);
        return ResponseEntity.ok(newServizio);
    }
    //GET  by ID
    // using an optional in the controller because you're checking whether the entity was found
    // if not found without the optional it will give you a null
    @GetMapping("/{id}")
    public ResponseEntity<Servizi> getServizio(@PathVariable Long id){
        Optional<Servizi> serviziOptional = services.visualizzaServizio(id);
        if (serviziOptional.isPresent()){
            return ResponseEntity.ok(serviziOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
    // GET
    @GetMapping("/elencoServizi")
    public ResponseEntity<List<Servizi>> elencoServizi(){
        return ResponseEntity.ok(services.elencoServizi());
    }
    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Servizi> modificareServizio(@PathVariable Long id, Servizi servizio) {
        Optional<Servizi> serviziOptional = services.modificareServizi(id,servizio);
        if (serviziOptional.isPresent()){
            return ResponseEntity.ok(serviziOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServizio(@PathVariable Long id){
        Optional<Servizi> serviziOptional = services.eliminareServizio(id);
        if(serviziOptional.isPresent()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
