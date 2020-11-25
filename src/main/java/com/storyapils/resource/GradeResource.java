package com.storyapils.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.storyapils.event.RecursoCriadoEvent;
import com.storyapils.model.Grade;
import com.storyapils.repository.GradeRepository;
import com.storyapils.repository.filter.GradeFilter;
import com.storyapils.service.CadastroGradeService;

@RestController
@RequestMapping("/grades")
public class GradeResource {

	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private CadastroGradeService gradeService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Grade> pesquisar(GradeFilter gradeFilter, Pageable pageable) {
		return gradeRepository.filtrar(gradeFilter, pageable);
	}
	
	@PostMapping
	public ResponseEntity<Grade> criar(@Valid @RequestBody Grade grade, HttpServletResponse response) {
		Grade gradeSalva = gradeRepository.save(grade);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, gradeSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(gradeSalva);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Grade> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Grade> grade = gradeRepository.findById(codigo);
		return grade != null ? ResponseEntity.of(grade) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		gradeRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Grade> atualizar(@PathVariable Long codigo, @Valid @RequestBody Grade grade) {
		Grade gradeSalva = gradeService.atualizar(codigo, grade);
		return ResponseEntity.ok(gradeSalva);
	}
	
//	@PutMapping("/{codigo}/ativo")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
//		gradeService.atualizarPropriedadeAtivo(codigo, ativo);
//	}
}
