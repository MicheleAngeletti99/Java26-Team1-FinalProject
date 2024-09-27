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
    private ClienteRepository clienteRepository;
    @Autowired
    private AlbergoRepository albergoRepository;
    @Autowired
    private EnteRepository enteRepository;

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
        List<Prenotazione> prenotazioni = prenotazioneRepository.findAll();
        return prenotazioni;
    }

    /**
     * Cerca una prenotazione tra quelle nel database.
     *
     * @param id l'id della prenotazione da cercare, deve non essere null.
     * @return un'Optional con la prenotazione in caso sia presente, altrimenti un Optional vuoto.
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
     * Aggiorna una prenotazione nel database.
     *
     * @param id l'id della prenotazione da aggiornare, deve non essere null.
     * @param prenotazione una prenotazione contenente i nuovi dati, deve non essere null.
     * @return un'Optional con la prenotazione aggiornata se l'id è stato trovato, altrimenti un Optional vuoto.
     */
    public Optional<Prenotazione> updateById(Long id, Prenotazione prenotazione) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findById(id);
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

    // todo: quando elimino una prenotazione devo prima eliminare la possibile recensione in relazione con essa, oppure metto un campo extra in prenotazione per l'elimiazione logica.
    /**
     * Elimina una prenotazione dal database.
     *
     * @param id l'id della prenotazione da eliminare, deve non essere null.
     */
    public void deleteById(Long id) {
            prenotazioneRepository.deleteById(id);
    }

    // Metodi per le liste
    // todo: che cosa ritornano questi metodi? un boolean, la prenotazione aggiornata, la lista di servizi aggiornata?

    /**
     * Aggiunge un servizio alla lista di servizi richiesti in una prenotazione.
     *
     * @param idPrenotazione l'id della prenotazione a cui aggiungere il servizio.
     * @param idServizio     l'id del servizio da aggiungere alla lista.
     * @return un Optional con la prenotazione aggiornata se l'id è stato trovato, altrimenti un Optional vuoto.
     */
    public Optional<Prenotazione> addServizio(Long idPrenotazione, Integer idServizio) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findById(idPrenotazione);
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
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findById(idPrenotazione);
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

    // todo: cambiare il cliente associato ad una prenotazione serve?
    /**
     * Mette in relazione una prenotazione e un cliente.
     *
     * @param idPrenotazione l'id della prenotazione da mettere in relazione.
     * @param idCliente      l'id del cliente da mettere in relazione.
     * @return un Optional con la prenotazione data con associato il cliente dato, se c'era già un cliente associato non viene sovrascritto,
     * un Optional vuoto se uno dei due id non è stato trovato.
     */
    public Optional<Prenotazione> associateCliente(Long idPrenotazione, Long idCliente) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findById(idPrenotazione);
        Optional<Cliente> optionalCliente = clienteRepository.findById(idCliente);

        if (optionalPrenotazione.isPresent() && optionalCliente.isPresent()) {
            Prenotazione updatePrenotazione = optionalPrenotazione.get();
            Cliente relationCliente = optionalCliente.get();
            // verifico che non ci sia un cliente associato
            if (updatePrenotazione.getCliente() == null) {
                // associo il cliente alla prenotazione
                updatePrenotazione.setCliente(relationCliente);
                // faccio l'update nel database e ritorno un Optional
                Prenotazione savedPrenotazione = prenotazioneRepository.save(updatePrenotazione);
                return Optional.of(savedPrenotazione);
            } else {
                // se c'è gia un cliente associato non lo sovrascrivo
                return Optional.of(updatePrenotazione);
            }
        } else {
            // se non ho trovato entrambi gli id ritorno un Optional vuoto
            return Optional.empty();
        }
    }

    /**
     * Mette in relazione una prenotazione con un nuovo cliente.
     *
     * @param idPrenotazione l'id della prenotazione da mettere in relazione.
     * @param idCliente      l'id del cliente da mettere in relazione.
     * @return un Optional con la prenotazione data con associato il cliente dato, sovrascrive il possibile cliente già associato,
     * un Optional vuoto se uno dei due id non è stato trovato.
     */
    public Optional<Prenotazione> changeAssociatedCliente(Long idPrenotazione, Long idCliente) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findById(idPrenotazione);
        Optional<Cliente> optionalCliente = clienteRepository.findById(idCliente);

        if (optionalPrenotazione.isPresent() && optionalCliente.isPresent()) {
            Prenotazione updatePrenotazione = optionalPrenotazione.get();
            Cliente relationCliente = optionalCliente.get();
            // associo il cliente alla prenotazione
            updatePrenotazione.setCliente(relationCliente);
            // faccio l'update nel database e ritorno un Optional
            Prenotazione savedPrenotazione = prenotazioneRepository.save(updatePrenotazione);
            return Optional.of(savedPrenotazione);
        } else {
            // se non ho trovato entrambi gli id ritorno un Optional vuoto
            return Optional.empty();
        }
    }

    /**
     * Mette in relazione una prenotazione e un albergo.
     *
     * @param idPrenotazione l'id della prenotazione da mettere in relazione.
     * @param idAlbergo      l'id dell'albergo da mettere in relazione.
     * @return un Optional con la prenotazione data con associato l'albergo dato, se c'era già un albergo associato non viene sovrascritto,
     * un Optional vuoto se uno dei due id non è stato trovato.
     */
    public Optional<Prenotazione> associateAlbergo(Long idPrenotazione, Long idAlbergo) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findById(idPrenotazione);
        Optional<Albergo> optionalAlbergo = albergoRepository.findById(idAlbergo);

        if (optionalPrenotazione.isPresent() && optionalAlbergo.isPresent()) {
            Prenotazione updatePrenotazione = optionalPrenotazione.get();
            Albergo relationAlbergo = optionalAlbergo.get();
            // verifico che non ci sia un albergo associato
            if (updatePrenotazione.getAlbergo() == null) {
                // associo l'albergo alla prenotazione
                updatePrenotazione.setAlbergo(relationAlbergo);
                // faccio l'update nel database e ritorno un Optional
                Prenotazione savedPrenotazione = prenotazioneRepository.save(updatePrenotazione);
                return Optional.of(savedPrenotazione);
            } else {
                // se c'è gia un albergo associato non lo sovrascrivo
                return Optional.of(updatePrenotazione);
            }
        } else {
            // se non ho trovato entrambi gli id ritorno un Optional vuoto
            return Optional.empty();
        }
    }

    /**
     * Mette in relazione una prenotazione con un nuovo albergo.
     *
     * @param idPrenotazione l'id della prenotazione da mettere in relazione.
     * @param idAlbergo      l'id dell'albergo da mettere in relazione.
     * @return un Optional con la prenotazione data con associato l'albergo dato, sovrascrive il possibile albergo già associato,
     * un Optional vuoto se uno dei due id non è stato trovato.
     */
    public Optional<Prenotazione> changeAssociatedAlbergo(Long idPrenotazione, Long idAlbergo) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findById(idPrenotazione);
        Optional<Albergo> optionalAlbergo = albergoRepository.findById(idAlbergo);

        if (optionalPrenotazione.isPresent() && optionalAlbergo.isPresent()) {
            Prenotazione updatePrenotazione = optionalPrenotazione.get();
            Albergo relationAlbergo = optionalAlbergo.get();
            // associo l'albergo alla prenotazione
            updatePrenotazione.setAlbergo(relationAlbergo);
            // faccio l'update nel database e ritorno un Optional
            Prenotazione savedPrenotazione = prenotazioneRepository.save(updatePrenotazione);
            return Optional.of(savedPrenotazione);
        } else {
            // se non ho trovato entrambi gli id ritorno un Optional vuoto
            return Optional.empty();
        }
    }

    /**
     * Mette in relazione una prenotazione e un ente.
     *
     * @param idPrenotazione l'id della prenotazione da mettere in relazione.
     * @param idEnte         l'id dell'ente da mettere in relazione.
     * @return un Optional con la prenotazione data con associato l'ente dato, se c'era già un ente associato non viene sovrascritto,
     * un Optional vuoto se uno dei due id non è stato trovato.
     */
    public Optional<Prenotazione> associateEnte(Long idPrenotazione, Long idEnte) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findById(idPrenotazione);
        Optional<Ente> optionalEnte = enteRepository.findById(idEnte);

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
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findById(idPrenotazione);
        Optional<Ente> optionalEnte = enteRepository.findById(idEnte);

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
