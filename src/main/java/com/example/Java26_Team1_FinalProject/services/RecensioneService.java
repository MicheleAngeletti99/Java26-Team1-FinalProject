package com.example.Java26_Team1_FinalProject.services;
import com.example.Java26_Team1_FinalProject.entities.Prenotazione;
import com.example.Java26_Team1_FinalProject.entities.Recensione;
import com.example.Java26_Team1_FinalProject.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Service per gestire le operazioni sulle recensioni.
 * Contiene metodi per le operazioni CRUD (Create, Read, Update, Delete).
 */

@Service
public class RecensioneService {

    @Autowired
    private RecensioniRepository recensioniRepository;
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AlbergoRepository albergoRepository;
    @Autowired
    private EnteRepository enteRepository;

    /**
     * Crea una nuova prenotazione nel database.
     *
     * @param newRecensione la recensione da creare, deve non essere null.
     * @return la recensione creata, non è mai null.
     */

    public Optional<Recensione> aggiungiRecensione(Long idPrenotazione, Recensione newRecensione){
        Optional<Prenotazione> prenotazioneOptional = prenotazioneRepository.findActiveById(idPrenotazione);
        if (prenotazioneOptional.isPresent()) {
            newRecensione.setPrenotazione(prenotazioneOptional.get());
            return Optional.of(recensioniRepository.save(newRecensione));
        }
        return Optional.empty();
    }

    /**
     * Legge tutte le recensioni salvate nel database.
     *
     * @return una List con le recensione.
     */

    public List<Recensione> elencoRecensioni(){
        return recensioniRepository.findAll();
    }

    /**
     * Trova una recensione in base al suo ID.
     *
     * @param id l'ID della recensione da cercare.
     * @return un Optional che contiene la recensione se trovato, altrimenti vuoto.
     */
    public Optional<Recensione> getRecensioniById(Long id){
        return recensioniRepository.findById(id);
    }

    /**
     * Aggiorna le informazioni di una recensione esistente.
     * Se la recensione viene trovata, aggiorna i suoi dati e restituisce la recensione aggiornata.
     *
     * @param id      l'ID della recensione da aggiornare.
     * @param recensione l'oggetto recensione con le nuove informazioni.
     * @return un Optional contenente la recensione aggiornato se trovato, altrimenti vuoto.
     */

    public Optional<Recensione> modificareRecensioni(Long id, Recensione recensione) {
        Optional<Recensione> optionalRecensione = recensioniRepository.findById(id);
        if (optionalRecensione.isPresent()) {
            Recensione recensioneDaModificare = optionalRecensione.get();
            recensioneDaModificare.setVoti(recensione.getVoti());
            recensioneDaModificare.setDescrizione(recensione.getDescrizione());

            return Optional.of(recensioniRepository.save(recensioneDaModificare));
        }
        return Optional.empty();
    }
    /**
     * Eliminazione logica di una recensione dal database.
     *
     * @param id l'id della recensione da eliminare, deve non essere null.
     */
    public boolean deleteRecensioneById(Long id) {
        Optional<Recensione> optionalRecensione = recensioniRepository.findById(id);
        if (optionalRecensione.isPresent()) {
            //elimina la recensione se trovata
            recensioniRepository.deleteById(id);
            return true;
        } else {
            // Restituisce false se la recensione non è stato trovata
            return false;
        }
    }

    // Metodi per la lettura dei dati

    /**
     * Cerca la recensione collegata ad una pernotazione data tramite l'id.
     *
     * @param idPrenotazione l'id della prenotazione di cui si cerca la recensione, deve non essere null.
     * @return un Optional contenente la recensione della prenotazione, un Optional vuoto se non è stata trovata la prenotazione e/o la recensione.
     */
    public Optional<Recensione> readByPrenotazione(Long idPrenotazione) {
        boolean isThere = prenotazioneRepository.existsById(idPrenotazione);
        if (isThere) {
            Optional<Recensione> optionalRecensione = recensioniRepository.findByPrenotazione(idPrenotazione);
            if (optionalRecensione.isPresent()) {
                return optionalRecensione;
            }
        }
        // se non sono state trovate sia la prenotazione che la recensione restituisco un Optional vuoto
        return Optional.empty();
    }

    /**
     * Cerca tutte le recensioni scritte da un cliente dato l'id.
     *
     * @param idCliente l'id del cliente di cui si cercano le recensioni, deve non essere null.
     * @return un Optional con le recensioni scritte dal cliente, un Optional vuoto se il cliente non è nel database.
     */
    public Optional<List<Recensione>> readByCliente(Long idCliente) {
        boolean isThere = clienteRepository.existsById(idCliente);
        if (isThere) {
            List<Recensione> recensioniTrovate = recensioniRepository.findByCliente(idCliente);
            return Optional.of(recensioniTrovate);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Cerca tutte le recensioni ricevute da un albergo dato l'id.
     *
     * @param idAlbergo l'id dell'albergo di cui si cercano le recensioni, deve non essere null.
     * @return un Optional con le recensioni ricevute dall'albergo, un Optional vuoto se l'albergo non è nel database.
     */
    public Optional<List<Recensione>> readByAlbergo(Long idAlbergo) {
        boolean isThere = albergoRepository.existsById(idAlbergo);
        if (isThere) {
            List<Recensione> recensioniTrovate = recensioniRepository.findByAlbergo(idAlbergo);
            return Optional.of(recensioniTrovate);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Cerca tutte le recensioni ricevute da un ente dato l'id.
     *
     * @param idEnte l'id dell'ente di cui si cercano le recensioni, deve non essere null.
     * @return un Optional con le recensioni ricevute dall'ente, un Optional vuoto se l'ente non è nel database.
     */
    public Optional<List<Recensione>> readByEnte(Long idEnte) {
        boolean isThere = enteRepository.existsById(idEnte);
        if (isThere) {
            List<Recensione> recensioniTrovate = recensioniRepository.findByEnte(idEnte);
            return Optional.of(recensioniTrovate);
        } else {
            return Optional.empty();
        }
    }
}

