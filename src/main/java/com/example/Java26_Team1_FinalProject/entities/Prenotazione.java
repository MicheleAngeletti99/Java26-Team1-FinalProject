package com.example.Java26_Team1_FinalProject.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_albergo")
    private Albergo albergo;

    private Integer numeroPersone;
    private Date dataArrivo;
    private Date dataPartenza;
    private String serviziRichiesti;

    public Prenotazione() {
    }

    public Prenotazione(Long id, Cliente cliente, Albergo albergo, Integer numeroPersone,
                        Date dataArrivo, Date dataPartenza, String serviziRichiesti) {
        this.id = id;
        this.cliente = cliente;
        this.albergo = albergo;
        this.numeroPersone = numeroPersone;
        this.dataArrivo = dataArrivo;
        this.dataPartenza = dataPartenza;
        this.serviziRichiesti = serviziRichiesti;
    }

    public Long getId() {
        return id;
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

    public String getServiziRichiesti() {
        return serviziRichiesti;
    }

    public void setServiziRichiesti(String serviziRichiesti) {
        this.serviziRichiesti = serviziRichiesti;
    }
}
