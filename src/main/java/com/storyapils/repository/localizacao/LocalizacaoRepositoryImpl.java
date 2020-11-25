package com.storyapils.repository.localizacao;

import java.util.ArrayList;
import java.util.List;

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

import com.storyapils.model.Localizacao;
import com.storyapils.model.Localizacao_;
import com.storyapils.repository.filter.LocalizacaoFilter;

public class LocalizacaoRepositoryImpl implements LocalizacaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Localizacao> filtrar(LocalizacaoFilter localizacaoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Localizacao> criteria = builder.createQuery(Localizacao.class);
        
        Root<Localizacao> root = criteria.from(Localizacao.class);
        
        Predicate[] predicates = criarRestricoes(localizacaoFilter, builder, root);
        criteria.where(predicates);
        
        TypedQuery<Localizacao> query = manager.createQuery(criteria);
        adicionarRestricoesDePagina(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(localizacaoFilter));
	}

	private void adicionarRestricoesDePagina(TypedQuery<Localizacao> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;
        query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
		
	}

	private Predicate[] criarRestricoes(LocalizacaoFilter localizacaoFilter, CriteriaBuilder builder,
			Root<Localizacao> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isEmpty(localizacaoFilter.getDescricao())) {
        	predicates.add(builder.like(builder.lower(root.get(Localizacao_.descricao)),"%" + localizacaoFilter.getDescricao().toLowerCase()+"%"));
//        	predicates.add(builder.like(builder.lower(root.get("descricao")),"%" + secaoFilter.getDescricao().toLowerCase()+"%"));
// linha 54 usando jpamodelgen linha 55 usando string normal        	
        }
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private Long total(LocalizacaoFilter localizacaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Localizacao> root = criteria.from(Localizacao.class);
        Predicate[] predicates = criarRestricoes(localizacaoFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
