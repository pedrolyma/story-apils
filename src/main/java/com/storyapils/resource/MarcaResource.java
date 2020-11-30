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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.storyapils.event.RecursoCriadoEvent;
import com.storyapils.model.Marca;
import com.storyapils.repository.MarcaRepository;
import com.storyapils.repository.filter.MarcasFilter;
import com.storyapils.service.CadastroMarcaService;

@RestController
@RequestMapping("/marcas")
public class MarcaResource {
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@Autowired
	private CadastroMarcaService marcaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
    @GetMapping
//    @RequestMapping(method = RequestMethod.GET)
	public Page<Marca> pesquisar(MarcasFilter marcasFilter, Pageable pageable) {
		return marcaRepository.filtrar(marcasFilter, pageable);
	}
    
	@PostMapping
	public ResponseEntity<Marca> criar(@Valid @RequestBody Marca marca, HttpServletResponse response) {
		Marca marcaSalva = marcaRepository.save(marca);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, marcaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(marcaSalva);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Marca> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Marca> marca = marcaRepository.findById(codigo);
		return marca != null ? ResponseEntity.of(marca) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		marcaRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Marca> atualizar(@PathVariable Long codigo, @Valid @RequestBody Marca marca) {
		Marca marcaSalva = marcaService.atualizar(codigo, marca);
		return ResponseEntity.ok(marcaSalva);
	}
	
	@PutMapping("/{codigo}/statusmarca")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean statusmarca) {
		marcaService.atualizarPropriedadeAtivo(codigo, statusmarca);
	}

}
