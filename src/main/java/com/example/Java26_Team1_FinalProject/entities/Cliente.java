package com.example.Java26_Team1_FinalProject.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente extends Utente{
    private String nomeCliente;
    private String cognomeCliente;
    private Date dataDiNascita;
    private List<String> carteDiPagamento;
    private String livelloAbbonamento;

    public Cliente() {}

    public Cliente(String email, String password, String contattoTelefonico, String nomeCliente, String cognomeCliente,
                   Date dataDiNascita, List<String> carteDiPagamento, String livelloAbbonamento) {
        super(email, password, contattoTelefonico);
        this.nomeCliente = nomeCliente;
        this.cognomeCliente = cognomeCliente;
        this.dataDiNascita = dataDiNascita;
        this.carteDiPagamento = carteDiPagamento;
        this.livelloAbbonamento = livelloAbbonamento;
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

    public List<String> getCarteDiPagamento() {
        return carteDiPagamento;
    }

    public void setCarteDiPagamento(List<String> carteDiPagamento) {
        this.carteDiPagamento = carteDiPagamento;
    }

    public String getLivelloAbbonamento() {
        return livelloAbbonamento;
    }

    public void setLivelloAbbonamento(String livelloAbbonamento) {
        this.livelloAbbonamento = livelloAbbonamento;
    }
}
