package com.example.Java26_Team1_FinalProject.services;

import com.example.Java26_Team1_FinalProject.entities.Cliente;
import com.example.Java26_Team1_FinalProject.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClienti() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> updateCliente(Long id, Cliente cliente) {
        Optional<Cliente> existingCliente = clienteRepository.findById(id);
        if (existingCliente.isPresent()) {
            Cliente clienteToUpdate = existingCliente.get();

            clienteToUpdate.setNomeCliente(cliente.getNomeCliente());
            clienteToUpdate.setCognomeCliente(cliente.getCognomeCliente());
            clienteToUpdate.setDataDiNascita(cliente.getDataDiNascita());
            clienteToUpdate.setLivelloAbbonamento(cliente.getLivelloAbbonamento());

            /*          !!DA CONTROLLARE!! se per la modifica delle liste va bene così
              (in teoria dovremmo modificare un elemento all'interno delle liste, non tutta la lista)
                        !! DA VALUTARE METODI PER OGNI LISTA (ADD , FIND , UPDATE , DELETE) !!

            clienteToUpdate.setCarteDiPagamento(cliente.getCarteDiPagamento());
            clienteToUpdate.setPrenotazioni(cliente.getPrenotazioni());
            clienteToUpdate.setRecensioni(cliente.getRecensioni()); */

            clienteRepository.save(clienteToUpdate);

            return Optional.of(clienteToUpdate);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Cliente> deleteClienteById(Long id){
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()){
            clienteRepository.deleteById(id);
            return optionalCliente;
        } else {
            return Optional.empty();
        }
    }
}
