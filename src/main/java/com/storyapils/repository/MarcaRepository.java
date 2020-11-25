package com.storyapils.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storyapils.model.Marca;
import com.storyapils.repository.marca.MarcaRepositoryQuery;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long>, MarcaRepositoryQuery {

	public Optional<Marca> findByDescricao(String descricao);
}
