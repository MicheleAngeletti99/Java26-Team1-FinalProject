package com.example.Java26_Team1_FinalProject.services;

import com.example.Java26_Team1_FinalProject.entities.Albergo;
import com.example.Java26_Team1_FinalProject.entities.Cliente;
import com.example.Java26_Team1_FinalProject.entities.Ente;
import com.example.Java26_Team1_FinalProject.entities.Prenotazione;
import com.example.Java26_Team1_FinalProject.enums.ServizioEnum;
import com.example.Java26_Team1_FinalProject.repositories.AlbergoRepository;
import com.example.Java26_Team1_FinalProject.repositories.ClienteRepository;
import com.example.Java26_Team1_FinalProject.repositories.EnteRepository;
import com.example.Java26_Team1_FinalProject.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrenotazioneService {
    // Campi
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private AlbergoService albergoService;
    @Autowired
    private EnteServices enteServices;

    // Metodi per il crud di base

    /**
     * Crea una nuova prenotazione nel database.
     *
     * @param prenotazione la prenotazione da creare, deve non essere null.
     * @return la prenotazione creata, non è mai null.
     */
    public Prenotazione create(Prenotazione prenotazione) {
        Prenotazione savedPrenotazione = prenotazioneRepository.save(prenotazione);
        return savedPrenotazione;
    }

    /**
     * Legge tutte le prenotazioni salvate nel database.
     *
     * @return una List con le prenotazioni.
     */
    public List<Prenotazione> readAll() {
        List<Prenotazione> prenotazioni = prenotazioneRepository.findAllActive();
        return prenotazioni;
    }

    /**
     * Cerca una prenotazione tra quelle nel database.
     *
     * @param id l'id della prenotazione da cercare, deve non essere null.
     * @return un'Optional con la prenotazione in caso sia presente, altrimenti un Optional vuoto.
     */
    public Optional<Prenotazione> readById(Long id) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findActiveById(id);
        if (optionalPrenotazione.isPresent()) {
            return optionalPrenotazione;
        } else {
            return Optional.empty();
        }
    }

    /**
     * Aggiorna una prenotazione nel database.
     *
     * @param id l'id della prenotazione da aggiornare, deve non essere null.
     * @param prenotazione una prenotazione contenente i nuovi dati, deve non essere null.
     * @return un'Optional con la prenotazione aggiornata se l'id è stato trovato, altrimenti un Optional vuoto.
     */
    public Optional<Prenotazione> updateById(Long id, Prenotazione prenotazione) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findActiveById(id);
        if (optionalPrenotazione.isPresent()) {
            Prenotazione updatePrenotazione = optionalPrenotazione.get();
            // faccio l'update dei campi della Prenotazione, non di Id
            updatePrenotazione.setAlbergo(prenotazione.getAlbergo());
            updatePrenotazione.setCliente(prenotazione.getCliente());
            updatePrenotazione.setDataArrivo(prenotazione.getDataArrivo());
            updatePrenotazione.setDataPartenza(prenotazione.getDataPartenza());
            updatePrenotazione.setNumeroPersone(prenotazione.getNumeroPersone());
            updatePrenotazione.setServiziRichiestiIds(prenotazione.getServiziRichiestiIds());
            // faccio l'update nel database e ritorno un Optional
            Prenotazione savedPrenotazione = prenotazioneRepository.save(updatePrenotazione);
            return Optional.of(savedPrenotazione);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Eliminazione logica di una prenotazione dal database.
     *
     * @param id l'id della prenotazione da eliminare, deve non essere null.
     */
    public void deleteById(Long id) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findActiveById(id);
        if (optionalPrenotazione.isPresent()) {
            Prenotazione deletePrenotazione = optionalPrenotazione.get();
            // faccio l'update di isActive e salvo nel database
            deletePrenotazione.setActive(false);
            prenotazioneRepository.save(deletePrenotazione);
        }
    }

    // Metodi per le liste

    /**
     * Aggiunge un servizio alla lista di servizi richiesti in una prenotazione.
     *
     * @param idPrenotazione l'id della prenotazione a cui aggiungere il servizio.
     * @param idServizio     l'id del servizio da aggiungere alla lista.
     * @return un Optional con la prenotazione aggiornata se l'id è stato trovato, altrimenti un Optional vuoto.
     */
    public Optional<Prenotazione> addServizio(Long idPrenotazione, Integer idServizio) {
        Optional<Prenotazione> optionalPrenotazione = readById(idPrenotazione);
        if (optionalPrenotazione.isPresent()) {
            Prenotazione updatePrenotazione = optionalPrenotazione.get();
            // aggiungo il servizio alla prenotazione
            updatePrenotazione.getServiziRichiestiIds().add(idServizio);
            // faccio l'update nel database e ritorno un Optional
            Prenotazione savedPrenotazione = prenotazioneRepository.save(updatePrenotazione);
            return Optional.of(savedPrenotazione);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Rimouve un servizio dalla lista di servizi richiesti in una prenotazione.
     *
     * @param idPrenotazione l'id della prenotazione a cui rimuovere il servizio.
     * @param idServizio     l'id del servizio da rimuovere dalla lista.
     * @return un Optional con la prenotazione aggiornata se l'id è stato trovato, altrimenti un Optional vuoto.
     */
    public Optional<Prenotazione> removeServizio(Long idPrenotazione, Integer idServizio) {
        Optional<Prenotazione> optionalPrenotazione = readById(idPrenotazione);
        if (optionalPrenotazione.isPresent()) {
            Prenotazione updatePrenotazione = optionalPrenotazione.get();
            // rimuovo il servizio dalla prenotazione
            updatePrenotazione.getServiziRichiestiIds().remove(idServizio);
            // faccio l'update nel database e ritorno un Optional
            Prenotazione savedPrenotazione = prenotazioneRepository.save(updatePrenotazione);
            return Optional.of(savedPrenotazione);
        } else {
            return Optional.empty();
        }
    }

    // Metodi per le relazioni

    /**
     * Mette in relazione una prenotazione e un ente.
     *
     * @param idPrenotazione l'id della prenotazione da mettere in relazione.
     * @param idEnte         l'id dell'ente da mettere in relazione.
     * @return un Optional con la prenotazione data con associato l'ente dato, se c'era già un ente associato non viene sovrascritto,
     * un Optional vuoto se uno dei due id non è stato trovato.
     */
    public Optional<Prenotazione> associateEnte(Long idPrenotazione, Long idEnte) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findActiveById(idPrenotazione);
        Optional<Ente> optionalEnte = enteServices.getEnteById(idEnte);

        if (optionalPrenotazione.isPresent() && optionalEnte.isPresent()) {
            Prenotazione updatePrenotazione = optionalPrenotazione.get();
            Ente relationEnte = optionalEnte.get();
            // verifico che non ci sia un ente associato
            if (updatePrenotazione.getEnte() == null) {
                // associo l'ente alla prenotazione
                updatePrenotazione.setEnte(relationEnte);
                // faccio l'update nel database e ritorno un Optional
                Prenotazione savedPrenotazione = prenotazioneRepository.save(updatePrenotazione);
                return Optional.of(savedPrenotazione);
            } else {
                // se c'è gia un ente associato non lo sovrascrivo
                return Optional.of(updatePrenotazione);
            }
        } else {
            // se non ho trovato entrambi gli id ritorno un Optional vuoto
            return Optional.empty();
        }
    }

    /**
     * Mette in relazione una prenotazione con un nuovo ente.
     *
     * @param idPrenotazione l'id della prenotazione da mettere in relazione.
     * @param idEnte         l'id dell'ente da mettere in relazione.
     * @return un Optional con la prenotazione data con associato l'ente dato, sovrascrive il possibile ente già associato,
     * un Optional vuoto se uno dei due id non è stato trovato.
     */
    public Optional<Prenotazione> changeAssociatedEnte(Long idPrenotazione, Long idEnte) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findActiveById(idPrenotazione);
        Optional<Ente> optionalEnte = enteServices.getEnteById(idEnte);

        if (optionalPrenotazione.isPresent() && optionalEnte.isPresent()) {
            Prenotazione updatePrenotazione = optionalPrenotazione.get();
            Ente relationEnte = optionalEnte.get();
            // associo l'ente alla prenotazione
            updatePrenotazione.setEnte(relationEnte);
            // faccio l'update nel database e ritorno un Optional
            Prenotazione savedPrenotazione = prenotazioneRepository.save(updatePrenotazione);
            return Optional.of(savedPrenotazione);
        } else {
            // se non ho trovato entrambi gli id ritorno un Optional vuoto
            return Optional.empty();
        }
    }
}
