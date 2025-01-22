package com.vsmanutencoes.sistemaweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsmanutencoes.sistemaweb.models.Material;
import com.vsmanutencoes.sistemaweb.repositories.MaterialRepositorio;



@Service
public class MaterialService {
	
	@Autowired
    private MaterialRepositorio materialRepositorio;

    // Criar novo equipamento
    public Material salvarMaterial(Material material) {
        return materialRepositorio.save(material);
    }

    // Método único para buscar um ou mais equipamentos
    public Material buscarMaterialPorId(Long id) {
        return materialRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("Material nao encontrado"));
    }
    
    public List<Material> buscarMateriaisPorIds(List<Long> materialIds) {
        return materialRepositorio.findAllById(materialIds);
    }

    // Listar todos os equipamentos
    public List<Material> listarTodosMateriais() {
        return materialRepositorio.findAll();
    }

    // Atualizar equipamento
    public Material atualizarMaterial(Long id, Material materialAtualizado) {
        Material material = buscarMaterialPorId(id);
        material.setNome(materialAtualizado.getNome());
        material.setModelo(materialAtualizado.getModelo());
        material.setMarca(materialAtualizado.getMarca());
        return materialRepositorio.save(material);
    }

    // Excluir equipamento
    public void excluirMaterial(Long id) {
        if (materialRepositorio.existsById(id)) {
            materialRepositorio.deleteById(id);
        } else {
            throw new RuntimeException("Equipamento não encontrado com o ID: " + id);
        }
    }

    public List<Material> buscarMateriais(String nome, String marca, String modelo) {
        // Se todos os filtros forem nulos ou vazios, retorna todos os materiais
        if ((nome == null || nome.isEmpty()) &&
            (marca == null || marca.isEmpty()) &&
            (modelo == null || modelo.isEmpty())) {
            return materialRepositorio.findAll();
        }
        // Busca com filtros
        return materialRepositorio.findByFiltros(nome, marca, modelo);
    }
}