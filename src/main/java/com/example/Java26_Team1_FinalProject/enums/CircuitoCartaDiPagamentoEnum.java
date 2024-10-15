package com.example.Java26_Team1_FinalProject.enums;

public enum CircuitoCartaDiPagamentoEnum {
    VISA(1, "Visa"),
    MASTERCARD(2, "Mastercard"),
    AMERICAN_EXPRESS(3, "American Express"),
    PAYPAL(4, "Paypal");

    private Integer id;
    private String circuito;

    CircuitoCartaDiPagamentoEnum(Integer id, String circuito) {
        this.id = id;
        this.circuito = circuito;
    }

    public String getCircuito() {
        return circuito;
    }

    public Integer getId() {
        return id;
    }
}
