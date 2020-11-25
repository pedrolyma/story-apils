package com.storyapils.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.storyapils.model.Fornecedor;
import com.storyapils.repository.FornecedorRepository;

@Service
public class CadastroFornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	public Fornecedor atualizar(Long codigo, Fornecedor fornecedor) {
		Fornecedor fornecedorSalvo = buscarFornecedorPeloCodigo(codigo);
		
		BeanUtils.copyProperties(fornecedor, fornecedorSalvo, "codigo");
		return fornecedorRepository.save(fornecedorSalvo);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Fornecedor fornecedorSalvo = buscarFornecedorPeloCodigo(codigo);
		fornecedorSalvo.setStatusFornecedor(ativo);
		fornecedorRepository.save(fornecedorSalvo);
	}
	
	public Fornecedor buscarFornecedorPeloCodigo(Long codigo) {
		Optional<Fornecedor> fornecedorSalvo = fornecedorRepository.findById(codigo);
		if (fornecedorSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return fornecedorSalvo.get();
	}
}
