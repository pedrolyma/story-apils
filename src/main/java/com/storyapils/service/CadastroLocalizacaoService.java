package com.storyapils.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.storyapils.model.Localizacao;
import com.storyapils.model.Secao;
import com.storyapils.repository.LocalizacaoRepository;
import com.storyapils.repository.SecaoRepository;

@Service
public class CadastroLocalizacaoService {

	@Autowired
	private LocalizacaoRepository localizacaoRepository;
	
	public Localizacao atualizar(Long codigo, Localizacao localizacao) {
		Localizacao localizacaoSalva = buscarLocalizacaoPeloCodigo(codigo);
		
		BeanUtils.copyProperties(localizacao, localizacaoSalva, "codigo");
		return localizacaoRepository.save(localizacaoSalva);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean statuslocaliza) {
		Localizacao localizacaoSalva = buscarLocalizacaoPeloCodigo(codigo);
		localizacaoSalva.setStatuslocaliza(statuslocaliza);
		localizacaoRepository.save(localizacaoSalva);
	}
	
	public Localizacao buscarLocalizacaoPeloCodigo(Long codigo) {
		Optional<Localizacao> localizacaoSalva = localizacaoRepository.findById(codigo);
		if (localizacaoSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return localizacaoSalva.get();
	}

}
