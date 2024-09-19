package com.example.Java26_Team1_FinalProject.entities;

import jakarta.persistence.*;

@Entity
@Table

public class Recensioni {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private  Integer voti;
    private String citta;
    private  String nome;
    private String descrizione;

    public Recensioni(){}

    public Recensioni(Integer voti,  String citta, String nome, String descrizione) {
        this.voti = voti;
        this.citta = citta;
        this.nome = nome;
        this.descrizione = descrizione;

    }

    public Integer getVoti() {
        return voti;
    }

    public void setVoti(Integer voti) {
        this.voti = voti;
    }


    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
