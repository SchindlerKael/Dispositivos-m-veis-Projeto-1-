package com.example.cafecompao.model;


import java.time.LocalTime;
import java.util.List;

public class Servico {
    private Long ID;
    private LocalTime horario;
    private String observacao;
    private Usuario usuario;
    private Plano plano;
    private List<Insumo> insumos;

    public Servico(Long ID, LocalTime horario, String observacao, Usuario usuario, Plano plano, List<Insumo> insumos) {
        this.ID = ID;
        this.horario = horario;
        this.observacao = observacao;
        this.usuario = usuario;
        this.plano = plano;
        this.insumos = insumos;
    }

    public Servico(LocalTime horario, String observacao, Usuario usuario, Plano plano, List<Insumo> insumos) {
        this.ID = null;
        this.horario = horario;
        this.observacao = observacao;
        this.usuario = usuario;
        this.plano = plano;
        this.insumos = insumos;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public List<Insumo> getInsumos() {
        return insumos;
    }

    public void setInsumos(List<Insumo> insumos) {
        this.insumos = insumos;
    }
}
