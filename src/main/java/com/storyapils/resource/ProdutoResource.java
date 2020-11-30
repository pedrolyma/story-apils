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
import com.storyapils.model.Produto;
import com.storyapils.model.Secao;
import com.storyapils.repository.ProdutoRepository;
import com.storyapils.repository.filter.ProdutoFilter;
import com.storyapils.repository.filter.SecaoFilter;
import com.storyapils.service.CadastroProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CadastroProdutoService produtoService;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Produto> pesquisar(ProdutoFilter produtoFilter, Pageable pageable) {
		return produtoRepository.filtrar(produtoFilter, pageable);
	}
	
	@PostMapping
	public ResponseEntity<Produto> criar(@Valid @RequestBody Produto produto, HttpServletResponse response) {
		Produto produtoSalvo = produtoRepository.save(produto);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, produtoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Produto> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Produto> produto = produtoRepository.findById(codigo);
		return produto != null ? ResponseEntity.of(produto) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		produtoRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Produto> atualizar(@PathVariable Long codigo, @Valid @RequestBody Produto produto) {
		Produto produtoSalvo = produtoService.atualizar(codigo, produto);
		return ResponseEntity.ok(produtoSalvo);
	}
	
	@PutMapping("/{codigo}/statusproduto")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		produtoService.atualizarPropriedadeAtivo(codigo, ativo);
	}

}
