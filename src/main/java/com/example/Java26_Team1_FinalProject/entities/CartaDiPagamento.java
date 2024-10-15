package com.example.Java26_Team1_FinalProject.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.example.Java26_Team1_FinalProject.enums.CircuitoCartaDiPagamentoEnum;

@Entity
@Table(name = "carta_di_pagamento")
public class CartaDiPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CircuitoCartaDiPagamentoEnum circuito;  // Tipo di circuito della carta (es. VISA, MASTERCARD)

    private String numeroCarta; // Numero della carta di pagamento
    private String dataDiScadenza; // Data di scadenza
    private Integer cvv; // Codice di sicurezza

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;

    public CartaDiPagamento() {
    }

    public CartaDiPagamento(CircuitoCartaDiPagamentoEnum circuito, String numeroCarta, String dataDiScadenza, Integer cvv, Cliente cliente) {
        this.circuito = circuito;
        this.numeroCarta = numeroCarta;
        this.dataDiScadenza = dataDiScadenza;
        this.cvv = cvv;
        this.cliente = cliente;
    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public CircuitoCartaDiPagamentoEnum getCircuito() {
        return circuito;
    }

    public void setCircuito(CircuitoCartaDiPagamentoEnum circuito) {
        this.circuito = circuito;
    }

    public String getNumeroCarta() {
        return numeroCarta;
    }

    public void setNumeroCarta(String numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    public String getDataDiScadenza() {
        return dataDiScadenza;
    }

    public void setDataDiScadenza(String dataDiScadenza) {
        this.dataDiScadenza = dataDiScadenza;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}