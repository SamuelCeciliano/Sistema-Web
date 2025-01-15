package com.vsmanutencoes.sistemaweb.service;

import com.vsmanutencoes.sistemaweb.models.Orcamento;
import com.vsmanutencoes.sistemaweb.repositories.OrcamentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepositorio orcamentoRepository;

    public List<Orcamento> listarTodos() {
        return orcamentoRepository.findAll();
    }

    public Orcamento buscarPorId(Long id) {
        return orcamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orçamento não encontrado"));
    }

    public void salvarOrcamento(Orcamento orcamento) {
        orcamentoRepository.save(orcamento);
    }

    public void deletarOrcamento(Long id) {
        if (orcamentoRepository.existsById(id)) {
            orcamentoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Orçamento não encontrado");
        }
    }
}