package com.example.Java26_Team1_FinalProject.services;

import com.example.Java26_Team1_FinalProject.entities.Albergo;
import com.example.Java26_Team1_FinalProject.repositories.AlbergoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbergoService {
    // parametri
    @Autowired
    private AlbergoRepository albergoRepository;

    // I METODI
    // metodo per aggiungere albergo
    public Albergo aggiungiAlbergo(Albergo newAlbergo){
        return albergoRepository.save(newAlbergo);
    }
    // metodo per rimuovere albergo
    public boolean rimuovereAlbergo(Long id){
        Optional<Albergo> optionalAlbergo = albergoRepository.findById(id);
        if(optionalAlbergo.isPresent()){
            albergoRepository.deleteById(id);
            return true;
        }
        return false;
    }
    // metodo per visualizzare il elenco di albergi
    public List<Albergo> elencoAlberghi(){
        return albergoRepository.findAll();
    }

    // metodo per visualizzare un albergo
    public Optional<Albergo> getAlbergoById(Long id){
        return albergoRepository.findById(id);
    }
    // metodo per modifica albergo
    public Optional<Albergo> modificareAlbergo(Long id,Albergo albergo){
        Optional<Albergo> optionalAlbergo = albergoRepository.findById(id);
                if(optionalAlbergo.isPresent()){
                    Albergo albergoDaModificare = optionalAlbergo.get();
                    albergoDaModificare.setNome(albergo.getNome());
                    albergoDaModificare.setNumeroDiCamere(albergo.getNumeroDiCamere());
                    albergoDaModificare.setCancellazionePrenotazioneGratuita(albergo.getCancellazionePrenotazioneGratuita());
                    albergoDaModificare.setOrarioCheckIn(albergo.getOrarioCheckIn());
                    albergoDaModificare.setOrarioCheckOut(albergo.getOrarioCheckOut());
                    albergoDaModificare.setCitta(albergoDaModificare.getCitta());
                    albergoDaModificare.setRatingMedio(albergo.getRatingMedio());
                    albergoDaModificare.setServices(albergo.getServices());
                    albergoDaModificare.setPrenotazioni(albergo.getPrenotazioni());
                    return Optional.of( albergoRepository.save(albergoDaModificare));
                }
                 return Optional.empty();
    }

    // METODI PER LE LISTE

    // metodo per aggiungere servizi alla lista
    public boolean aggiungiServizio(Long id, Integer servizi){
        Optional<Albergo> albergoOptional = albergoRepository.findById(id);
        if(albergoOptional.isPresent()){
           Albergo albergo = albergoOptional.get();
           albergo.getServices().add(servizi);
           albergoRepository.save(albergo);
           return true;
        }
        return false;

    }
    // metodo per eliminare un servizio dalla lista
    public boolean eliminaServizio(Long idAlbergo, Integer servizio ){
        Optional<Albergo> albergoOptional = albergoRepository.findById(idAlbergo);
        if(albergoOptional.isPresent()){
            Albergo albergo = albergoOptional.get();
            albergo.getServices().remove(servizio);
            albergoRepository.save(albergo);
            return true;
        }
        return false;
    }
}
