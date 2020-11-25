package com.storyapils.repository.localizacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.storyapils.model.Localizacao;
import com.storyapils.repository.filter.LocalizacaoFilter;

public interface LocalizacaoRepositoryQuery {
	
	public Page<Localizacao> filtrar(LocalizacaoFilter localizacaoFilter, Pageable pageable);

}
