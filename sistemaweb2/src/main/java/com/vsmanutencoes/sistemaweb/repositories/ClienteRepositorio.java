package com.vsmanutencoes.sistemaweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vsmanutencoes.sistemaweb.models.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c WHERE " +
           "(:id IS NULL OR c.id = :id) AND " +
           "(:nome IS NULL OR LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
           "(:cnpjCpf IS NULL OR c.cnpjCpf LIKE CONCAT('%', :cnpjCpf, '%')) AND " +
           "(:empresa IS NULL OR LOWER(c.empresa) LIKE LOWER(CONCAT('%', :empresa, '%')))")
    List<Cliente> filtrarClientes(
        @Param("id") Long id,
        @Param("nome") String nome,
        @Param("cnpjCpf") String cnpjCpf,
        @Param("empresa") String empresa
    );
}