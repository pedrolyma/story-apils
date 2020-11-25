package com.storyapils.resource;

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
import com.storyapils.model.Marca;
import com.storyapils.model.Unidade;
import com.storyapils.repository.UnidadeRepository;
import com.storyapils.repository.filter.UnidadeFilter;
import com.storyapils.service.CadastroUnidadeService;

@RestController
@RequestMapping("/unidades")
public class UnidadeResource {
	
	@Autowired
	private UnidadeRepository unidadeRepository;

	@Autowired
	private CadastroUnidadeService unidadeService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Unidade> filtrar(UnidadeFilter unidadeFilter, Pageable pageable) {
		return unidadeRepository.filtrar(unidadeFilter, pageable);
	}
	
	@PostMapping
	public ResponseEntity<Unidade> criar(@Valid @RequestBody Unidade unidade, HttpServletResponse response) {
		Unidade unidadeSalva = unidadeRepository.save(unidade);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, unidadeSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(unidadeSalva);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Unidade> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Unidade> unidade = unidadeRepository.findById(codigo);
		return unidade != null ? ResponseEntity.of(unidade) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		unidadeRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Unidade> atualizar(@PathVariable Long codigo, @Valid @RequestBody Unidade unidade) {
		Unidade unidadeSalva = unidadeService.atualizar(codigo, unidade);
		return ResponseEntity.ok(unidadeSalva);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		unidadeService.atualizarPropriedadeAtivo(codigo, ativo);
	}

}
