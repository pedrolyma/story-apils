package com.storyapils.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storyapils.model.Modelo;
import com.storyapils.repository.modelo.ModeloRepositoryQuery;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long>, ModeloRepositoryQuery {
	
	public Optional<Modelo> findByDescricao(String descricao);

}
