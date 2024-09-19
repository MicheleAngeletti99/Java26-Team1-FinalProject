package com.example.Java26_Team1_FinalProject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table
public class Ente extends Utente{

    //Parametri
    private String nome;
    private List<String> servizi;
    private String citta;
    private String linkWeb;
    private String recensione;
    private Long ratingAVG;

    //Costruttori
    public Ente(){}

    public Ente(String email, String password, String contattoTelefonico, String nome, List<String> servizi, String citta, String linkWeb, String recensione, Long ratingAVG) {
        super(email, password, contattoTelefonico);
        this.nome = nome;
        this.servizi = servizi;
        this.citta = citta;
        this.linkWeb = linkWeb;
        this.recensione = recensione;
        this.ratingAVG = ratingAVG;
    }

    //Getter and Setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getServizi() {
        return servizi;
    }

    public void setServizi(List<String> servizi) {
        this.servizi = servizi;
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
}
