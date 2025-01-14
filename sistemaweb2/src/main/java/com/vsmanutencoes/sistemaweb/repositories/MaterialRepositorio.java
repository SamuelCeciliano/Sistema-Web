package com.vsmanutencoes.sistemaweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vsmanutencoes.sistemaweb.models.Material;

public interface MaterialRepositorio extends JpaRepository<Material, Long>{

}
