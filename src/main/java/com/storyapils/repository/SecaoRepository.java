package com.storyapils.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storyapils.model.Secao;
import com.storyapils.repository.secao.SecaoRepositoryQuery;

@Repository
public interface SecaoRepository extends JpaRepository<Secao, Long>, SecaoRepositoryQuery {
	
	public Optional<Secao> findByDescricao(String descricao);

}
