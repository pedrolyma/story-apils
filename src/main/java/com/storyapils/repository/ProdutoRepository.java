package com.storyapils.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storyapils.model.Produto;
import com.storyapils.repository.produto.ProdutoRepositoryQuery;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQuery {

	public Optional<Produto> findByDescricaoProduto(String descricaoProduto);
}
