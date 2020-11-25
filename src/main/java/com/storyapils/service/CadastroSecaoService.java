package com.storyapils.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.storyapils.model.Secao;
import com.storyapils.repository.SecaoRepository;

@Service
public class CadastroSecaoService {
	
	@Autowired
	private SecaoRepository secaoRepository;
	
	public Secao atualizar(Long codigo, Secao secao) {
		Secao secaoSalva = buscarSecaoPeloCodigo(codigo);
		
		BeanUtils.copyProperties(secao, secaoSalva, "codigo");
		return secaoRepository.save(secaoSalva);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Secao secaoSalva = buscarSecaoPeloCodigo(codigo);
		secaoSalva.setStatussecao(ativo);
		secaoRepository.save(secaoSalva);
	}
	
	public Secao buscarSecaoPeloCodigo(Long codigo) {
		Optional<Secao> secaoSalva = secaoRepository.findById(codigo);
		if (secaoSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return secaoSalva.get();
	}

}
