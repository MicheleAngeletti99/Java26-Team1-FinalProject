package com.example.Java26_Team1_FinalProject.services;

import com.example.Java26_Team1_FinalProject.entities.Servizi;
import com.example.Java26_Team1_FinalProject.repositories.ServiziRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ServiziServices {
    // parameters
    @Autowired
    private ServiziRepository serviziRepository;
    //CRUD METHODS
    // creare un servizio
    public Servizi createService(Servizi newService){
        return serviziRepository.save(newService);
    }
    // eliminare un servizio
    public Optional<Servizi> eliminareServizio(Long id){
        Optional<Servizi> serviziOptional = serviziRepository.findActiveById(id);
        if (serviziOptional.isPresent()){
            serviziRepository.logicDeleteById(id);
        }
        return Optional.empty();
    }
    // modificare un servizio
    public Optional<Servizi> modificareServizi(Long id, Servizi servizio){
        Optional<Servizi> serviziOptional = serviziRepository.findActiveById(id);
        if (serviziOptional.isPresent()){
            Servizi updatedServizio = serviziOptional.get();
            updatedServizio.setName(servizio.getName());
            updatedServizio.setPrezzo(servizio.getPrezzo());
            updatedServizio.setPrenotazioni(servizio.getPrenotazioni());
            updatedServizio.setAlbergo(servizio.getAlbergo());
            updatedServizio.setEnte(servizio.getEnte());
            updatedServizio.setActive(servizio.isActive());
            return Optional.of(serviziRepository.save(updatedServizio));
        }
        return Optional.empty();
    }
    // visualizzare un servizio tramite ID
    // perche nel albergo avevo fatto solo albergorepository.findbyid() e non salvato su un optional
    public Optional<Servizi> visualizzaServizio(Long id){
        Optional<Servizi> serviziOptional = serviziRepository.findActiveById(id);
        return serviziOptional;
    }
    // visualizzare il elenco di servizi
    public List<Servizi> elencoServizi(){
        return serviziRepository.findAllActive();
    }

}
