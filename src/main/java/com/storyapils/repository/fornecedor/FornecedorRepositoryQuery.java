package com.storyapils.repository.fornecedor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.storyapils.model.Fornecedor;
import com.storyapils.repository.filter.FornecedorFilter;

public interface FornecedorRepositoryQuery {

	public Page<Fornecedor> filtrar(FornecedorFilter fornecedorFilter, Pageable pageable);
}
