package com.vsmanutencoes.sistemaweb.service;

import com.vsmanutencoes.sistemaweb.models.SolicitacaoOrcamento;
import com.vsmanutencoes.sistemaweb.repositories.SolicitacaoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class SolicitacaoOrcamentoService {

    @Autowired
    private SolicitacaoRepositorio solicitacaoRepositorio;

    public void salvarSolicitacao(SolicitacaoOrcamento solicitacao) {
        solicitacaoRepositorio.save(solicitacao);
    }

    public List<SolicitacaoOrcamento> listarTodasSolicitacoes() {
        return solicitacaoRepositorio.findAll();
    }

    public SolicitacaoOrcamento buscarSolicitacaoPorId(Long id) {
        return solicitacaoRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("Solicitração nao encontrado"));
    }
    
    public List<SolicitacaoOrcamento> buscarSolicitacoesPorIds(List<Long> solicitacaoIds) {
        return solicitacaoRepositorio.findAllById(solicitacaoIds);
    }
    
    public SolicitacaoOrcamento atualizarSolicitacao(Long id, SolicitacaoOrcamento solicitacaoAtualizado) {
    	SolicitacaoOrcamento solicitacao = buscarSolicitacaoPorId(id);
    	solicitacao.setData(solicitacaoAtualizado.getData());
    	solicitacao.setHora(solicitacaoAtualizado.getHora());
    	solicitacao.setDescricao(solicitacaoAtualizado.getDescricao());
    	solicitacao.setCliente(solicitacaoAtualizado.getCliente());
    	solicitacao.setEquipamentos(solicitacaoAtualizado.getEquipamentos());
        return solicitacaoRepositorio.save(solicitacao);
    }

    public void excluirSolicitacao(Long id) {
        solicitacaoRepositorio.deleteById(id);
    }

    public List<SolicitacaoOrcamento> filtrarSolicitacoes(String nome, String empresa, String cnpj, String equipamento, LocalDate data) {
        return solicitacaoRepositorio.filtrarSolicitacoes(nome, empresa, cnpj, equipamento, data);
    }
}
