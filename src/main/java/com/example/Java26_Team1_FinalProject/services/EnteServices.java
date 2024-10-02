package com.example.Java26_Team1_FinalProject.services;

import com.example.Java26_Team1_FinalProject.entities.Ente;
import com.example.Java26_Team1_FinalProject.repositories.EnteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnteServices {

    @Autowired
    private EnteRepository enteRepository;

    //ritorna elenco enti
    public List<Ente> elencoEnti(){
        return enteRepository.findAll();
    }
    //ritorna ente by id
    public Optional<Ente> getEnteById(Long id){
        return enteRepository.findById(id);
    }

    //crea un nuovo ente
    public Ente addEnte(Ente ente){
        return enteRepository.save(ente);
    }
    //eliminare un Ente per id
    public boolean deleteEnte(Long id){
        Optional<Ente> enteOptional = enteRepository.findById(id);
        if(enteOptional.isPresent()){
            enteRepository.deleteById(id);
            return true;
        }
        return false;
    }
    //modificare un ente
    public Optional<Ente> modificaEnte(Long id, Ente ente){
        Optional<Ente> enteOptional = enteRepository.findById(id);
        if(enteOptional.isPresent()){
            Ente enteDaModificare = enteOptional.get();
            enteDaModificare.setNome(ente.getNome());
            enteDaModificare.setCitta(ente.getCitta());
            enteDaModificare.setLinkWeb(ente.getLinkWeb());
            enteDaModificare.setRecensione(ente.getRecensione());
            enteDaModificare.setServiziEnumIds(ente.getServiziEnumIds());
            enteDaModificare.setRatingAVG(ente.getRatingAVG());

            return Optional.of(enteRepository.save(enteDaModificare));
        }
        return Optional.empty();
    }

}
