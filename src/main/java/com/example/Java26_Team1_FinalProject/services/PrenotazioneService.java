package com.example.Java26_Team1_FinalProject.services;

import com.example.Java26_Team1_FinalProject.entities.*;
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
    private EnteRepository enteRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AlbergoRepository albergoRepository;

    // Metodi per il crud di base

    /**
     * Crea una nuova prenotazione nel database, fatta da un cliente ad un albergo.
     *
     * @param prenotazione  la prenotazione da creare, deve non essere null.
     * @param idCliente     l'id del cliente che sta facendo la prenotazione, deve non essere null.
     * @param idAlbergo     l'id dell'albergo a cui si vuole prenotare, deve non essere null.
     * @return un Optional con la prenotazione creata, un Optional vuoto se non sono stati trovati
     * entrambi gli id nel database.
     */
    public Optional<Prenotazione> create(Prenotazione prenotazione, Long idCliente, Long idAlbergo) {
        Optional<Cliente> optionalCliente = clienteRepository.findActiveById(idCliente);
        Optional<Albergo> optionalAlbergo = albergoRepository.findActiveById(idAlbergo);
        // controllo che il cliente e l'albergo esistano nel database
        if (optionalCliente.isPresent() && optionalAlbergo.isPresent()) {
            Cliente cliete = optionalCliente.get();
            Albergo albergo = optionalAlbergo.get();
            // associo alla prenotazione il cliente e l'albergo dati
            prenotazione.setCliente(cliete);
            prenotazione.setAlbergo(albergo);
            // salvo la prenotazione nel database e la restituisco con un Optional
            Prenotazione savedPrenotazione = prenotazioneRepository.save(prenotazione);
            return Optional.of(savedPrenotazione);
        }else {
            // se non trovo sia il cliente che l'albergo ritorno un Optional vuoto
            return Optional.empty();
        }
    }

    /**
     * Legge tutte le prenotazioni attive salvate nel database.
     *
     * @return una List con le prenotazioni.
     */
    public List<Prenotazione> readAll() {
        List<Prenotazione> prenotazioni = prenotazioneRepository.findAllActive();
        return prenotazioni;
    }

    /**
     * Cerca una prenotazione tra quelle attive nel database.
     *
     * @param id l'id della prenotazione da cercare, deve non essere null.
     * @return un Optional con la prenotazione in caso sia presente, altrimenti un Optional vuoto.
     */
    public Optional<Prenotazione> readById(Long id) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findActiveById(id);
        return optionalPrenotazione;
    }

    /**
     * Aggiorna una prenotazione nel database.
     *
     * @param id l'id della prenotazione da aggiornare, deve non essere null.
     * @param prenotazione una prenotazione contenente i nuovi dati, deve non essere null.
     * @return un Optional con la prenotazione aggiornata se l'id è stato trovato, altrimenti un Optional vuoto.
     */
    public Optional<Prenotazione> updateById(Long id, Prenotazione prenotazione) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findActiveById(id);
        if (optionalPrenotazione.isPresent()) {
            Prenotazione updatePrenotazione = optionalPrenotazione.get();
            // faccio l'update dei campi della Prenotazione, non di Id
            updatePrenotazione.setNumeroPersone(prenotazione.getNumeroPersone());
            updatePrenotazione.setDataArrivo(prenotazione.getDataArrivo());
            updatePrenotazione.setDataPartenza(prenotazione.getDataPartenza());
            updatePrenotazione.setServizi(prenotazione.getServizi());
            updatePrenotazione.setActive(prenotazione.isActive());
            updatePrenotazione.setPayd(prenotazione.isPayd());
            updatePrenotazione.setCostoTotale(prenotazione.getCostoTotale());
            updatePrenotazione.setCliente(prenotazione.getCliente());
            updatePrenotazione.setAlbergo(prenotazione.getAlbergo());
            updatePrenotazione.setEnte(prenotazione.getEnte());
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
        prenotazioneRepository.logicDeleteById(id);
    }

    // Metodi per la lettura dei dati

    /**
     * Cerca tutte le prenotazioni attive nel database di uno specifico cliente, anche se il cliente è stato cancellato (disattivato).
     *
     * @param idCliente l'id del cliente di cui si cercano le prenotazioni fatte, deve non essere null.
     * @return un Optional con una List delle prenotazioni del cliente, se il cliente non esiste nel database un Optional vuoto.
     */
    public Optional<List<Prenotazione>> findByCliente(Long idCliente) {
        Boolean isThere = clienteRepository.existsById(idCliente);
        // controllo se il cliente è nel database
        if (isThere) {
            // se il cliente è presente nel database restituisco la lista di prenotazioni in un Optional
            List<Prenotazione> prenotazioni = prenotazioneRepository.findActiveByCliente(idCliente);
            return Optional.of(prenotazioni);
        } else {
            // se il cliente non è presente nel database restituisco un Optional vuoto
            return Optional.empty();
        }
    }

    /**
     * Cerca tutte le prenotazioni attive nel database di uno specifico albergo, anche se l'albergo è stato cancellato (disattivato).
     *
     * @param idAlbergo l'id dell'albergo di cui si cercano le prenotazioni ricevute, deve non essere null.
     * @return un Optional con una List delle prenotazioni dell'albergo, se l'albergo non esiste nel database un Optional vuoto.
     */
    public Optional<List<Prenotazione>> findByAlbergo(Long idAlbergo) {
        Boolean isThere = albergoRepository.existsById(idAlbergo);
        // controllo se l'albergo è nel database
        if (isThere) {
            // se l'albergo è presente nel database restituisco la lista di prenotazioni in un Optional
            List<Prenotazione> prenotazioni = prenotazioneRepository.findActiveByAlbergo(idAlbergo);
            return Optional.of(prenotazioni);
        } else {
            // se l'albergo non è presente nel database restituisco un Optional vuoto
            return Optional.empty();
        }
    }

    /**
     * Cerca tutte le prenotazioni attive nel database di uno specifico ente, anche se l'ente è stato cancellato (disattivato).
     *
     * @param idEnte l'id dell'ente di cui si cercano le prenotazioni ricevute, deve non essere null.
     * @return un Optional con una List delle prenotazioni dell'ente, se l'ente non esiste nel database un Optional vuoto.
     */
    public Optional<List<Prenotazione>> findByEnte(Long idEnte) {
        Boolean isThere = enteRepository.existsById(idEnte);
        // controllo se l'ente è nel database
        if (isThere) {
            // se l'ente è presente nel database restituisco la lista di prenotazioni in un Optional
            List<Prenotazione> prenotazioni = prenotazioneRepository.findActiveByEnte(idEnte);
            return Optional.of(prenotazioni);
        } else {
            // se l'ente non è presente nel database restituisco un Optional vuoto
            return Optional.empty();
        }
    }

    /**
     * Calcola quanto un cliente ha speso tramite l'applicazione, anche per un cliente che è stato cancellato (disattivato).
     *
     * @param idCliente l'id del cliente di cui si vuole calcolare la spesa, deve non essere null.
     * @return un Optional con la spesa totale, se il cliente non esiste nel database un Optional vuoto.
     */
    public Optional<Double> spesaTotaleCliente(Long idCliente) {
        Boolean isThere = clienteRepository.existsById(idCliente);
        // controllo se il cliente è nel database
        if (isThere) {
            // se il cliente è presente nel database restituisco un Optional con la spesa totale
            Double spesaTotale = prenotazioneRepository.spesaTotaleCliente(idCliente);
            return Optional.of(spesaTotale);
        } else {
            // se il cliente non è presente nel database restituisco un Optional vuoto
            return Optional.empty();
        }
    }

    /**
     * Calcola quanto un albergo ha guadagnato tramite l'applicazione, anche per un albergo che è stato cancellato (disattivato).
     *
     * @param idAlbergo l'id dell'albergo di cui si vuole calcolare il guadagno, deve non essere null.
     * @return un Optional con il guadagno totale, se l'albergo non esiste nel database un Optional vuoto.
     */
    public Optional<Double> guadagnoTotaleAlbergo(Long idAlbergo) {
        Boolean isThere = albergoRepository.existsById(idAlbergo);
        // controllo se l'albergo è nel database
        if (isThere) {
            // se l'albergo è presente nel database restituisco un Optional con il guadagno totale
            Double guadagnoTotale = prenotazioneRepository.guadagnoTotaleAlbergo(idAlbergo);
            return Optional.of(guadagnoTotale);
        } else {
            // se l'albergo non è presente nel database restituisco un Optional vuoto
            return Optional.empty();
        }
    }

    /**
     * Calcola quanto un ente ha guadagnato tramite l'applicazione, anche per un ente che è stato cancellato (disattivato).
     *
     * @param idEnte l'id dell'ente di cui si vuole calcolare il guadagno, deve non essere null.
     * @return un Optional con il guadagno totale, se l'ente non esiste nel database un Optional vuoto.
     */
    public Optional<Double> guadagnoTotaleEnte(Long idEnte) {
        Boolean isThere = enteRepository.existsById(idEnte);
        // controllo se l'ente è nel database
        if (isThere) {
            // se l'ente è presente nel database restituisco un Optional con il guadagno totale
            Double guadagnoTotale = prenotazioneRepository.guadagnoTotaleEnte(idEnte);
            return Optional.of(guadagnoTotale);
        } else {
            // se l'ente non è presente nel database restituisco un Optional vuoto
            return Optional.empty();
        }
    }

    // Metodi per le relazioni

    /**
     * Mette in relazione una prenotazione e un ente.
     *
     * @param idPrenotazione l'id della prenotazione da mettere in relazione, deve non essere null.
     * @param idEnte         l'id dell'ente da mettere in relazione, deve non essere null.
     * @return un Optional con la prenotazione data con associato l'ente dato, se c'era già un ente associato non viene sovrascritto,
     * un Optional vuoto se uno dei due id non è stato trovato.
     */
    public Optional<Prenotazione> addToEnte(Long idPrenotazione, Long idEnte) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findActiveById(idPrenotazione);
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
     * @param idPrenotazione l'id della prenotazione da mettere in relazione, deve non essere null.
     * @param idEnte         l'id dell'ente da mettere in relazione, deve non essere null.
     * @return un Optional con la prenotazione data con associato l'ente dato, sovrascrive il possibile ente già associato,
     * un Optional vuoto se uno dei due id non è stato trovato.
     */
    public Optional<Prenotazione> changeEnte(Long idPrenotazione, Long idEnte) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findActiveById(idPrenotazione);
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

    /**
     * Rimuove la relazione tra la prenotazione data e l'ente in relazione con essa.
     *
     * @param idPrenotazione l'id della prenotazione da cui togliere la relazione, deve non essere null.
     * @return un Optional con la prenotazione senza ente associato, un Optional vuoto se la prenotazione
     * non è stata trovata.
     */
    public Optional<Prenotazione> removeEnte(Long idPrenotazione) {
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findActiveById(idPrenotazione);

        if (optionalPrenotazione.isPresent()) {
            Prenotazione updatePrenotazione = optionalPrenotazione.get();
            // rimuovo l'ente associato
            updatePrenotazione.setEnte(null);
            // faccio l'update nel database e ritorno un Optional
            Prenotazione savedPrenotazione = prenotazioneRepository.save(updatePrenotazione);
            return Optional.of(savedPrenotazione);
        } else {
            // se non ho trovato la prenotazione ritorno un Optional vuoto
            return Optional.empty();
        }
    }

    // Metodi per il pagamento

    //metodo per conferma pagamento
    public boolean confermaPagamento(Long idPrenotazione){
        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findActiveById(idPrenotazione);
        if (optionalPrenotazione.isPresent()){
            Prenotazione prenotazione = optionalPrenotazione.get();
            if (prenotazione.isPayd()){
                return false;
            } else {
                prenotazione.setPayd(true);
                prenotazioneRepository.save(prenotazione);
                return true;
            }
        }
        return false;
    }

    //   metodo per aggiornare il prezzo
//    private void aggiornaPrezzo (Prenotazione prenotazione){
//        Double prezzoPersona = prenotazione.getAlbergo().getPrezzoPersona();
//        Integer dataArrivo = prenotazione.getDataArrivo().getDayOfYear();
//        Integer dataPartenza = prenotazione.getDataPartenza().getDayOfYear();
//        Integer numeroPersone = prenotazione.getNumeroPersone();
//        Double totaleAlloggio = (dataPartenza - dataArrivo) * prezzoPersona * numeroPersone;
//
//    }

}
