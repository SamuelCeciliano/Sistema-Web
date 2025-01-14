package com.vsmanutencoes.sistemaweb.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToMany
    private List<Equipamento> equipamentos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orcamento")
    private List<ServicoOrcamento> servicos;

    @Enumerated(EnumType.STRING)
    private StatusSolicitacao status;

    private LocalDate dataCriacao;

    private BigDecimal valorTotal;
    
    @ManyToOne
    private SolicitacaoOrcamento solicitacaoOrcamento;

	

	@Override
	public String toString() {
		return "Orcamento [id=" + id + ", cliente=" + cliente + ", equipamentos=" + equipamentos + ", servicos="
				+ servicos + ", status=" + status + ", dataCriacao=" + dataCriacao + ", valorTotal=" + valorTotal
				+ ", solicitacaoOrcamento=" + solicitacaoOrcamento + "]";
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}



	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}



	public List<ServicoOrcamento> getServicos() {
		return servicos;
	}



	public void setServicos(List<ServicoOrcamento> servicos) {
		this.servicos = servicos;
	}



	public StatusSolicitacao getStatus() {
		return status;
	}



	public void setStatus(StatusSolicitacao status) {
		this.status = status;
	}



	public LocalDate getDataCriacao() {
		return dataCriacao;
	}



	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}



	public BigDecimal getValorTotal() {
		return valorTotal;
	}



	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}



	public SolicitacaoOrcamento getSolicitacaoOrcamento() {
		return solicitacaoOrcamento;
	}



	public void setSolicitacaoOrcamento(SolicitacaoOrcamento solicitacaoOrcamento) {
		this.solicitacaoOrcamento = solicitacaoOrcamento;
	}



	public Orcamento() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}
