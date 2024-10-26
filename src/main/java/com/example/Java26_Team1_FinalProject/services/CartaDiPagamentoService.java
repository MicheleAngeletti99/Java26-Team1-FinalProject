package com.example.Java26_Team1_FinalProject.services;

import com.example.Java26_Team1_FinalProject.entities.CartaDiPagamento;
import com.example.Java26_Team1_FinalProject.entities.Cliente;
import com.example.Java26_Team1_FinalProject.repositories.CartaDiPagamentoRepository;
import com.example.Java26_Team1_FinalProject.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service per gestire la logica di business relativa alle Carte di Pagamento.
 */
@Service
public class CartaDiPagamentoService {

    @Autowired
    private CartaDiPagamentoRepository cartaDiPagamentoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Recupera tutte le carte di pagamento presenti nel sistema.
     *
     * @return una lista di tutte le carte di pagamento
     */
    public List<CartaDiPagamento> getAllCarte() {
        return cartaDiPagamentoRepository.findAll();
    }

    /**
     * Cerca una carta di pagamento tramite il suo ID.
     *
     * @param id l'ID della carta di pagamento da cercare
     * @return un Optional contenente la carta di pagamento se trovata, vuoto altrimenti
     */
    public Optional<CartaDiPagamento> findCartaById(Long id){
        return cartaDiPagamentoRepository.findById(id);
    }

    /**
     * Crea e salva una nuova carta di pagamento nel sistema.
     *
     * @param carta i dati della nuova carta di pagamento
     * @return la carta di pagamento salvata
     */
    public CartaDiPagamento createCarta(CartaDiPagamento carta){
        return cartaDiPagamentoRepository.save(carta);
    }

    /**
     * Aggiorna una carta di pagamento esistente con i nuovi dati forniti.
     *
     * @param id l'ID della carta di pagamento da aggiornare
     * @param cartaDiPagamento i nuovi dati per aggiornare la carta di pagamento
     * @return un Optional contenente la carta di pagamento aggiornata se l'operazione ha successo, vuoto altrimenti
     */
    public Optional<CartaDiPagamento> updateCarta(Long id, CartaDiPagamento cartaDiPagamento){
        Optional<CartaDiPagamento> cartaDiPagamentoOptional = cartaDiPagamentoRepository.findById(id);
        if (cartaDiPagamentoOptional.isPresent()){
            CartaDiPagamento cartaTrovata = cartaDiPagamentoOptional.get();
            cartaTrovata.setNumeroCarta(cartaDiPagamento.getNumeroCarta());
            cartaTrovata.setDataDiScadenza(cartaDiPagamento.getDataDiScadenza());
            cartaTrovata.setCvv(cartaDiPagamento.getCvv());
            cartaTrovata.setCircuito(cartaDiPagamento.getCircuito());

            return Optional.of(cartaDiPagamentoRepository.save(cartaTrovata));
        }
        return Optional.empty();
    }

    /**
     * Elimina una carta di pagamento dal sistema tramite il suo ID.
     *
     * @param id l'ID della carta di pagamento da eliminare
     * @return true se l'eliminazione è avvenuta con successo, false se la carta di pagamento non è stata trovata
     */
    public boolean deleteCarta (Long id){
        Optional<CartaDiPagamento> cartaDiPagamentoOptional = cartaDiPagamentoRepository.findById(id);
        if (cartaDiPagamentoOptional.isPresent()){
            cartaDiPagamentoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Aggiunge una carta di pagamento a un cliente specifico.
     *
     * @param idCliente l'ID del cliente a cui si desidera associare la carta di pagamento
     * @param idCarta l'ID della carta di pagamento da aggiungere al cliente
     * @return il cliente aggiornato se sia il cliente che la carta esistono,
     *         altrimenti un Optional vuoto
     */
    public Optional<CartaDiPagamento> addToClient(Long idCliente, Long idCarta) {
        // Cerca il cliente nel database tramite l'ID fornito
        Optional<Cliente> optionalCliente = clienteRepository.findActiveById(idCliente);
        // Cerca la carta di pagamento nel database tramite l'ID fornito
        Optional<CartaDiPagamento> cartaOptional = cartaDiPagamentoRepository.findById(idCarta);
        // Se il cliente e la carta sono presenti
        if (optionalCliente.isPresent() && cartaOptional.isPresent()) {
            Cliente cliente = optionalCliente.get();
            CartaDiPagamento carta = cartaOptional.get();
            // Associa la carta di pagamento al cliente
            carta.setCliente(cliente);
            // Salva la carta nel database
            // Restituisce la carta aggiornata
            return Optional.of(cartaDiPagamentoRepository.save(carta));
        }
        // Restituisce un Optional vuoto se il cliente o la carta non esistono
        return Optional.empty();
    }

    /**
     * Permette a un cliente di rimuovere una carta di pagamento.
     *
     * @param idCarta la carta di pagamento da rimuovere dal cliente
     * @return true se la carta è stata rimossa dal cliente con successo, false se la carta non è stata trovata o il cliente non esiste
     */
    public boolean deleteCartaToClient(Long idCarta){
        Optional<CartaDiPagamento> optionalCarta = cartaDiPagamentoRepository.findById(idCarta);
        if (optionalCarta.isPresent()){
            optionalCarta.get().setCliente(null);
            cartaDiPagamentoRepository.save(optionalCarta.get());
            return true;
        }
        return false;
    }
}
