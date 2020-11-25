package com.storyapils.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.storyapils.model.Marca;
import com.storyapils.model.Unidade;
import com.storyapils.repository.MarcaRepository;
import com.storyapils.repository.UnidadeRepository;

@Service
public class CadastroUnidadeService {

	@Autowired
	private UnidadeRepository unidadeRepository;
	
	public Unidade atualizar(Long codigo, Unidade unidade) {
		Unidade unidadeSalva = buscarUnidadePeloCodigo(codigo);
		
		BeanUtils.copyProperties(unidade, unidadeSalva, "codigo");
		return unidadeRepository.save(unidadeSalva);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Unidade unidadeSalva = buscarUnidadePeloCodigo(codigo);
		unidadeSalva.setStatusunidade(ativo);
		unidadeRepository.save(unidadeSalva);
	}
	
	public Unidade buscarUnidadePeloCodigo(Long codigo) {
		Optional<Unidade> unidadeSalva = unidadeRepository.findById(codigo);
		if (unidadeSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return unidadeSalva.get();
	}
}
