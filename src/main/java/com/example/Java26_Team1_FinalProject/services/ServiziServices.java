package com.example.Java26_Team1_FinalProject.services;

import com.example.Java26_Team1_FinalProject.entities.Albergo;
import com.example.Java26_Team1_FinalProject.entities.Ente;
import com.example.Java26_Team1_FinalProject.entities.Prenotazione;
import com.example.Java26_Team1_FinalProject.entities.Servizi;
import com.example.Java26_Team1_FinalProject.repositories.AlbergoRepository;
import com.example.Java26_Team1_FinalProject.repositories.EnteRepository;
import com.example.Java26_Team1_FinalProject.repositories.PrenotazioneRepository;
import com.example.Java26_Team1_FinalProject.repositories.ServiziRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ServiziServices {
    // parametri
    @Autowired
    private ServiziRepository serviziRepository;
    @Autowired
    private EnteRepository enteRepository;
    @Autowired
    private AlbergoRepository albergoRepository;
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    // Methodi per CRUD di base

    /**
     *
     * @param newService
     * @return
     */
    public Servizi createService(Servizi newService){
        return serviziRepository.save(newService);
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Servizi> eliminareServizio(Long id){
        Optional<Servizi> serviziOptional = serviziRepository.findActiveById(id);
        if (serviziOptional.isPresent()){
            serviziRepository.logicDeleteById(id);
        }
        return Optional.empty();
    }

    /**
     *
     * @param id
     * @param servizio
     * @return
     */
    public Optional<Servizi> modificareServizi(Long id, Servizi servizio){
        Optional<Servizi> serviziOptional = serviziRepository.findActiveById(id);
        if (serviziOptional.isPresent()){
            Servizi updatedServizio = serviziOptional.get();
            updatedServizio.setName(servizio.getName());
            updatedServizio.setPrezzo(servizio.getPrezzo());
            updatedServizio.setActive(servizio.isActive());
            return Optional.of(serviziRepository.save(updatedServizio));
        }
        return Optional.empty();
    }

    /**
     *
     * @param id
     * @return
     */
    // perche nel albergo avevo fatto solo albergorepository.findbyid() e non salvato su un optional
    public Optional<Servizi> visualizzaServizio(Long id){
        Optional<Servizi> serviziOptional = serviziRepository.findActiveById(id);
        return serviziOptional;
    }

    /**
     *
     * @return
     */
    public List<Servizi> elencoServizi(){
        return serviziRepository.findAllActive();
    }

    // metodi per la relazione

    // metodi per aggiungere,modificare(cambia la relazione da ente 1 a ente 2), eliminare un ente
    // aggiungere ente
    public Optional<Servizi> createFromEnte(Long idEnte, Servizi servizio){
        Optional<Ente> enteOptional = enteRepository.findActiveById(idEnte);
        if (enteOptional.isPresent()){
            Ente enteRelazione = enteOptional.get();
            servizio.setEnte(enteRelazione);
            return Optional.of(serviziRepository.save(servizio));

        }
        // se non esiste il servizio o ente
        else {
            return Optional.empty();
        }
    }


    // metodi per creare relatione albergo

    // aggiungere albergo
    public Optional<Servizi> createFromAlbergo(Long idAlbergo, Servizi servizio){
        Optional<Albergo> albergoOptional = albergoRepository.findActiveById(idAlbergo);
        if (albergoOptional.isPresent()) {
            Albergo relationAlbergo = albergoOptional.get();
            servizio.setAlbergo(relationAlbergo);
            return Optional.of(serviziRepository.save(servizio));
        } // se non esiste il servizio o ente
        else {
            return Optional.empty();
        }
    }
   // metodi per aggiungere,modificare, eliminare un prenotazione

    // aggiungere prenotazione
    public String addPrenotazione(Long idPrenotazione, Long idServizio){
        Optional<Servizi> serviziOptional = serviziRepository.findActiveById(idServizio);
        Optional<Prenotazione> prenotazioneOptional = prenotazioneRepository.findActiveById(idPrenotazione);
        if (serviziOptional.isPresent() && prenotazioneOptional.isPresent()){
            serviziRepository.createRelationPrenotazioneEServizio(idPrenotazione,idServizio);
            return "Service added successfully";
        } else {
            return "there was an error";
        }
    }

    // remuovere un prenotazione
    public String removePrenotazioneById(Long idServizio, Long idPrenotazione){
        Optional<Servizi> serviziOptional = serviziRepository.findActiveById(idServizio);
        Optional<Prenotazione> prenotazioneOptional = prenotazioneRepository.findActiveById(idPrenotazione);
        if (serviziOptional.isPresent() && prenotazioneOptional.isPresent()) {
            serviziRepository.deleteRelationPrenotazioneEServizio(idPrenotazione, idServizio);
            return "removed service successfully";
        }
           else {
            return "there was an error";
        }
    }

}
