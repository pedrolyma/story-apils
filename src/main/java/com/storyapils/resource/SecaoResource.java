package com.storyapils.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.storyapils.event.RecursoCriadoEvent;
import com.storyapils.model.Secao;
import com.storyapils.repository.SecaoRepository;
import com.storyapils.repository.filter.SecaoFilter;
import com.storyapils.service.CadastroSecaoService;

@RestController
@RequestMapping("/secoes")
public class SecaoResource {
	
	@Autowired
	private SecaoRepository secaoRepository;
	
	@Autowired
	private CadastroSecaoService secaoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Secao> pesquisar(SecaoFilter secaoFilter, Pageable pageable) {
		return secaoRepository.filtrar(secaoFilter, pageable);
	}
	
	@PostMapping
	public ResponseEntity<Secao> criar(@Valid @RequestBody Secao secao, HttpServletResponse response) {
		Secao secaoSalva = secaoRepository.save(secao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, secaoSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(secaoSalva);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Secao> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Secao> secao = secaoRepository.findById(codigo);
		return secao != null ? ResponseEntity.of(secao) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		secaoRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Secao> atualizar(@PathVariable Long codigo, @Valid @RequestBody Secao secao) {
		Secao secaoSalva = secaoService.atualizar(codigo, secao);
		return ResponseEntity.ok(secaoSalva);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		secaoService.atualizarPropriedadeAtivo(codigo, ativo);
	}

}
