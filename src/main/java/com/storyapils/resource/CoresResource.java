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
import com.storyapils.model.Cores;
import com.storyapils.repository.CoresRepository;
import com.storyapils.repository.filter.CoresFilter;
import com.storyapils.service.CadastroCoresService;

@RestController
@RequestMapping("/cores")
public class CoresResource {
	
	@Autowired
	private CoresRepository coresRepository;
	
	@Autowired
	private CadastroCoresService coresService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Cores> pesquisar(CoresFilter coresFilter, Pageable pageable) {
		return coresRepository.filtrar(coresFilter, pageable);
	}

	@PostMapping
	public ResponseEntity<Cores> criar(@Valid @RequestBody Cores cores, HttpServletResponse response) {
		Cores coresSalva = coresRepository.save(cores);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, coresSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(coresSalva);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Cores> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Cores> cores = coresRepository.findById(codigo);
		return cores != null ? ResponseEntity.of(cores) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		coresRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Cores> atualizar(@PathVariable Long codigo, @Valid @RequestBody Cores cores) {
		Cores coresSalva = coresService.atualizar(codigo, cores);
		return ResponseEntity.ok(coresSalva);
	}
	
//	@PutMapping("/{codigo}/ativo")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
//		coresService.atualizarPropriedadeAtivo(codigo, ativo);
//	}
}
