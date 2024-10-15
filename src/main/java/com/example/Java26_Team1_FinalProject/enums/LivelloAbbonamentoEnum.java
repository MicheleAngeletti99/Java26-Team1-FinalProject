package com.example.Java26_Team1_FinalProject.enums;

public enum LivelloAbbonamentoEnum {
    BASIC(1,"abbonamento basic", 5.99),
    MEDIUM(2,"abbonamento medium", 8.99),
    PREMIUM(3,"abbonamento premium", 13.99);

    private Integer id;
    private String descrizione;
    private Double prezzo;

    LivelloAbbonamentoEnum(Integer id, String descrizione, Double prezzo) {
        this.id = id;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
    }

    public Integer getId() {
        return id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Double getPrezzo() {
        return prezzo;
    }
}
