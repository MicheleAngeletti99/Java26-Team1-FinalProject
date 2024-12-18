package com.example.Java26_Team1_FinalProject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Optional;

@Entity
@Table
public class Ente extends Utente{

    //Parametri
    private String nome;
    private String citta;
    private String linkWeb;
    private Long ratingAVG;
    @Column(name = "is_active")
    private boolean isActive = true; // campo per l'eliminazione logica
    @OneToMany(mappedBy = "ente")
    @JsonIgnore
    private List<Prenotazione> prenotazioni;
    @OneToMany(mappedBy = "ente")
    private List<Servizi> servizi;

    //Costruttori
    public Ente(){}

    public Ente(Long id, String email, String password, String contattoTelefonico,
                String nome, String citta, String linkWeb,
                Long ratingAVG, List<Prenotazione> prenotazioni, List<Servizi> servizi) {
        super(id, email, password, contattoTelefonico);
        this.nome = nome;
        this.servizi = servizi;
        this.citta = citta;
        this.linkWeb = linkWeb;
        this.ratingAVG = ratingAVG;
        this.prenotazioni = prenotazioni;
    }

    //Getter and Setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getLinkWeb() {
        return linkWeb;
    }

    public void setLinkWeb(String linkWeb) {
        this.linkWeb = linkWeb;
    }

    public Long getRatingAVG() {
        return ratingAVG;
    }

    public void setRatingAVG(Long ratingAVG) {
        this.ratingAVG = ratingAVG;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public List<Servizi> getServizi() {
        return servizi;
    }

    public void setServizi(List<Servizi> servizi) {
        this.servizi = servizi;
    }
}
