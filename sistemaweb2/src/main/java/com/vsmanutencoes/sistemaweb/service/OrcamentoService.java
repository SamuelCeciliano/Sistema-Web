package com.vsmanutencoes.sistemaweb.service;

import com.vsmanutencoes.sistemaweb.models.*;
import com.vsmanutencoes.sistemaweb.repositories.EquipamentoRepositorio;
import com.vsmanutencoes.sistemaweb.repositories.OrcamentoRepositorio;
import com.vsmanutencoes.sistemaweb.repositories.ServicoOrcamentoRepositorio;
import com.vsmanutencoes.sistemaweb.repositories.ServicoRepositorio;
import com.vsmanutencoes.sistemaweb.repositories.SolicitacaoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrcamentoService {

	@Autowired
    private OrcamentoRepositorio orcamentoRepositorio;

    @Autowired
    private SolicitacaoRepositorio solicitacaoOrcamentoRepositorio;

    @Autowired
    private EquipamentoRepositorio equipamentoRepositorio;

    @Autowired
    private ServicoRepositorio servicoRepositorio;
    
    @Autowired
    private ServicoOrcamentoRepositorio servicoOrcamentoRepositorio;

    public Orcamento criarOrcamento(Long solicitacaoId) {
        // Pega a solicitação de orçamento
        SolicitacaoOrcamento solicitacaoOrcamento = solicitacaoOrcamentoRepositorio.findById(solicitacaoId)
                .orElseThrow(() -> new RuntimeException("Solicitação de Orçamento não encontrada"));

        // Cria o orçamento baseado na solicitação
        Orcamento orcamento = new Orcamento();
        orcamento.setCliente(solicitacaoOrcamento.getCliente());
        orcamento.setEquipamentos(solicitacaoOrcamento.getEquipamentos());
        orcamento.setStatus(StatusSolicitacao.PENDENTE); // Status inicial
        orcamento.setDataCriacao(LocalDate.now());

        // Puxa os serviços associados a cada equipamento
        List<ServicoOrcamento> servicosOrcamento = new ArrayList<>();
        for (Equipamento equipamento : solicitacaoOrcamento.getEquipamentos()) {
            // Aqui, buscamos os serviços relacionados ao equipamento
            List<Servico> servicosEquipamento = equipamento.getServicos();
            for (Servico servico : servicosEquipamento) {
                ServicoOrcamento servicoOrcamento = new ServicoOrcamento();
                servicoOrcamento.setServico(servico);
                servicosOrcamento.add(servicoOrcamento);
            }
        }

        // Associa os serviços ao orçamento
        orcamento.setServicos(servicosOrcamento);

        // Calcula o valor total
        BigDecimal valorTotal = calcularValorTotal(servicosOrcamento);
        orcamento.setValorTotal(valorTotal);

        // Salva o orçamento
        return orcamentoRepositorio.save(orcamento);
    }

    private BigDecimal calcularValorTotal(List<ServicoOrcamento> servicosOrcamento) {
        BigDecimal total = BigDecimal.ZERO;
        for (ServicoOrcamento servico : servicosOrcamento) {
            total = total.add(servico.getPrecoUnitario().multiply(BigDecimal.valueOf(servico.getQuantidadeHoras())));
        }
        return total;
    }

    // Buscar todos os orçamentos
    public List<Orcamento> listarOrcamentos() {
        return orcamentoRepositorio.findAll();
    }

    // Buscar um orçamento específico
    public Orcamento buscarOrcamento(Long id) {
        Optional<Orcamento> orcamento = orcamentoRepositorio.findById(id);
        return orcamento.orElse(null);  // Retorna null se não encontrar o orçamento
    }

    // Excluir Orçamento
    public void excluirOrcamento(Long id) {
        Optional<Orcamento> orcamentoExistente = orcamentoRepositorio.findById(id);
        if (orcamentoExistente.isPresent()) {
            orcamentoRepositorio.delete(orcamentoExistente.get());
        }
    }

    // Adicionar ou remover serviços do orçamento
    public ServicoOrcamento adicionarServicoAoOrcamento(Long orcamentoId, ServicoOrcamento servicoOrcamento) {
        Optional<Orcamento> orcamentoExistente = orcamentoRepositorio.findById(orcamentoId);
        if (orcamentoExistente.isPresent()) {
            Orcamento orcamento = orcamentoExistente.get();
            servicoOrcamento.setOrcamento(orcamento);
            servicoOrcamentoRepositorio.save(servicoOrcamento);
            orcamento.getServicos().add(servicoOrcamento);
            orcamentoRepositorio.save(orcamento);
            return servicoOrcamento;
        }
        return null;
    }
    
    /**
     * Método responsável por salvar um orçamento.
     * @param orcamento O orçamento a ser salvo.
     * @return O orçamento salvo.
     */
    public Orcamento salvarOrcamento(Orcamento orcamento) {
        return orcamentoRepositorio.save(orcamento); // Salva o orçamento no banco de dados
    }
}
