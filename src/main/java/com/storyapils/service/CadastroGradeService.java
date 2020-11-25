package com.storyapils.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.storyapils.model.Grade;
import com.storyapils.model.Marca;
import com.storyapils.repository.GradeRepository;

@Service
public class CadastroGradeService {
	
	@Autowired
	private GradeRepository gradeRepository;
	
	public Grade atualizar(Long codigo, Grade grade) {
		Grade gradeSalva = buscarGradePeloCodigo(codigo);
		BeanUtils.copyProperties(grade, gradeSalva, "codigo");
		return gradeRepository.save(gradeSalva);
	}
	
//	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
//		Grade gradeSalva = buscarGradePeloCodigo(codigo);
//		gradeSalva.setStatusgrade(ativo);
//		gradeRepository.save(gradeSalva);
//	}

	private Grade buscarGradePeloCodigo(Long codigo) {
		Optional<Grade> gradeSalva = gradeRepository.findById(codigo);
		if (gradeSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return gradeSalva.get();
	}

}
