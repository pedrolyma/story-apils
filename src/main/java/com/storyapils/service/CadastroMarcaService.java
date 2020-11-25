package com.storyapils.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.storyapils.model.Marca;
import com.storyapils.repository.MarcaRepository;

@Service
public class CadastroMarcaService {
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	public Marca atualizar(Long codigo, Marca marca) {
		Marca marcaSalva = buscarMarcaPeloCodigo(codigo);
		
		BeanUtils.copyProperties(marca, marcaSalva, "codigo");
		return marcaRepository.save(marcaSalva);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Marca marcaSalva = buscarMarcaPeloCodigo(codigo);
		marcaSalva.setStatusmarca(ativo);
		marcaRepository.save(marcaSalva);
	}
	
	public Marca buscarMarcaPeloCodigo(Long codigo) {
		Optional<Marca> marcaSalva = marcaRepository.findById(codigo);
		if (marcaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return marcaSalva.get();
	}

}
