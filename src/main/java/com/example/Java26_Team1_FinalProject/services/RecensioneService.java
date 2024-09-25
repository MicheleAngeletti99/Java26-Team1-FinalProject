package com.example.Java26_Team1_FinalProject.services;

import com.example.Java26_Team1_FinalProject.entities.Recensione;
import com.example.Java26_Team1_FinalProject.repositories.RecensioniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecensioneService {

    @Autowired
    private RecensioniRepository recensioniRepository;

    public Recensione aggiungiRecensione(Recensione newRecensione) {
        return recensioniRepository.save(newRecensione);
    }

    public List<Recensione> elencoRecensioni() {
        return recensioniRepository.findAll();
    }

    public Optional<Recensione> getRecensioniById(Long id) {
        return recensioniRepository.findById(id);
    }

    public Optional<Recensione> modificareRecensioni(Long id, Recensione recensione) {
        Optional<Recensione> optionalRecensione = recensioniRepository.findById(id);
        if (optionalRecensione.isPresent()) {
            Recensione recensioneDaModificare = optionalRecensione.get();
            recensioneDaModificare.setNome(recensione.getNome());
            recensioneDaModificare.setVoti(recensione.getVoti());
            recensioneDaModificare.setCitta(recensione.getCitta());
            recensioneDaModificare.setDescrizione(recensione.getDescrizione());


            recensioniRepository.save(recensioneDaModificare);
            return Optional.of(recensioneDaModificare);
        }
        return Optional.empty();
    }
}
