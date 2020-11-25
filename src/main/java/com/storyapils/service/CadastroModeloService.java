package com.storyapils.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.storyapils.model.Modelo;
import com.storyapils.model.Secao;
import com.storyapils.repository.ModeloRepository;

@Service
public class CadastroModeloService {
	
	@Autowired
	private ModeloRepository modeloRepository;
	
	public Modelo atualizar(Long codigo, Modelo modelo) {
		Modelo modeloSalvo = buscarModeloPeloCodigo(codigo);
		
		BeanUtils.copyProperties(modelo, modeloSalvo, "codigo");
		return modeloRepository.save(modeloSalvo);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Modelo modeloSalvo = buscarModeloPeloCodigo(codigo);
		modeloSalvo.setStatusmodelo(ativo);
		modeloRepository.save(modeloSalvo);
	}
	
	public Modelo buscarModeloPeloCodigo(Long codigo) {
		Optional<Modelo> modeloSalvo = modeloRepository.findById(codigo);
		if (modeloSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return modeloSalvo.get();
	}

}
