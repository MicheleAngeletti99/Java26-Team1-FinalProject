package com.example.Java26_Team1_FinalProject.enums;

public enum ServizioEnum {
    PISCINA(1, "Piscina", 10.00),
    SEGGIOVIA(2, "Seggiovia",20.99),
    IDROMASSAGGIO(3,"Vasca idromaggaggio", 15.00),
    COLAZIONE(4, "Colazione",8.50),
    PRANZO(5,"Pranzo", 18.00),
    CENA(6, "Cena", 20.00),
    SPA(7, "Spa", 40.00),
    TAXI(8,"Taxi",20.00),
    BABYCLUB(9,"Club per bambini", 10.00),
    OPENBAR(10, "Open bar", 19.99);

    private Integer id;
    private String descrizione;
    private Double prezzo;

    ServizioEnum(Integer id, String descrizione, Double prezzo) {
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
