package com.storyapils.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.storyapils.model.Pedido;
import com.storyapils.model.Secao;
import com.storyapils.repository.PedidoRepository;
import com.storyapils.repository.SecaoRepository;

@Service
public class CadastroPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido atualizar(Long codigo, Pedido pedido) {
		Pedido pedidoSalvo = buscarPedidoPeloCodigo(codigo);
		
		BeanUtils.copyProperties(pedido, pedidoSalvo, "codigo");
		return pedidoRepository.save(pedidoSalvo);
	}

//	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
//		Pedido pedidoSalvo = buscarPedidoPeloCodigo(codigo);
//		pedidoSalvo.setStatussecao(ativo);
//		secaoRepository.save(secaoSalva);
//	}
	
	public Pedido buscarPedidoPeloCodigo(Long codigo) {
		Optional<Pedido> pedidoSalvo = pedidoRepository.findById(codigo);
		if (pedidoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pedidoSalvo.get();
	}

}
