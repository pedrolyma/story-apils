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
import com.storyapils.model.Pedido;
import com.storyapils.repository.PedidoRepository;
import com.storyapils.service.CadastroPedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CadastroPedidoService pedidoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
    @GetMapping
	public Page<Pedido> pesquisar(Pageable pageable) {
		return pedidoRepository.findAll(pageable);
	}
    
	@PostMapping
	public ResponseEntity<Pedido> criar(@Valid @RequestBody Pedido pedido, HttpServletResponse response) {
		Pedido pedidoSalvo = pedidoRepository.save(pedido);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pedidoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Pedido> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Pedido> pedido = pedidoRepository.findById(codigo);
		return pedido != null ? ResponseEntity.of(pedido) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		pedidoRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pedido> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pedido pedido) {
		Pedido pedidoSalvo = pedidoService.atualizar(codigo, pedido);
		return ResponseEntity.ok(pedidoSalvo);
	}
	
//	@PutMapping("/{codigo}/ativo")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
//		marcaService.atualizarPropriedadeAtivo(codigo, ativo);
//	}

}
