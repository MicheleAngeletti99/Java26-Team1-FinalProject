package com.example.Java26_Team1_FinalProject.entities;

import com.example.Java26_Team1_FinalProject.enums.ServizioEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String infoOrari;
    private String citta;
    private Double prezzoPersona;
    @OneToMany(mappedBy = "albergo")
    @JsonIgnore
    private List<Prenotazione> prenotazioni;
    private Double ratingMedio;

    // costruttore vuoto
   public Albergo(){

    }

    // costruttore con tutti i field
    public Albergo(Long id, String email, String password, String contattoTelefonico, String nome, Integer numeroDiCamere,
                   Double prezzoPersona, List<Integer> services, Boolean cancellazionePrenotazioneGratuita,String infoOrari, String citta,List<Prenotazione> prenotazioni, Double ratingMedio) {
        super(id, email, password, contattoTelefonico);
        this.nome = nome;
        this.numeroDiCamere = numeroDiCamere;
        this.services = services;
        this.cancellazionePrenotazioneGratuita = cancellazionePrenotazioneGratuita;
        this.infoOrari = infoOrari;
        this.citta = citta;
        this.prezzoPersona = prezzoPersona;
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

    public String getInfoOrari() {
        return infoOrari;
    }

    public void setInfoOrari(String infoOrari) {
        this.infoOrari = infoOrari;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public Double getPrezzoPersona() {
        return prezzoPersona;
    }

    public void setPrezzoPersona(Double prezzoPersona) {
        this.prezzoPersona = prezzoPersona;
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
