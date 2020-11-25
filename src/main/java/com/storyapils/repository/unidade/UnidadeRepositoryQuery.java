package com.storyapils.repository.unidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.storyapils.model.Unidade;
import com.storyapils.repository.filter.UnidadeFilter;

public interface UnidadeRepositoryQuery {

	public Page<Unidade> filtrar(UnidadeFilter unidadeFilter, Pageable pageable);
}
