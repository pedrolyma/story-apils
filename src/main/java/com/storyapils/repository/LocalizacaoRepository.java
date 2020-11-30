package com.storyapils.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storyapils.model.Localizacao;
import com.storyapils.repository.localizacao.LocalizacaoRepositoryQuery;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {
	
//	public Optional<Localizacao> findByDescricao(String descricao);

}
