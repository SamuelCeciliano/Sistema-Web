package com.vsmanutencoes.sistemaweb.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vsmanutencoes.sistemaweb.models.Cliente;
import com.vsmanutencoes.sistemaweb.models.Endereco;
import com.vsmanutencoes.sistemaweb.repositories.ClienteRepositorio;
import com.vsmanutencoes.sistemaweb.service.CepService;
import com.vsmanutencoes.sistemaweb.service.ClienteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CepService cepService; // Serviço de consulta de CEP

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    // Listar todos os clientes
    @GetMapping
    public String listarClientes(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("username", username);
        model.addAttribute("clientes", clienteService.listarTodosClientes());
        return "clientes";
    }

    // Exibir formulário de novo cliente
    @GetMapping("/new")
    public String novoClienteForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-form";
    }

    // Salvar novo cliente ou editar cliente existente
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

    // Método para buscar endereço via CEP
    @GetMapping("/cep")
    @ResponseBody
    public Endereco buscarEnderecoPorCep(@RequestParam String cep) {
        return cepService.buscarEnderecoPorCep(cep); // Chama o serviço que retorna o endereço
    }

    @PostMapping("/toggleStatus/{id}")
    public ResponseEntity<Void> toggleStatus(@PathVariable Long id) {
        Optional<Cliente> clienteOpt = clienteRepositorio.findById(id);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            cliente.setAtivo(!cliente.isAtivo());
            clienteRepositorio.save(cliente);
            System.out.println("Cliente atualizado: " + cliente); // Log
            return ResponseEntity.ok().build();
        }
        System.out.println("Cliente não encontrado para o ID: " + id); // Log
        return ResponseEntity.notFound().build();
    }

}
