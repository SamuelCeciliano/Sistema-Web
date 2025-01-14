package com.vsmanutencoes.sistemaweb.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.vsmanutencoes.sistemaweb.models.Equipamento;

public interface EquipamentoRepositorio extends JpaRepository<Equipamento, Long> {
	
}
