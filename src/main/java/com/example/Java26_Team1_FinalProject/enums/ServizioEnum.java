package com.example.Java26_Team1_FinalProject.enums;

public enum ServizioEnum {
    PISCINA(1,'A',"Piscina"),
    SEGGIOVIA(2, 'E', "Seggiovia");

    private Integer id;
    private Character AlbergoOEnte;
    private String descrizione;

    ServizioEnum(Integer id, Character albergoOEnte, String descrizione) {
        this.id = id;
        AlbergoOEnte = albergoOEnte;
        this.descrizione = descrizione;
    }

    public Integer getId() {
        return id;
    }

    public Character getAlbergoOEnte() {
        return AlbergoOEnte;
    }

    public String getDescrizione() {
        return descrizione;
    }
}
