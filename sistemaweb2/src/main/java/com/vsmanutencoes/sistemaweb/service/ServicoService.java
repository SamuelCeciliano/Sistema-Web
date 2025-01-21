package com.vsmanutencoes.sistemaweb.service;

import com.vsmanutencoes.sistemaweb.models.Servico;
import com.vsmanutencoes.sistemaweb.repositories.ServicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepositorio servicoRepositorio;

    // Criar novo serviço
    public Servico salvarServico(Servico servico) {
        return servicoRepositorio.save(servico);
    }

    // Obter serviço por ID
    public Servico buscarServicoPorId(Long id) {
        return servicoRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("Servico nao encontrado")); 
    }
    
    public List<Servico> buscarServicosPorIds(List<Long> servicoIds) {
        return servicoRepositorio.findAllById(servicoIds);
    }

    // Listar todos os serviços
    public List<Servico> listarTodosServicos() {
        return servicoRepositorio.findAll();
    }

    // Atualizar serviço
    public Servico atualizarServico(Long id, Servico servicoAtualizado) {
        Servico servico = buscarServicoPorId(id);
        servico.setNome(servicoAtualizado.getNome());
        servico.setMateriais(servicoAtualizado.getMateriais());
        return servicoRepositorio.save(servico);
    }
    
    // Excluir serviço
    public void excluirServico(Long id) {
        if (servicoRepositorio.existsById(id)) {
            servicoRepositorio.deleteById(id);
        } else {
            throw new RuntimeException("Serviço não encontrado com o ID: " + id);
        }
    }

    public List<Servico> filtrarServicos(Long id, String nome) {
        return servicoRepositorio.filtrarServicos(id, nome);
    }
}
