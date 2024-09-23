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
                    /* non avevo mezzo le liste:
                      recensione, prenotazione e services
                     */
                    albergoRepository.save(albergoDaModificare);
                    return Optional.of(albergoDaModificare);
                }
                 return Optional.empty();
    }
}
