package com.storyapils.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.storyapils.model.Produto;
import com.storyapils.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto atualizar(Long codigo, Produto produto) {
		Produto produtoSalvo = buscarProdutoPeloCodigo(codigo);
		
		BeanUtils.copyProperties(produto, produtoSalvo, "codigo");
		return produtoRepository.save(produtoSalvo);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Produto produtoSalvo = buscarProdutoPeloCodigo(codigo);
		produtoSalvo.setStatusProduto(ativo);
		produtoRepository.save(produtoSalvo);
	}
	
	public Produto buscarProdutoPeloCodigo(Long codigo) {
		Optional<Produto> produtoSalvo = produtoRepository.findById(codigo);
		if (produtoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return produtoSalvo.get();
	}

}
