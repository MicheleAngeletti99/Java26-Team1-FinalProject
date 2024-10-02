package com.example.Java26_Team1_FinalProject.entities;

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
    private List<Integer> serviziEnumIds;
    private String citta;
    private String linkWeb;
    private String recensione;
    private Long ratingAVG;
    @OneToMany
    private List<Prenotazione> prenotazioni;

    //Costruttori
    public Ente(){}

    public Ente(Long id, String email, String password, String contattoTelefonico,
                String nome, List<Integer> serviziEnumIds, String citta, String linkWeb,
                String recensione, Long ratingAVG, List<Prenotazione> prenotazioni) {
        super(id, email, password, contattoTelefonico);
        this.nome = nome;
        this.serviziEnumIds = serviziEnumIds;
        this.citta = citta;
        this.linkWeb = linkWeb;
        this.recensione = recensione;
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

    public List<Integer> getServiziEnumIds() {
        return serviziEnumIds;
    }

    public void setServiziEnumIds(List<Integer> serviziEnumIds) {
        this.serviziEnumIds = serviziEnumIds;
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

    public String getRecensione() {
        return recensione;
    }

    public void setRecensione(String recensione) {
        this.recensione = recensione;
    }

    public Long getRatingAVG() {
        return ratingAVG;
    }

    public void setRatingAVG(Long ratingAVG) {
        this.ratingAVG = ratingAVG;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public void deletePrenotazione(Optional<Prenotazione> prenotazione){this.prenotazioni.remove(prenotazione);}
}
