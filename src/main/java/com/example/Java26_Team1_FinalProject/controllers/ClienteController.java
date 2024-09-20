package com.example.Java26_Team1_FinalProject.controllers;

import com.example.Java26_Team1_FinalProject.entities.Cliente;
import com.example.Java26_Team1_FinalProject.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/all-clients")
    public ResponseEntity<List<Cliente>> listaClienti(){
       return ResponseEntity.ok(clienteService.getAllClienti());
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Cliente> findClienteById(@PathVariable Long id){
        Optional<Cliente> optionalCliente = clienteService.getClienteById(id);
        if (optionalCliente.isPresent()){
            return ResponseEntity.ok(optionalCliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/new/client")
    public ResponseEntity<Cliente> createCliente (@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.createCliente(cliente));
    }

    @PutMapping("/modify")
    public ResponseEntity<Optional<Cliente>> updateCliente (@PathVariable Long id, @RequestBody Cliente cliente){
        Optional<Cliente> optionalCliente = clienteService.updateCliente(id, cliente);
        if (optionalCliente.isPresent()){
            return ResponseEntity.ok(optionalCliente);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCliente (@PathVariable Long id){
        Optional<Cliente> optionalCliente = clienteService.deleteClienteById(id);
        if (optionalCliente.isPresent()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
