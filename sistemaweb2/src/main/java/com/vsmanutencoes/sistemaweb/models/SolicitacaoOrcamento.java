package com.vsmanutencoes.sistemaweb.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Entity
public class SolicitacaoOrcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "A descrição é obrigatória")
    private String descricao;

    @ManyToMany
    private List<Equipamento> equipamentos;

    private LocalDate data;

    private LocalTime hora;
    
    @Enumerated(EnumType.STRING)
    private StatusSolicitacao status = StatusSolicitacao.PENDENTE;


    @ManyToOne
    private Cliente cliente;
    
    public SolicitacaoOrcamento() {
    	super();
	}

	/**
	 *
	 * @return the id
	 */

	public Long getId() {
		return id;
	}

	/**

	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**

	 * @return 
	 */
	public String getDescricao() {
		return descricao;
	}

	/**

	 * @param descricao 
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**

	 * @return 
	 */
	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	/**
	 * @param equipamentos 
	 */
	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	/**
	 * @return
	 */
	public LocalDate getData() {
		return data;
	}

	/**
	 * @param data
	 */
	public void setData(LocalDate data) {
		this.data = data;
	}

	/**
	 * @return
	 */
	public LocalTime getHora() {
		return hora;
	}

	/**
	 * @param hora 
	 */
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the new cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public StatusSolicitacao getStatus() {
		return status;
	}

	public void setStatus(StatusSolicitacao status) {
		this.status = status;
	}  
}