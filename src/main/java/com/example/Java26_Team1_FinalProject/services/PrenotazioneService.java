package com.example.Java26_Team1_FinalProject.services;

import com.example.Java26_Team1_FinalProject.entities.Prenotazione;
import com.example.Java26_Team1_FinalProject.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    /**
     * Crea una nuova prenotazione nel database
     *
     * @param prenotazione la prenotazione da creare, deve non essere null
     * @return la prenotazione creata, non è mai null
     */
    public Prenotazione create(Prenotazione prenotazione) {
        Prenotazione savedPrenotazione = prenotazioneRepository.save(prenotazione);
        return savedPrenotazione;
    }

    /**
     * Legge tutte le prenotazioni salvate nel database
     *
     * @return una List con le prenotazioni
     */
    public List<Prenotazione> readAll() {
        List<Prenotazione> prenotazioni = prenotazioneRepository.findAll();
        return prenotazioni;
    }

    /**
     * Cerca una prenotazione tra quelle nel database
     * @param id l'id della prenotazione da cercare, deve non essere null
     * @return un'Optional con la prenotazione in caso sia presente, altrimenti un Optional vuoto
     */
    public Optional<Prenotazione> readById(Long id) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findById(id);
        if (optionalPrenotazione.isPresent()) {
            return optionalPrenotazione;
        } else {
            return Optional.empty();
        }
    }

    /**
     * Aggiorna una prenotazione nel database
     * @param id l'id della prenotazione da aggiornare, deve non essere null
     * @param prenotazione una prenotazione contenente i nuovi dati, deve non essere null
     * @return un'Optional con la prenotazione aggiornata se l'id è stato trovato, altrimenti un Optional vuoto
     */
    public Optional<Prenotazione> updateById(Long id, Prenotazione prenotazione) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findById(id);
        if (optionalPrenotazione.isPresent()) {
            // faccio l'update dei campi di optionalPrenotazione, non di Id
            optionalPrenotazione.get().setAlbergo(prenotazione.getAlbergo());
            optionalPrenotazione.get().setCliente(prenotazione.getCliente());
            optionalPrenotazione.get().setDataArrivo(prenotazione.getDataArrivo());
            optionalPrenotazione.get().setDataPartenza(prenotazione.getDataPartenza());
            optionalPrenotazione.get().setNumeroPersone(prenotazione.getNumeroPersone());
            optionalPrenotazione.get().setServiziRichiesti(prenotazione.getServiziRichiesti());
            // faccio l'update nel database e ritorno un Optional
            Prenotazione updatedPrenotazione = prenotazioneRepository.save(optionalPrenotazione.get());
            return Optional.of(updatedPrenotazione);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Elimina una prenotazione dal database
     * @param id l'id della prenotazione da eliminare, deve non essere null
     * @return true se l'id è stato trovato prima della cancellazione, altrimenti false
     */
    public boolean deleteById(Long id) {
        boolean isThere = prenotazioneRepository.existsById(id);
        if (isThere) {
            prenotazioneRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
