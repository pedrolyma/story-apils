package com.storyapils.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.storyapils.model.Cores;
import com.storyapils.repository.CoresRepository;

@Service
public class CadastroCoresService {
	
	@Autowired
	private CoresRepository coresRepository;
	
	public Cores atualizar(Long codigo, Cores cores) {
		Cores coresSalva = buscarCoresPeloCodigo(codigo);
		
		BeanUtils.copyProperties(cores, coresSalva, "codigo");
		return coresRepository.save(coresSalva);
	}

	public Cores buscarCoresPeloCodigo(Long codigo) {
		Optional<Cores> coresSalva = coresRepository.findById(codigo);
		if (coresSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return coresSalva.get();
	}
}
