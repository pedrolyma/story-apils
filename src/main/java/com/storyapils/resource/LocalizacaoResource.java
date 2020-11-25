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
import com.storyapils.model.Localizacao;
import com.storyapils.repository.LocalizacaoRepository;
import com.storyapils.repository.filter.LocalizacaoFilter;
import com.storyapils.service.CadastroLocalizacaoService;

@RestController
@RequestMapping("/localizacao")
public class LocalizacaoResource {

	@Autowired
	private LocalizacaoRepository localizacaoRepository;
	
	@Autowired
	private CadastroLocalizacaoService localizacaoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Localizacao> pesquisar(LocalizacaoFilter localizacaoFilter, Pageable pageable) {
		return localizacaoRepository.filtrar(localizacaoFilter, pageable);
	}
	
	@PostMapping
	public ResponseEntity<Localizacao> criar(@Valid @RequestBody Localizacao localizacao, HttpServletResponse response) {
		Localizacao localizacaoSalva = localizacaoRepository.save(localizacao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, localizacaoSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(localizacaoSalva);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Localizacao> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Localizacao> localizacao = localizacaoRepository.findById(codigo);
		return localizacao != null ? ResponseEntity.of(localizacao) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		localizacaoRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Localizacao> atualizar(@PathVariable Long codigo, @Valid @RequestBody Localizacao localizacao) {
		Localizacao localizacaoSalva = localizacaoService.atualizar(codigo, localizacao);
		return ResponseEntity.ok(localizacaoSalva);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		localizacaoService.atualizarPropriedadeAtivo(codigo, ativo);
	}

}
