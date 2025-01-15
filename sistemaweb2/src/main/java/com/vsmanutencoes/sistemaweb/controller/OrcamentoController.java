package com.vsmanutencoes.sistemaweb.controller;

import com.vsmanutencoes.sistemaweb.models.Orcamento;
import com.vsmanutencoes.sistemaweb.service.ClienteService;
import com.vsmanutencoes.sistemaweb.service.EquipamentoService;
import com.vsmanutencoes.sistemaweb.service.OrcamentoService;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orcamentos")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EquipamentoService equipamentoService;

    @GetMapping
    public String listarOrcamentos(Model model) {
        model.addAttribute("orcamentos", orcamentoService.listarTodos());
        return "orcamentos";
    }

    @GetMapping("/new")
    public String criarOrcamento(Model model) {
        model.addAttribute("orcamento", new Orcamento());
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("equipamentos", equipamentoService.listarTodos());
        return "orcamento-form";
    }

    @PostMapping("/save")
    public String salvarOrcamento(@ModelAttribute Orcamento orcamento) {
        if (orcamento.getDataCriacao() == null) {
            orcamento.setDataCriacao(LocalDateTime.now());
        }
        if (orcamento.getId() != null) {
            // Atualiza o orçamento existente
            Orcamento existente = orcamentoService.buscarPorId(orcamento.getId());
            existente.setCliente(orcamento.getCliente());
            existente.setEquipamentos(orcamento.getEquipamentos());
            existente.setStatus(orcamento.getStatus());
            existente.setValorTotal(orcamento.getValorTotal());
            orcamentoService.salvarOrcamento(existente);
        } else {
            // Cria um novo orçamento
            orcamentoService.salvarOrcamento(orcamento);
        }
        return "redirect:/orcamentos";
    }


    @GetMapping("/edit/{id}")
    public String editarOrcamento(@PathVariable Long id, Model model) {
        model.addAttribute("orcamento", orcamentoService.buscarPorId(id));
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("equipamentos", equipamentoService.listarTodos());
        return "orcamento-form";
    }

    @GetMapping("/delete/{id}")
    public String deletarOrcamento(@PathVariable Long id) {
        orcamentoService.deletarOrcamento(id);
        return "redirect:/orcamentos";
    }
    
    
    //Exibir detalhes
    @GetMapping("/{id}")
    public String exibirDetalhes(@PathVariable Long id, Model model) {
        Orcamento orcamento = orcamentoService.buscarPorId(id); // ou outro serviço que retorna o orçamento
        if (orcamento != null) {
            model.addAttribute("orcamento", orcamento);
        } else {
            // Tratar caso não encontre o orçamento
            throw new RuntimeException("Orçamento não encontrado");
        }
        return "orcamento-detalhe"; // Nome do template HTML
    }

}
