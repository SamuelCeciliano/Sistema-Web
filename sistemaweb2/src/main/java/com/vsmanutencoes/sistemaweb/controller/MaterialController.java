package com.vsmanutencoes.sistemaweb.controller;


import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vsmanutencoes.sistemaweb.models.Material;
import com.vsmanutencoes.sistemaweb.service.MaterialService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/materiais")
public class MaterialController {
	
	@Autowired
	private MaterialService materialService;
	
	// Listar todos os clientes
    @GetMapping
    public String listarMateriais(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo,
            Model model,
            Principal principal) {
        
        String username = principal.getName();
        model.addAttribute("username", username);

        List<Material> materiais = materialService.buscarMateriais(nome, marca, modelo);
        model.addAttribute("materiais", materiais);
        model.addAttribute("nome", nome);
        model.addAttribute("marca", marca);
        model.addAttribute("modelo", modelo);

        return "materiais";
    }


    // Exibir formulário de novo cliente
    @GetMapping("/new")
    public String novoMaterialForm(Model model) {
        model.addAttribute("material", new Material());
        return "material-form";
    }

    // Salvar novo cliente ou editar cliente existente
    @PostMapping("/save")
    public String salvarMaterial(@Valid Material material) {
        materialService.salvarMaterial(material);
        return "redirect:/materiais";
    }

    // Exibir formulário de edição de cliente
    @GetMapping("/edit/{id}")
    public String editarMaterialForm(@PathVariable Long id, Model model) {
        Material material = materialService.buscarMaterialPorId(id);
        model.addAttribute("material", material);
        return "material-form";
    }

    // Excluir cliente
    @GetMapping("/delete/{id}")
    public String excluirMaterial(@PathVariable("id") Long id) {
        materialService.excluirMaterial(id);
        return "redirect:/materiais";
    }
	
}
