package com.example.Java26_Team1_FinalProject.controllers;


import com.example.Java26_Team1_FinalProject.entities.CartaDiPagamento;
import com.example.Java26_Team1_FinalProject.entities.Cliente;
import com.example.Java26_Team1_FinalProject.services.CartaDiPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carte-di-pagamento")
public class CartaDiPagamentoController {

    @Autowired
    private CartaDiPagamentoService cartaDiPagamentoService;

    @GetMapping("/all-carte")
    public ResponseEntity<List<CartaDiPagamento>> getAllCarte() {
        return ResponseEntity.ok(cartaDiPagamentoService.getAllCarte());
    }

    @GetMapping("/{id}/cliente")
    public ResponseEntity<CartaDiPagamento> findByid(@PathVariable Long id) {
        Optional<CartaDiPagamento> optionalCartaDiPagamento = cartaDiPagamentoService.findCartaById(id);
        if (optionalCartaDiPagamento.isPresent()) {
            return ResponseEntity.ok(optionalCartaDiPagamento.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create-carta")
    public ResponseEntity<CartaDiPagamento> createCarta(@RequestBody CartaDiPagamento carta) {
        CartaDiPagamento nuovaCarta = cartaDiPagamentoService.createCarta(carta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovaCarta);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<CartaDiPagamento> updateCarta(@PathVariable Long id, @RequestBody CartaDiPagamento cartaDiPagamento) {
        Optional<CartaDiPagamento> optionalCartaDiPagamento = cartaDiPagamentoService.updateCarta(id, cartaDiPagamento);
        if (optionalCartaDiPagamento.isPresent()) {
            return ResponseEntity.ok(optionalCartaDiPagamento.get());
        }
        // Restituisce 404 se il cliente non è stato trovato
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCarta(@PathVariable Long id) {
        boolean deletedCartaById = cartaDiPagamentoService.deleteCarta(id);
        // Restituisce 204 No Content se l'eliminazione ha avuto successo
        if (deletedCartaById) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


