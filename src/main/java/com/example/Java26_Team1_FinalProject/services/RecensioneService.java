package com.example.Java26_Team1_FinalProject.services;
import com.example.Java26_Team1_FinalProject.entities.Recensione;
import com.example.Java26_Team1_FinalProject.repositories.RecensioniRepository;
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
    /**
     * Crea una nuova prenotazione nel database.
     *
     * @param newRecensione la recensione da creare, deve non essere null.
     * @return la recensione creata, non è mai null.
     */

    public Recensione aggiungiRecensione(Recensione newRecensione){
        return recensioniRepository.save(newRecensione);
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
            recensioneDaModificare.setNome(recensione.getNome());
            recensioneDaModificare.setVoti(recensione.getVoti());
            recensioneDaModificare.setCitta(recensione.getCitta());
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

    }

