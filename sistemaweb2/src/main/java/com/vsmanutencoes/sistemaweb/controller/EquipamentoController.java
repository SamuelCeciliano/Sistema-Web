package com.vsmanutencoes.sistemaweb.controller;

import com.vsmanutencoes.sistemaweb.models.Equipamento;
import com.vsmanutencoes.sistemaweb.models.Servico;
import com.vsmanutencoes.sistemaweb.service.EquipamentoService;
import com.vsmanutencoes.sistemaweb.service.ServicoService;

import java.security.Principal;
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
    public String listarEquipamentos(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) String marca,
            Model model, Principal principal) {

        String username = principal.getName();
        model.addAttribute("username", username);

        if (id == null && nome == null && modelo == null && marca == null) {
            model.addAttribute("equipamentos", equipamentoService.listarTodosEquipamentos());
        } else {
            model.addAttribute("equipamentos", equipamentoService.filtrarEquipamentos(id, nome, modelo, marca));
        }
        return "equipamentos";
    }


    @GetMapping("/new")
    public String novoEquipamentoForm(Model model, Principal principal
    ) {
        String username = principal.getName();
        model.addAttribute("username", username);
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
    public String editarEquipamentoForm(@PathVariable Long id, Model model, Principal principal
    ) {
        String username = principal.getName();
        model.addAttribute("username", username);
        Equipamento equipamento = equipamentoService.buscarEquipamentoPorId(id);
        model.addAttribute("equipamento", equipamento);
        model.addAttribute("servicos", servicoService.listarTodosServicos()); // Lista de serviços para o formulário
        return "equipamento-form";
    }

    @GetMapping("/delete/{id}")
    public String excluirEquipamento(@PathVariable("id") Long id, Model model, Principal principal
    ) {
        String username = principal.getName();
        model.addAttribute("username", username);
        equipamentoService.excluirEquipamento(id);
        return "redirect:/equipamentos";
    }
}
