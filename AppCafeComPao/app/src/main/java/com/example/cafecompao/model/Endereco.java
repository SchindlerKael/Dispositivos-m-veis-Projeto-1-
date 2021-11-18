package com.example.cafecompao.model;

public class Endereco {
    private Long ID;
    private String cidade;
    private String uf;
    private String bairro;
    private String rua;
    private String numero;
    private String complemento;

    public Endereco(Long ID, String cidade, String uf, String bairro, String rua, String numero, String complemento){
        this.ID = ID;
        this.cidade = cidade;
        this.uf = uf;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Endereco(String cidade, String uf, String bairro, String rua, String numero, String complemento){
        this.ID = null;
        this.cidade = cidade;
        this.uf = uf;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    @Override
    public String toString() {
        return rua+", "+numero+" "+complemento+" - "+bairro+", "+cidade+" - "+uf;
    }

    public Long getID() { return ID; }

    public void setID(Long ID) { this.ID = ID; }

    public String getCidade() { return cidade; }

    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getUf() { return uf; }

    public void setUf(String uf) { this.uf = uf; }

    public String getBairro() { return bairro; }

    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getRua() { return rua; }

    public void setRua(String rua) { this.rua = rua; }

    public String getNumero() { return numero; }

    public void setNumero(String numero) { this.numero = numero; }

    public String getComplemento() { return complemento; }

    public void setComplemento(String complemento) { this.complemento = complemento; }
}
