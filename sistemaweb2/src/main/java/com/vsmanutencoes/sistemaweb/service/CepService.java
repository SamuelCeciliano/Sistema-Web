package com.vsmanutencoes.sistemaweb.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.vsmanutencoes.sistemaweb.models.Endereco;

@Service
public class CepService {
    
    public Endereco buscarEnderecoPorCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url, Endereco.class);
}
}
