package com.storyapils.repository.secao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.storyapils.model.Secao;
import com.storyapils.repository.filter.SecaoFilter;

public interface SecaoRepositoryQuery {

	public Page<Secao> filtrar(SecaoFilter secaoFilter, Pageable pageable);
}
