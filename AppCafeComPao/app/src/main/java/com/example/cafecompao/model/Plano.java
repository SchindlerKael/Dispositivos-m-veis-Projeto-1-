package com.example.cafecompao.model;

public class Plano {
    private Long ID;
    private String nome;
    private Double valor;
    private Integer qtdComida;
    private Integer qtdBebida;

    public Plano(Long ID, String nome, Double valor, Integer qtdComida, Integer qtdBebida) {
        this.ID = ID;
        this.nome = nome;
        this.valor = valor;
        this.qtdComida = qtdComida;
        this.qtdBebida = qtdBebida;
    }

    public Plano(String nome, Double valor, Integer qtdComida, Integer qtdBebida) {
        this.ID = null;
        this.nome = nome;
        this.valor = valor;
        this.qtdComida = qtdComida;
        this.qtdBebida = qtdBebida;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQtdComida() {
        return qtdComida;
    }

    public void setQtdComida(Integer qtdComida) {
        qtdComida = qtdComida;
    }

    public Integer getQtdBebida() {
        return qtdBebida;
    }

    public void setQtdBebida(Integer qtdBebida) {
        qtdBebida = qtdBebida;
    }
}
