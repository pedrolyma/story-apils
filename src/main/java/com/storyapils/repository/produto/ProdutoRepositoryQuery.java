package com.storyapils.repository.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.storyapils.model.Produto;
import com.storyapils.repository.filter.ProdutoFilter;

public interface ProdutoRepositoryQuery {
	
	public Page<Produto> filtrar(ProdutoFilter produtoFilter, Pageable pageable);

}
