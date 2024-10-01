package com.example.Java26_Team1_FinalProject.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Recensione {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private  Integer voti;
    private String citta;
    private  String nome;
    private String descrizione;
    @OneToOne
    @JoinColumn(name = "id_prenotazione")
    private Prenotazione prenotazione;

    public Recensione() {
    }

    public Recensione(Long id, Integer voti, String citta, String nome, String descrizione, Prenotazione prenotazione) {
        this.id = id;
        this.voti = voti;
        this.citta = citta;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prenotazione = prenotazione;

    }

    public Long getId() {
        return id;
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

    public Prenotazione getPrenotazione() {
        return prenotazione;
    }

    public void setPrenotazione(Prenotazione prenotazione) {
        this.prenotazione = prenotazione;
    }
}
