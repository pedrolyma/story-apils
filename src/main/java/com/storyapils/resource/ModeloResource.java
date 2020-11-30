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
import com.storyapils.model.Modelo;
import com.storyapils.model.Secao;
import com.storyapils.repository.ModeloRepository;
import com.storyapils.repository.filter.ModeloFilter;
import com.storyapils.service.CadastroModeloService;
import com.storyapils.service.CadastroSecaoService;

@RestController
@RequestMapping("/modelos")
public class ModeloResource {
	
	@Autowired
	private ModeloRepository modeloRepository;
	
	@Autowired
	private CadastroModeloService modeloService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Modelo> pesquisar(ModeloFilter modeloFilter, Pageable pageable) {
		return modeloRepository.filtrar(modeloFilter, pageable);
	}
	
	@PostMapping
	public ResponseEntity<Modelo> criar(@Valid @RequestBody Modelo modelo, HttpServletResponse response) {
		Modelo modeloSalvo = modeloRepository.save(modelo);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, modeloSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(modeloSalvo);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Modelo> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Modelo> modelo = modeloRepository.findById(codigo);
		return modelo != null ? ResponseEntity.of(modelo) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		modeloRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Modelo> atualizar(@PathVariable Long codigo, @Valid @RequestBody Modelo modelo) {
		Modelo modeloSalvo = modeloService.atualizar(codigo, modelo);
		return ResponseEntity.ok(modeloSalvo);
	}
	
	@PutMapping("/{codigo}/statusmodelo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean statusmodelo) {
		modeloService.atualizarPropriedadeAtivo(codigo, statusmodelo);
	}

}
