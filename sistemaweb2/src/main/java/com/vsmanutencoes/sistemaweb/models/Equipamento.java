package com.vsmanutencoes.sistemaweb.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "equipamentos")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String modelo;
    private String marca;

    // Relacionamento com Servico
    @ManyToMany
    private List<Servico> servicos;

    public Equipamento() {
    	super();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public List<Servico> getServicos() {
        return servicos;
    }
    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }
}
