package com.example.Java26_Team1_FinalProject.entities;

import com.example.Java26_Team1_FinalProject.enums.ServizioEnum;
import jakarta.persistence.*;

import java.util.Date;
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
    private Date dataArrivo;
    private Date dataPartenza;
    private List<Integer> serviziRichiestiIds;
    private boolean isActive = true; // campo per l'eliminazione logica
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

    // Costruttore senza argomenti e con tutti gli argomenti
    public Prenotazione() {
    }

    public Prenotazione(Long id, Integer numeroPersone, Date dataArrivo, Date dataPartenza, List<Integer> serviziRichiestiIds,
                        Cliente cliente, Albergo albergo, Ente ente) {
        this.id = id;
        this.numeroPersone = numeroPersone;
        this.dataArrivo = dataArrivo;
        this.dataPartenza = dataPartenza;
        this.serviziRichiestiIds = serviziRichiestiIds;
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

    public Date getDataArrivo() {
        return dataArrivo;
    }

    public void setDataArrivo(Date dataArrivo) {
        this.dataArrivo = dataArrivo;
    }

    public Date getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(Date dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

    public List<Integer> getServiziRichiestiIds() {
        return serviziRichiestiIds;
    }

    public void setServiziRichiestiIds(List<Integer> serviziRichiestiIds) {
        this.serviziRichiestiIds = serviziRichiestiIds;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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
