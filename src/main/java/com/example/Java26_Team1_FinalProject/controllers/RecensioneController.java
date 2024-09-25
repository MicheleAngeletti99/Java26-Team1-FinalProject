package com.example.Java26_Team1_FinalProject.controllers;
import com.example.Java26_Team1_FinalProject.entities.Recensione;
import com.example.Java26_Team1_FinalProject.services.RecensioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recensione")
public class RecensioneController {

    @Autowired
    private RecensioneService recensioneService;
    @GetMapping("/elencoRecensioni")
    public ResponseEntity<List<Recensione>> elencoRecensioni(){
        return ResponseEntity.ok(recensioneService.elencoRecensioni());
    }


    @GetMapping("/recensione/{id}")
    public ResponseEntity<Recensione> getRecensioneTramiteId(@PathVariable Long id){
        Optional<Recensione> recensioneOptional = recensioneService.getRecensioniById(id);
        return recensioneOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }


    @PostMapping("/newReceensione")
    public ResponseEntity<Recensione> newRecensione(@RequestBody Recensione recensione){
        Recensione newRecensione = recensioneService.aggiungiRecensione(recensione);
        return ResponseEntity.ok(newRecensione);
    }

    @PutMapping("/modificaRecensione/{id}")
    public ResponseEntity<Recensione> modificaRecensione(@PathVariable Long id, @RequestBody Recensione recensione){
        Optional<Recensione> optionalRecensione = recensioneService.modificareRecensioni(id,recensione);
        return optionalRecensione.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}

