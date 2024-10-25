package com.example.Java26_Team1_FinalProject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "servizi")
public class Servizi {
    // parametri
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double prezzo;
    @Column(name = "is_active")
    private boolean isActive = true; // campo per l'eliminazione logica
    @ManyToOne
    @JoinColumn(name = "id_albergo")
    @JsonIgnore
    private Albergo albergo;
    @ManyToOne
    @JoinColumn(name = "id_ente")
    @JsonIgnore
    private Ente ente;
    @ManyToMany(mappedBy = "servizi")
    @JsonIgnore
    private List<Prenotazione> prenotazioni;

    // empty constructor
    public Servizi(){}
    // constructor with all the parameters
    public Servizi(Long id, String name, Double prezzo, Albergo albergo, Ente ente, List<Prenotazione> prenotazioni) {
        this.id = id;
        this.name = name;
        this.prezzo = prezzo;
        this.albergo = albergo;
        this.ente = ente;
        this.prenotazioni = prenotazioni;
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Albergo getAlbergo() {
        return albergo;
    }

    public void setAlbergo(Albergo albergo) {
        this.albergo = albergo;
    }

    public Ente getEnte() {
        return ente;
    }

    public void setEnte(Ente ente) {
        this.ente = ente;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

}

