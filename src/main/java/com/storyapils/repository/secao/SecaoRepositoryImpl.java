package com.storyapils.repository.secao;

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

import com.storyapils.model.Secao;
import com.storyapils.model.Secao_;
import com.storyapils.repository.filter.SecaoFilter;

public class SecaoRepositoryImpl implements SecaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Secao> filtrar(SecaoFilter secaoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Secao> criteria = builder.createQuery(Secao.class);
        
        Root<Secao> root = criteria.from(Secao.class);
        
        Predicate[] predicates = criarRestricoes(secaoFilter, builder, root);
        criteria.where(predicates);
        
        TypedQuery<Secao> query = manager.createQuery(criteria);
        adicionarRestricoesDePagina(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(secaoFilter));
	}

	private void adicionarRestricoesDePagina(TypedQuery<Secao> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;
        query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
	}

	private Predicate[] criarRestricoes(SecaoFilter secaoFilter, CriteriaBuilder builder, Root<Secao> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isEmpty(secaoFilter.getDescricao())) {
        	predicates.add(builder.like(builder.lower(root.get(Secao_.descricao)),"%" + secaoFilter.getDescricao().toLowerCase()+"%"));
//        	predicates.add(builder.like(builder.lower(root.get("descricao")),"%" + secaoFilter.getDescricao().toLowerCase()+"%"));
// linha 54 usando jpamodelgen linha 55 usando string normal        	
        }
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private Long total(SecaoFilter secaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Secao> root = criteria.from(Secao.class);
        Predicate[] predicates = criarRestricoes(secaoFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
