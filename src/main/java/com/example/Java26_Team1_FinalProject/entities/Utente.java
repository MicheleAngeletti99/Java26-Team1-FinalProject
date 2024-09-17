package com.example.Java26_Team1_FinalProject.entities;


import jakarta.persistence.*;

@MappedSuperclass

public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;
    private String contattoTelefonico;

    public Utente() {}

    public Utente(String email, String password, String contattoTelefonico) {
        this.email = email;
        this.password = password;
        this.contattoTelefonico = contattoTelefonico;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContattoTelefonico() {
        return contattoTelefonico;
    }

    public void setContattoTelefonico(String contattoTelefonico) {
        this.contattoTelefonico = contattoTelefonico;
    }
}
