package com.example.Java26_Team1_FinalProject.services;

import com.example.Java26_Team1_FinalProject.entities.Albergo;
import com.example.Java26_Team1_FinalProject.entities.CartaDiPagamento;
import com.example.Java26_Team1_FinalProject.entities.Cliente;
import com.example.Java26_Team1_FinalProject.entities.Prenotazione;
import com.example.Java26_Team1_FinalProject.enums.CircuitoCartaDiPagamentoEnum;
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
     * Restituisce una lista di tutti i clienti nel database.
     *
     * @return una lista di oggetti Cliente.
     */
    public List<Cliente> getAllClienti() {
        return clienteRepository.findAll();
    }

    /**
     * Trova un cliente in base al suo ID.
     *
     * @param id l'ID del cliente da cercare.
     * @return un Optional che contiene il cliente se trovato, altrimenti vuoto.
     */
    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
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
        Optional<Cliente> existingCliente = clienteRepository.findById(id);
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
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            //elimina il cliente se trovato
            clienteRepository.deleteById(id);
            return true;
        } else {
            // Restituisce false se il cliente non è stato trovato
            return false;
        }
    }

    // ==== METODI PER LE LISTE ====

    /**
     * Permette ad un cliente di aggiungere una prenotazione.
     *
     * @param idCliente    l'ID del cliente al quale si desidera aggiungere una prenotazione.
     * @param idAlbergo    l'ID dell'albergo al quale si desidera prenotare.
     * @param prenotazione la prenotazione da aggiungere.
     * @return Optional del cliente con la prenotazione aggiunta alla sua lista, empty se il cliente non esiste.
     */
    public Optional<Prenotazione> addPrenotazione(Long idCliente, Long idAlbergo, Prenotazione prenotazione) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(idCliente);
        Optional<Albergo> optionalAlbergo = albergoRepository.findById(idAlbergo);
        if (optionalCliente.isPresent() && optionalAlbergo.isPresent()) {
            prenotazione.setCliente(optionalCliente.get());
            prenotazione.setAlbergo(optionalAlbergo.get());
            Prenotazione savedPrenotazione = prenotazioneRepository.save(prenotazione);
            return Optional.of(savedPrenotazione);
        }else {
            return Optional.empty();
        }
    }

    /**
     * Permette di rimuovere una prenotazione per ID.
     *
     * @param prenotazioneId l'ID della prenotazione da rimuovere.
     * @return true se la prenotazione è stata rimossa, false se la prenotazione non esiste.
     */
    public boolean removePrenotazione(Long prenotazioneId) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findById(prenotazioneId);
        if (optionalPrenotazione.isPresent()) {
            prenotazioneRepository.logicDeleteById(prenotazioneId);
            return true;
        }
        return false;
    }

    /**
     * Permette a un cliente di aggiungere una carta di pagamento.
     *
     * @param idCliente l'ID del cliente al quale si desidera aggiungere la carta
     * @param idCarta     la carta di pagamento da aggiungere
     * @return true se la carta è stata aggiunta con successo, false se il cliente non esiste
     */
    public Optional<Cliente> addCartaDiPagamento(Long idCliente, Long idCarta) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(idCliente);
        Optional<CartaDiPagamento> cartaOptional = cartaDiPagamentoRepository.findById(idCarta);
        if (optionalCliente.isPresent() && cartaOptional.isPresent()) {
            Cliente cliente = optionalCliente.get();
            CartaDiPagamento carta = cartaOptional.get();
            carta.setCliente(cliente);
            cliente.getCarteDiPagamento().add(carta);
            clienteRepository.save(cliente);
            return Optional.of(cliente);
        }
        return Optional.empty();
    }

    /**
     * Permette a un cliente di rimuovere una carta di pagamento.
     *
     * @param idCliente l'ID del cliente da cui si desidera rimuovere la carta
     * @param carta la carta di pagamento da rimuovere
     * @return true se la carta è stata rimossa con successo, false se la carta non è stata trovata o il cliente non esiste
     */
    public boolean deleteCartaDiPagamento(Long idCliente, String carta){
        Optional<Cliente> optionalCliente = clienteRepository.findById(idCliente);
        if (optionalCliente.isPresent()){
            Cliente cliente = optionalCliente.get();
            cliente.getCarteDiPagamento().remove(carta);
            clienteRepository.save(cliente);
            return true;
        }
        return false;
    }
}



