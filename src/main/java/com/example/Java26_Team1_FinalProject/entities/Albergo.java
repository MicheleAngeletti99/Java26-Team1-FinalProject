package com.example.Java26_Team1_FinalProject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class Albergo extends Utente {
    // i field
    private String nome;
    private Integer numeroDiCamere;
    // deve mettere una lista di Servizi
    private List<String> services;
    private Boolean cancellazionePrenotazioneGratuita;
    private LocalDateTime orarioCheckIn;
    private LocalDateTime orarioCheckOut;
    private String citta;
    // deve mettere una lista di Recenzioni
    private List<String> recenzioni;
    private Double ratingMedio;

    // costruttore vuoto
   public Albergo(){

    }

    // costruttore con tutti i field
    public Albergo(String email, String password, String contattoTelefonico, String nome, Integer numeroDiCamere, List<String> services, Boolean cancellazionePrenotazioneGratuita, LocalDateTime orarioCheckIn, LocalDateTime orarioCheckOut, String citta, List<String> recenzioni, Double ratingMedio) {
        super(email, password, contattoTelefonico);
        this.nome = nome;
        this.numeroDiCamere = numeroDiCamere;
        this.services = services;
        this.cancellazionePrenotazioneGratuita = cancellazionePrenotazioneGratuita;
        this.orarioCheckIn = orarioCheckIn;
        this.orarioCheckOut = orarioCheckOut;
        this.citta = citta;
        this.recenzioni = recenzioni;
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

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
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

    public List<String> getRecenzioni() {
        return recenzioni;
    }

    public void setRecenzioni(List<String> recenzioni) {
        this.recenzioni = recenzioni;
    }

    public Double getRatingMedio() {
        return ratingMedio;
    }

    public void setRatingMedio(Double
                                       ratingMedio) {
        this.ratingMedio = ratingMedio;
    }
}
