package com.storyapils.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storyapils.model.Unidade;
import com.storyapils.repository.unidade.UnidadeRepositoryQuery;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
	
//	public Optional<Unidade> findByDescricao(String descricao);

}
