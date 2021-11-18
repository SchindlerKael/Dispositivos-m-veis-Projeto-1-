package com.example.cafecompao.model;

public class Usuario {
    private Long ID;
    private String nome;
    private String email;
    private String senha;
    private Endereco endereco;

    public Usuario(Long ID, String nome, String email, String senha, Endereco endereco) {
        this.ID = ID;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    public Usuario(String nome, String email, String senha, Endereco endereco) {
        this.ID = null;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "nome: "+this.getNome();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String tipo) {
        this.senha = senha;
    }

    public Endereco getEndereco() { return endereco; }

    public void setEndereco(Endereco endereco) { this.endereco = endereco; }
}
