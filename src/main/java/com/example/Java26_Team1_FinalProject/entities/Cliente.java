package com.example.Java26_Team1_FinalProject.entities;


import com.example.Java26_Team1_FinalProject.enums.LivelloAbbonamentoEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente extends Utente{
    private String nomeCliente;
    private String cognomeCliente;
    private Date dataDiNascita;

    @OneToMany(mappedBy = "cliente")
    private List<CartaDiPagamento> carteDiPagamento;

    @Enumerated(EnumType.STRING)
    private LivelloAbbonamentoEnum livelloAbbonamento;

    @OneToMany(mappedBy = "cliente")
    private List<Prenotazione> prenotazioni;

    public Cliente() {}

    public Cliente(Long id, String email, String password, String contattoTelefonico, String nomeCliente, String cognomeCliente,
                   Date dataDiNascita, List<CartaDiPagamento> carteDiPagamento, LivelloAbbonamentoEnum livelloAbbonamento,List<Prenotazione> prenotazioni) {
        super(id, email, password, contattoTelefonico);
        this.nomeCliente = nomeCliente;
        this.cognomeCliente = cognomeCliente;
        this.dataDiNascita = dataDiNascita;
        this.carteDiPagamento = carteDiPagamento;
        this.livelloAbbonamento = livelloAbbonamento;
        this.prenotazioni = prenotazioni;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCognomeCliente() {
        return cognomeCliente;
    }

    public void setCognomeCliente(String cognomeCliente) {
        this.cognomeCliente = cognomeCliente;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public List<CartaDiPagamento> getCarteDiPagamento() {
        return carteDiPagamento;
    }

    public void setCarteDiPagamento(List<CartaDiPagamento> carteDiPagamento) {
        this.carteDiPagamento = carteDiPagamento;
    }

    public LivelloAbbonamentoEnum getLivelloAbbonamento() {
        return livelloAbbonamento;
    }

    public void setLivelloAbbonamento(LivelloAbbonamentoEnum livelloAbbonamento) {
        this.livelloAbbonamento = livelloAbbonamento;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }
}
