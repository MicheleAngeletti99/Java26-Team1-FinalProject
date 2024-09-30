package com.example.Java26_Team1_FinalProject.entities;

import com.example.Java26_Team1_FinalProject.enums.ServizioEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class Albergo extends Utente {
    // i field
    private String nome;
    private Integer numeroDiCamere;
    private List<Integer> services;
    private Boolean cancellazionePrenotazioneGratuita;
    private LocalDateTime orarioCheckIn;
    private LocalDateTime orarioCheckOut;
    private String citta;
    @OneToMany
    private List<Prenotazione> prenotazioni;
    private Double ratingMedio;

    // costruttore vuoto
   public Albergo(){

    }

    // costruttore con tutti i field
    public Albergo(Long id, String email, String password, String contattoTelefonico, String nome, Integer numeroDiCamere,
                   List<Integer> services, Boolean cancellazionePrenotazioneGratuita, LocalDateTime orarioCheckIn,
                   LocalDateTime orarioCheckOut, String citta,List<Prenotazione> prenotazioni, Double ratingMedio) {
        super(id, email, password, contattoTelefonico);
        this.nome = nome;
        this.numeroDiCamere = numeroDiCamere;
        this.services = services;
        this.cancellazionePrenotazioneGratuita = cancellazionePrenotazioneGratuita;
        this.orarioCheckIn = orarioCheckIn;
        this.orarioCheckOut = orarioCheckOut;
        this.citta = citta;
        this.prenotazioni = prenotazioni;
        this.ratingMedio = ratingMedio;
    }

    // i getter e setter
    public String getNome(){
       return nome;
    }
    public void setNome(String nome){
       this.nome = nome;
    }

    public Integer getNumeroDiCamere(){
       return numeroDiCamere;
    }
    public void setNumeroDiCamere(Integer numeroDiCamere){
       this.numeroDiCamere = numeroDiCamere;
    }

    public List<Integer> getServices() {
        return services;
    }

    public void setServices(List<Integer> services) {
        this.services = services;
    }

    public Boolean getCancellazionePrenotazioneGratuita() {
        return cancellazionePrenotazioneGratuita;
    }

    public void setCancellazionePrenotazioneGratuita(Boolean cancellazionePrenotazioneGratuita) {
        this.cancellazionePrenotazioneGratuita = cancellazionePrenotazioneGratuita;
    }

    public LocalDateTime getOrarioCheckIn() {
        return orarioCheckIn;
    }

    public void setOrarioCheckIn(LocalDateTime orarioCheckIn) {
        this.orarioCheckIn = orarioCheckIn;
    }

    public LocalDateTime getOrarioCheckOut() {
        return orarioCheckOut;
    }

    public void setOrarioCheckOut(LocalDateTime orarioCheckOut) {
        this.orarioCheckOut = orarioCheckOut;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public Double getRatingMedio() {
        return ratingMedio;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public void setRatingMedio(Double ratingMedio) {
        this.ratingMedio = ratingMedio;
    }
}
