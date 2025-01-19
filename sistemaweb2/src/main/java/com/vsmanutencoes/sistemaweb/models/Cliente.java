package com.vsmanutencoes.sistemaweb.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O nome é obrigatório")
    private String nome;
    
    @Column(nullable = false)
    @Email(message = "O email deve ser válido")
    private String email;

    @NotEmpty(message = "O CNPJ/CPF é obrigatório")
    private String cnpjCpf;

    @NotEmpty(message = "A empresa é obrigatória")
    private String empresa;

    @NotEmpty(message = "O telefone é obrigatório")
    private String telefone;

    @NotEmpty(message = "O email da NF é obrigatório")
    @Email(message = "O email da NF deve ser válido")
    private String emailNf;

    private String site;

    @NotEmpty(message = "O CEP é obrigatório")
    private String cep;

    @NotEmpty(message = "A UF é obrigatória")
    @Size(min = 2, max = 2, message = "A UF deve ter 2 caracteres")
    private String uf;

    @NotEmpty(message = "A cidade é obrigatória")
    private String cidade;

    @NotEmpty(message = "O endereço é obrigatório")
    private String endereco;

    @NotEmpty(message = "O número é obrigatório")
    private String numero;

    private String complemento;

    private String bairro;

    @NotEmpty(message = "O país é obrigatório")
    private String pais;
    
    private boolean ativo;
    
    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmailNf() {
        return emailNf;
    }

    public void setEmailNf(String emailNf) {
        this.emailNf = emailNf;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
