package com.example.Java26_Team1_FinalProject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table

public class Recensioni extends Utente {
    private  Integer voti;
    private Integer percentualiVoti;
    private Integer percentualeVotiTotali;
    private String citta;
    private  String nome;

    public Recensioni(){}

    public Recensioni(Integer voti, Integer percentualiVoti, Integer percentualeVotiTotali, String citta, String nome) {
        this.voti = voti;
        this.percentualiVoti = percentualiVoti;
        this.percentualeVotiTotali = percentualeVotiTotali;
        this.citta = citta;
        this.nome = nome;

    }

    public Integer getVoti() {
        return voti;
    }

    public void setVoti(Integer voti) {
        this.voti = voti;
    }

    public Integer getPercentualiVoti() {
        return percentualiVoti;
    }

    public void setPercentualiVoti(Integer percentualiVoti) {
        this.percentualiVoti = percentualiVoti;
    }

    public Integer getPercentualeVotiTotali() {
        return percentualeVotiTotali;
    }

    public void setPercentualeVotiTotali(Integer percentualeVotiTotali) {
        this.percentualeVotiTotali = percentualeVotiTotali;
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
}
