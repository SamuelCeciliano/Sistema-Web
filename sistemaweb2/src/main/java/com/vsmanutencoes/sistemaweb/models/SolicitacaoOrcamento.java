package com.vsmanutencoes.sistemaweb.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class SolicitacaoOrcamento.
 */
@Entity
public class SolicitacaoOrcamento {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The descricao. */
    @NotEmpty(message = "A descrição é obrigatória")
    private String descricao;

    /** The equipamentos. */
    @ManyToMany
    private List<Equipamento> equipamentos;

    /** The data. */
    private LocalDate data;

    /** The hora. */
    private LocalTime hora;
    
    @Enumerated(EnumType.STRING)
    private StatusSolicitacao status = StatusSolicitacao.PENDENTE;


    /** The cliente. */
    @ManyToOne
    private Cliente cliente;
    
    /**
     * Instantiates a new solicitacao orcamento.
     */
    public SolicitacaoOrcamento() {
    	super();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	// Getters e Setters
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the descricao.
	 *
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Sets the descricao.
	 *
	 * @param descricao the new descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Gets the equipamentos.
	 *
	 * @return the equipamentos
	 */
	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	/**
	 * Sets the equipamentos.
	 *
	 * @param equipamentos the new equipamentos
	 */
	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public LocalDate getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(LocalDate data) {
		this.data = data;
	}

	/**
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public LocalTime getHora() {
		return hora;
	}

	/**
	 * Sets the hora.
	 *
	 * @param hora the new hora
	 */
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
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
