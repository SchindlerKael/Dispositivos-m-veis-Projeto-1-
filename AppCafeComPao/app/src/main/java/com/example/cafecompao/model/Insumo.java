package com.example.cafecompao.model;

public class Insumo {
    private Long ID;
    private String nome;
    private String descricao;
    private InsumoType tipo;

    public Insumo(Long ID, String nome, String descricao, InsumoType tipo) {
        this.ID = ID;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public Insumo(String nome, String descricao, InsumoType tipo) {
        this.ID = null;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "nome: "+getNome()+" - descrição: "+getDescricao()+" - Tipo: "+getTipo();
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public InsumoType getTipo() {
        return tipo;
    }

    public void setTipo(InsumoType tipo) {
        this.tipo = tipo;
    }

}
