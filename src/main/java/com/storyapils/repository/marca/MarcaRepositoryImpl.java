package com.storyapils.repository.marca;

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

import com.storyapils.model.Marca;
import com.storyapils.model.Marca_;
import com.storyapils.repository.filter.MarcasFilter;

public class MarcaRepositoryImpl implements MarcaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Marca> filtrar(MarcasFilter marcasFilter, Pageable pageable) {

	        CriteriaBuilder builder = manager.getCriteriaBuilder();
	        CriteriaQuery<Marca> criteria = builder.createQuery(Marca.class);
	        
	        Root<Marca> root = criteria.from(Marca.class);
	        
	        Predicate[] predicates = criarRestricoes(marcasFilter, builder, root);
	        criteria.where(predicates);
	        
	        TypedQuery<Marca> query = manager.createQuery(criteria);
	        adicionarRestricoesDePagina(query, pageable);
			return new PageImpl<>(query.getResultList(), pageable, total(marcasFilter));
		}

		private void adicionarRestricoesDePagina(TypedQuery<Marca> query, Pageable pageable) {
	        int paginaAtual = pageable.getPageNumber();
	        int totalRegistroPorPagina = pageable.getPageSize();
	        int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;
	        query.setFirstResult(primeiroRegistroDaPagina);
			query.setMaxResults(totalRegistroPorPagina);
		}

		private Predicate[] criarRestricoes(MarcasFilter marcasFilter, CriteriaBuilder builder, Root<Marca> root) {
	        List<Predicate> predicates = new ArrayList<>();
	        if (!StringUtils.isEmpty(marcasFilter.getDescricao())) {
	        	predicates.add(builder.like(builder.lower(root.get(Marca_.descricao)),"%" + marcasFilter.getDescricao().toLowerCase()+"%"));
//	        	predicates.add(builder.like(builder.lower(root.get("descricao")),"%" + secaoFilter.getDescricao().toLowerCase()+"%"));
	// linha 54 usando jpamodelgen linha 55 usando string normal        	
	        }
			return predicates.toArray(new Predicate[predicates.size()]);
		}
		
		private Long total(MarcasFilter marcasFilter) {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
	        Root<Marca> root = criteria.from(Marca.class);
	        Predicate[] predicates = criarRestricoes(marcasFilter, builder, root);
	        criteria.where(predicates);
	        criteria.select(builder.count(root));
			return manager.createQuery(criteria).getSingleResult();
		}

}
