package com.vsmanutencoes.sistemaweb.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class ServicoOrcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Orcamento orcamento;

    @ManyToOne
    private Servico servico;

    private Integer quantidadeHoras;

    private BigDecimal precoUnitario;

    @ManyToMany
    private List<Material> materiais;

	@Override
	public String toString() {
		return "ServicoOrcamento [id=" + id + ", orcamento=" + orcamento + ", servico=" + servico + ", quantidadeHoras="
				+ quantidadeHoras + ", precoUnitario=" + precoUnitario + ", materiais=" + materiais + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Integer getQuantidadeHoras() {
		return quantidadeHoras;
	}

	public void setQuantidadeHoras(Integer quantidadeHoras) {
		this.quantidadeHoras = quantidadeHoras;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public List<Material> getMateriais() {
		return materiais;
	}

	public void setMateriais(List<Material> materiais) {
		this.materiais = materiais;
	}

	public ServicoOrcamento() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}
