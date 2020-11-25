package com.storyapils.repository.modelo;

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

import com.storyapils.model.Modelo;
import com.storyapils.model.Modelo_;
import com.storyapils.model.Secao;
import com.storyapils.model.Secao_;
import com.storyapils.repository.filter.ModeloFilter;
import com.storyapils.repository.filter.SecaoFilter;

public class ModeloRepositoryImpl implements ModeloRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Modelo> filtrar(ModeloFilter modeloFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Modelo> criteria = builder.createQuery(Modelo.class);
        
        Root<Modelo> root = criteria.from(Modelo.class);
        
        Predicate[] predicates = criarRestricoes(modeloFilter, builder, root);
        criteria.where(predicates);
        
        TypedQuery<Modelo> query = manager.createQuery(criteria);
        adicionarRestricoesDePagina(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(modeloFilter));
	}

	private void adicionarRestricoesDePagina(TypedQuery<Modelo> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;
        query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
	}

	private Predicate[] criarRestricoes(ModeloFilter modeloFilter, CriteriaBuilder builder, Root<Modelo> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isEmpty(modeloFilter.getDescricao())) {
        	predicates.add(builder.like(builder.lower(root.get(Modelo_.descricao)),"%" + modeloFilter.getDescricao().toLowerCase()+"%"));
//        	predicates.add(builder.like(builder.lower(root.get("descricao")),"%" + secaoFilter.getDescricao().toLowerCase()+"%"));
// linha 54 usando jpamodelgen linha 55 usando string normal        	
        }
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Long total(ModeloFilter modeloFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Modelo> root = criteria.from(Modelo.class);
        Predicate[] predicates = criarRestricoes(modeloFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
	
}
