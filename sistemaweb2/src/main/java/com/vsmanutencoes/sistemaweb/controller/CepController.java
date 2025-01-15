package com.vsmanutencoes.sistemaweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vsmanutencoes.sistemaweb.models.Endereco;
import com.vsmanutencoes.sistemaweb.service.CepService;

public class CepController {
    
    @Autowired
    private CepService cepService;

    @GetMapping("/cep")
    public Endereco buscarEndereco(@RequestParam String cep) {
        return cepService.buscarEnderecoPorCep(cep);
    }
}
