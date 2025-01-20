package com.vsmanutencoes.sistemaweb.controller;

import com.vsmanutencoes.sistemaweb.models.Orcamento;
import com.vsmanutencoes.sistemaweb.service.ClienteService;
import com.vsmanutencoes.sistemaweb.service.EmailService;
import com.vsmanutencoes.sistemaweb.service.EquipamentoService;
import com.vsmanutencoes.sistemaweb.service.OrcamentoService;

import jakarta.mail.MessagingException;

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

    @Autowired
    private EmailService emailService;

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
        try {
            // Verificar se a data de criação precisa ser inicializada
            if (orcamento.getDataCriacao() == null) {
                orcamento.setDataCriacao(LocalDateTime.now());
            }

            // Verificar se é uma atualização ou um novo orçamento
            if (orcamento.getId() != null) {
                // Atualiza o orçamento existente
                Orcamento existente = orcamentoService.buscarPorId(orcamento.getId());
                if (existente != null) {
                    existente.setCliente(orcamento.getCliente());
                    existente.setEquipamentos(orcamento.getEquipamentos());
                    existente.setStatus(orcamento.getStatus());
                    existente.setValorTotal(orcamento.getValorTotal());
                    existente.setDescricao(orcamento.getDescricao()); // Atualiza a descrição
                    orcamentoService.salvarOrcamento(existente);
                } else {
                    throw new IllegalArgumentException("Orçamento com ID " + orcamento.getId() + " não encontrado.");
                }
            } else {
                // Cria um novo orçamento
                orcamentoService.salvarOrcamento(orcamento);
            }

            // Enviar e-mail após salvar o orçamento
            enviarEmailOrcamento(orcamento);

        } catch (Exception e) {
            e.printStackTrace();
            // Adicionar log ou redirecionar para uma página de erro
            return "redirect:/orcamentos?error=true";
        }

        return "redirect:/orcamentos?success=true";
    }

    // Método para envio de e-mail
    private void enviarEmailOrcamento(Orcamento orcamento) throws MessagingException {
        String destinatario = orcamento.getCliente().getEmail();
        String assunto = "Novo Orçamento Criado";
        String mensagem = "<h1>Detalhes do Orçamento</h1>" +
                        "<p>Olá, " + orcamento.getCliente().getNome() + ",</p>" +
                        "<p>Seu orçamento foi criado com sucesso.</p>" +
                        "<p><strong>Status:</strong> " + orcamento.getStatus() + "</p>" +
                        "<p><strong>Descrição:</strong> " + orcamento.getDescricao() + "</p>" +
                        "<p><strong>Valor Total:</strong> R$ " + orcamento.getValorTotal() + "</p>" +
                        "<p>Obrigado por escolher nossos serviços.</p>";

        emailService.enviarEmail(destinatario, assunto, mensagem);
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
