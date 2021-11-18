package com.example.cafecompao.model;

public enum InsumoType {

    COMIDA("C"),
    BEBIDA("B");

    private String descricao;

    InsumoType(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
