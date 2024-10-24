package com.example.Java26_Team1_FinalProject.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
    // Chiave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // Campi
    private Integer numeroPersone;
    private LocalDateTime dataArrivo;
    private LocalDateTime dataPartenza;
    private List<Integer> serviziRichiestiIds;
    @Column(name = "is_active")
    private boolean isActive = true; // campo per l'eliminazione logica
    private boolean confermaPagamento = false;
    private Double costoTotale = 0.00;
    // chiavi esterne
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_albergo")
    private Albergo albergo;
    @ManyToOne
    @JoinColumn(name = "id_ente")
    private Ente ente;
    @ManyToMany
    @JoinTable(
            name = "prenotazioni_servizi",
            joinColumns = @JoinColumn(name = "prenotazioni_id"),
            inverseJoinColumns = @JoinColumn(name = "servizi_id")
    )
    private List<Servizi> servizi;

    // Costruttore senza argomenti e con tutti gli argomenti
    public Prenotazione() {
    }

    public Prenotazione(Long id, Integer numeroPersone, LocalDateTime dataArrivo, LocalDateTime dataPartenza, List<Servizi> servizi,
                        Cliente cliente, Albergo albergo, Ente ente) {
        this.id = id;
        this.numeroPersone = numeroPersone;
        this.dataArrivo = dataArrivo;
        this.dataPartenza = dataPartenza;
        this.servizi = servizi;
        this.cliente = cliente;
        this.albergo = albergo;
        this.ente = ente;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public Integer getNumeroPersone() {
        return numeroPersone;
    }

    public void setNumeroPersone(Integer numeroPersone) {
        this.numeroPersone = numeroPersone;
    }

    public LocalDateTime getDataArrivo() {
        return dataArrivo;
    }

    public void setDataArrivo(LocalDateTime dataArrivo) {
        this.dataArrivo = dataArrivo;
    }

    public LocalDateTime getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(LocalDateTime dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

    public List<Servizi> getServizi() {
        return servizi;
    }

    public void setServizi(List<Servizi> servizi) {
        this.servizi = servizi;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isConfermaPagamento() {
        return confermaPagamento;
    }

    public void setConfermaPagamento(boolean confermaPagamento) {
        this.confermaPagamento = confermaPagamento;
    }

    public Double getCostoTotale() {
        return costoTotale;
    }

    public void setCostoTotale(Double costoTotale) {
        this.costoTotale = costoTotale;
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
