package com.storyapils.repository.cores;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.storyapils.model.Cores;
import com.storyapils.repository.filter.CoresFilter;

public interface CoresRepositoryQuery {
	
	public Page<Cores> filtrar(CoresFilter coresFilter, Pageable pageable);

}
