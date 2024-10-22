package com.example.Java26_Team1_FinalProject.services;

import com.example.Java26_Team1_FinalProject.entities.Cliente;
import com.example.Java26_Team1_FinalProject.repositories.AlbergoRepository;
import com.example.Java26_Team1_FinalProject.repositories.CartaDiPagamentoRepository;
import com.example.Java26_Team1_FinalProject.repositories.ClienteRepository;
import com.example.Java26_Team1_FinalProject.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service per gestire le operazioni sui clienti.
 * Contiene metodi per le operazioni CRUD (Create, Read, Update, Delete).
 */
@Service
public class ClienteService {

    // Inietta il repository per accedere ai metodi di persistenza dei clienti
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private AlbergoRepository albergoRepository;

    @Autowired
    private CartaDiPagamentoRepository cartaDiPagamentoRepository;

    /**
     * Restituisce una lista di tutti i clienti attivi nel database.
     *
     * @return una lista di oggetti Cliente.
     */
    public List<Cliente> getAllClienti() {
        return clienteRepository.findAllActive();
    }

    /**
     * Trova un cliente in base al suo ID.
     *
     * @param id l'ID del cliente da cercare.
     * @return un Optional che contiene il cliente se trovato, altrimenti vuoto.
     */
    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findActiveById(id);
    }

    /**
     * Crea un nuovo cliente e lo salva nel database.
     *
     * @param cliente l'oggetto Cliente da salvare.
     * @return il cliente creato.
     */
    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * Aggiorna le informazioni di un cliente esistente.
     * Se il cliente viene trovato, aggiorna i suoi dati e restituisce il cliente aggiornato.
     *
     * @param id      l'ID del cliente da aggiornare.
     * @param cliente l'oggetto Cliente con le nuove informazioni.
     * @return un Optional contenente il cliente aggiornato se trovato, altrimenti vuoto.
     */
    public Optional<Cliente> updateCliente(Long id, Cliente cliente) {
        Optional<Cliente> existingCliente = clienteRepository.findActiveById(id);
        if (existingCliente.isPresent()) {
            Cliente clienteToUpdate = existingCliente.get();

            //aggiorna i campi del cliente
            clienteToUpdate.setEmail(cliente.getEmail());
            clienteToUpdate.setPassword(cliente.getPassword());
            clienteToUpdate.setContattoTelefonico(cliente.getContattoTelefonico());
            clienteToUpdate.setNomeCliente(cliente.getNomeCliente());
            clienteToUpdate.setCognomeCliente(cliente.getCognomeCliente());
            clienteToUpdate.setDataDiNascita(cliente.getDataDiNascita());
            clienteToUpdate.setLivelloAbbonamento(cliente.getLivelloAbbonamento());
            clienteToUpdate.setCarteDiPagamento(cliente.getCarteDiPagamento());
            clienteToUpdate.setPrenotazioni(cliente.getPrenotazioni());
            clienteToUpdate.setActive(cliente.isActive());

            //ritorna e salva il cliente aggiornato nel database
            return Optional.of(clienteRepository.save(clienteToUpdate));

        } else {
            //restituisce un Optional vuoto se il cliente non è stato trovato
            return Optional.empty();
        }
    }

    /**
     * Elimina un cliente dal database in base al suo ID.
     *
     * @param id l'ID del cliente da eliminare.
     * @return true se il cliente è stato eliminato, false altrimenti.
     */
    public boolean deleteClienteById(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findActiveById(id);
        if (optionalCliente.isPresent()) {
            //elimina le carte di pagamento collegate al cliente
            cartaDiPagamentoRepository.deleteByCliente(id);
            //elimina il cliente se trovato
            clienteRepository.logicDeleteById(id);
            return true;
        } else {
            // Restituisce false se il cliente non è stato trovato
            return false;
        }
    }
}



