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

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_albergo")
    private Albergo albergo;

    @ManyToOne
    @JoinColumn(name = "id_ente")
    private Ente ente;

    public Recensione() {
    }

    public Recensione(Long id, Integer voti, String citta, String nome, String descrizione, Cliente cliente, Albergo albergo, Ente ente) {
        this.id = id;
        this.voti = voti;
        this.citta = citta;
        this.nome = nome;
        this.descrizione = descrizione;
        this.cliente = cliente;
        this.albergo = albergo;
        this.ente = ente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
}
