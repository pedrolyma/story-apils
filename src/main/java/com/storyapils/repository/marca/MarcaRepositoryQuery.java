package com.storyapils.repository.marca;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.storyapils.model.Marca;
import com.storyapils.repository.filter.MarcasFilter;

public interface MarcaRepositoryQuery {
	
	public Page<Marca> filtrar(MarcasFilter marcasFilter, Pageable pageable);

}
