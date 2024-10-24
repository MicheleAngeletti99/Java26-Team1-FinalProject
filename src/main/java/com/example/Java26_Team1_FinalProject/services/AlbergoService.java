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
        Optional<Albergo> optionalAlbergo = albergoRepository.findActiveById(id);
        if(optionalAlbergo.isPresent()){
            albergoRepository.logicDeleteById(id);
            return true;
        }
        return false;
    }
    // metodo per visualizzare il elenco di albergi
    public List<Albergo> elencoAlberghi(){
        return albergoRepository.findAllActive();
    }

    // metodo per visualizzare un albergo
    public Optional<Albergo> getAlbergoById(Long id){
        return albergoRepository.findActiveById(id);
    }
    // metodo per modifica albergo
    public Optional<Albergo> modificareAlbergo(Long id,Albergo albergo){
        Optional<Albergo> optionalAlbergo = albergoRepository.findActiveById(id);
                if(optionalAlbergo.isPresent()){
                    Albergo albergoDaModificare = optionalAlbergo.get();
                    albergoDaModificare.setEmail(albergo.getEmail());
                    albergoDaModificare.setPassword(albergo.getPassword());
                    albergoDaModificare.setContattoTelefonico(albergo.getContattoTelefonico());
                    albergoDaModificare.setNome(albergo.getNome());
                    albergoDaModificare.setNumeroDiCamere(albergo.getNumeroDiCamere());
                    albergoDaModificare.setCancellazionePrenotazioneGratuita(albergo.getCancellazionePrenotazioneGratuita());
                    albergoDaModificare.setInfoOrari(albergo.getInfoOrari());
                    albergoDaModificare.setCitta(albergo.getCitta());
                    albergoDaModificare.setRatingMedio(albergo.getRatingMedio());
                    albergoDaModificare.setServices(albergo.getServices());
                    albergoDaModificare.setPrenotazioni(albergo.getPrenotazioni());
                    albergoDaModificare.setActive(albergo.isActive());
                    return Optional.of( albergoRepository.save(albergoDaModificare));
                }
                 return Optional.empty();
    }

}
