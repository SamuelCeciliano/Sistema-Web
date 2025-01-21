package com.vsmanutencoes.sistemaweb.service;

import com.vsmanutencoes.sistemaweb.models.Equipamento;
import com.vsmanutencoes.sistemaweb.repositories.EquipamentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepositorio equipamentoRepositorio;

    // Criar novo equipamento
    public Equipamento salvarEquipamento(Equipamento equipamento) {
        return equipamentoRepositorio.save(equipamento);
    }
    
    public List<Equipamento> listarTodos() {
        return equipamentoRepositorio.findAll();
    }

    // Método único para buscar um ou mais equipamentos
    public Equipamento buscarEquipamentoPorId(Long id) {
        return equipamentoRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("Equipamento nao encontrado"));
    }
    
    public List<Equipamento> buscarEquipamentosPorIds(List<Long> equipamentoIds) {
        return equipamentoRepositorio.findAllById(equipamentoIds);
    }

    // Listar todos os equipamentos
    public List<Equipamento> listarTodosEquipamentos() {
        return equipamentoRepositorio.findAll();
    }

    // Atualizar equipamento
    public Equipamento atualizarEquipamento(Long id, Equipamento equipamentoAtualizado) {
        Equipamento equipamento = buscarEquipamentoPorId(id);
        equipamento.setNome(equipamentoAtualizado.getNome());
        equipamento.setModelo(equipamentoAtualizado.getModelo());
        equipamento.setMarca(equipamentoAtualizado.getMarca());
        equipamento.setServicos(equipamentoAtualizado.getServicos());
        return equipamentoRepositorio.save(equipamento);
    }

    // Excluir equipamento
    public void excluirEquipamento(Long id) {
        if (equipamentoRepositorio.existsById(id)) {
            equipamentoRepositorio.deleteById(id);
        } else {
            throw new RuntimeException("Equipamento não encontrado com o ID: " + id);
        }
    }

    public List<Equipamento> filtrarEquipamentos(Long id, String nome, String modelo, String marca) {
        return equipamentoRepositorio.filtrarEquipamentos(id, nome, modelo, marca);
    }
}
