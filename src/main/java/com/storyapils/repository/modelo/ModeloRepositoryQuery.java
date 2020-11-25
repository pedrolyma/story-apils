package com.storyapils.repository.modelo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.storyapils.model.Modelo;
import com.storyapils.repository.filter.ModeloFilter;

public interface ModeloRepositoryQuery {
	
	public Page<Modelo> filtrar(ModeloFilter modeloFilter, Pageable pageable);

}
