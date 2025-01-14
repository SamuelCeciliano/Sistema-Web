package com.vsmanutencoes.sistemaweb.controller;

import com.vsmanutencoes.sistemaweb.models.Equipamento;
import com.vsmanutencoes.sistemaweb.models.Servico;
import com.vsmanutencoes.sistemaweb.service.EquipamentoService;
import com.vsmanutencoes.sistemaweb.service.ServicoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;
    
    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public String listarEquipamentos(Model model) {
        model.addAttribute("equipamentos", equipamentoService.listarTodosEquipamentos());
        return "equipamentos";
    }


    @GetMapping("/new")
    public String novoEquipamentoForm(Model model) {
        model.addAttribute("equipamento", new Equipamento());
        model.addAttribute("servicos", servicoService.listarTodosServicos()); // Lista de serviços para o formulário
        return "equipamento-form";
    }

    @PostMapping("/save")
    public String salvarEquipamento(@ModelAttribute("equipamento") Equipamento equipamento,
                                     @RequestParam List<Long> servicoIds) {
        // Carregar os serviços selecionados
        List<Servico> servicosSelecionados = servicoService.buscarServicosPorIds(servicoIds);
        
        // Atribuir os serviços ao equipamento
        equipamento.setServicos(servicosSelecionados);

        // Salvar o equipamento com os serviços associados
        equipamentoService.salvarEquipamento(equipamento);
        return "redirect:/equipamentos";
    }


    @GetMapping("/edit/{id}")
    public String editarEquipamentoForm(@PathVariable Long id, Model model) {
        Equipamento equipamento = equipamentoService.buscarEquipamentoPorId(id);
        model.addAttribute("equipamento", equipamento);
        model.addAttribute("servicos", servicoService.listarTodosServicos()); // Lista de serviços para o formulário
        return "equipamento-form";
    }

    @GetMapping("/delete/{id}")
    public String excluirEquipamento(@PathVariable("id") Long id) {
        equipamentoService.excluirEquipamento(id);
        return "redirect:/equipamentos";
    }
}
