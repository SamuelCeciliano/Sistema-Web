package com.vsmanutencoes.sistemaweb.controller;

import com.vsmanutencoes.sistemaweb.models.Cliente;
import com.vsmanutencoes.sistemaweb.service.ClienteService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    // Listar todos os clientes
    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodosClientes());
        return "clientes";
    }

    // Exibir formulário de novo cliente
    @GetMapping("/new")
    public String novoClienteForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-form";
    }

    // Salvar novo cliente ou editar cliente existente ssss
    @PostMapping("/save")
    public String salvarCliente(@Valid Cliente cliente) {
        clienteService.salvarCliente(cliente);
        return "redirect:/clientes";
    }

    // Exibir formulário de edição de cliente
    @GetMapping("/edit/{id}")
    public String editarClienteForm(@PathVariable("id") Long id, Model model) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        model.addAttribute("cliente", cliente);
        return "cliente-form";
    }

    // Excluir cliente
    @GetMapping("/delete/{id}")
    public String excluirCliente(@PathVariable("id") Long id) {
        clienteService.inativarCliente(id);
        return "redirect:/clientes";
    }
}
