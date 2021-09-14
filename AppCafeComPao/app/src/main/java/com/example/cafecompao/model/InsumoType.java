package com.example.cafecompao.model;

public enum InsumoType {

    COMIDA("comida"),
    BEBIDA("beibida");

    private String descricao;

    InsumoType(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
