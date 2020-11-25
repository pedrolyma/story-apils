package com.storyapils.repository.produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.storyapils.model.Produto;
import com.storyapils.model.Produto_;
import com.storyapils.repository.filter.ProdutoFilter;

public class ProdutoRepositoryImpl implements ProdutoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Produto> filtrar(ProdutoFilter produtoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteria = builder.createQuery(Produto.class);
        
        Root<Produto> root = criteria.from(Produto.class);
        
        Predicate[] predicates = criarRestricoes(produtoFilter, builder, root);
        criteria.where(predicates);
        
        TypedQuery<Produto> query = manager.createQuery(criteria);
        adicionarRestricoesDePagina(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(produtoFilter));
	}

	private Predicate[] criarRestricoes(ProdutoFilter produtoFilter, CriteriaBuilder builder, Root<Produto> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isEmpty(produtoFilter.getDescricaoProduto())) {
        	predicates.add(builder.like(builder.lower(root.get(Produto_.DESCRICAO_PRODUTO)),"%" + produtoFilter.getDescricaoProduto().toLowerCase()+"%"));
//        	predicates.add(builder.like(builder.lower(root.get("descricao")),"%" + secaoFilter.getDescricao().toLowerCase()+"%"));
// linha 54 usando jpamodelgen linha 55 usando string normal        	
        }
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePagina(TypedQuery<Produto> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;
        query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
	}

	private Long total(ProdutoFilter produtoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Produto> root = criteria.from(Produto.class);
        Predicate[] predicates = criarRestricoes(produtoFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
