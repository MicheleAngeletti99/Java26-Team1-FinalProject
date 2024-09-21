package com.example.Java26_Team1_FinalProject.controllers;

import com.example.Java26_Team1_FinalProject.entities.Cliente;
import com.example.Java26_Team1_FinalProject.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
@Tag(name = "Cliente Controller", description = "Gestisce le operazioni CRUD per i clienti")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/all-clients")
    @Operation(summary = "Lista di tutti i clienti", description = "Restituisce una lista di tutti i clienti")
    public ResponseEntity<List<Cliente>> listaClienti() {
        return ResponseEntity.ok(clienteService.getAllClienti());
    }

    @GetMapping("/client/{id}")
    @Operation(summary = "Trova il cliente per ID", description = "Restituisce un cliente tramite il suo ID")
    public ResponseEntity<Cliente> findClienteById(@PathVariable Long id) {
        Optional<Cliente> optionalCliente = clienteService.getClienteById(id);
        if (optionalCliente.isPresent()) {
            return ResponseEntity.ok(optionalCliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/new/client")
    @Operation(summary = "Crea un nuovo cliente", description = "Aggiunge un nuovo cliente nel database")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.createCliente(cliente));
    }

    @PutMapping("/modify")
    @Operation(summary = "Aggiorna un cliente", description = "Aggiorna le informazioni di un cliente esistente dato il suo ID")
    public ResponseEntity<Optional<Cliente>> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> optionalCliente = clienteService.updateCliente(id, cliente);
        if (optionalCliente.isPresent()) {
            return ResponseEntity.ok(optionalCliente);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Elimina un cliente", description = "Elimina un cliente dal database dato il suo ID")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        boolean deletedClienteById = clienteService.deleteClienteById(id);
        if (deletedClienteById) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
