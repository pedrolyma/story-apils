package com.storyapils.repository.unidade;

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

import com.storyapils.model.Unidade;
import com.storyapils.model.Unidade_;
import com.storyapils.repository.filter.UnidadeFilter;

public class UnidadeRepositoryImpl implements UnidadeRepositoryQuery {

		@PersistenceContext
		private EntityManager manager;
		
		@Override
		public Page<Unidade> filtrar(UnidadeFilter unidadeFilter, Pageable pageable) {
	        CriteriaBuilder builder = manager.getCriteriaBuilder();
	        CriteriaQuery<Unidade> criteria = builder.createQuery(Unidade.class);
	        
	        Root<Unidade> root = criteria.from(Unidade.class);
	        
	        Predicate[] predicates = criarRestricoes(unidadeFilter, builder, root);
	        criteria.where(predicates);
	        
	        TypedQuery<Unidade> query = manager.createQuery(criteria);
	        adicionarRestricoesDePagina(query, pageable);
			return new PageImpl<>(query.getResultList(), pageable, total(unidadeFilter));
		}

		private void adicionarRestricoesDePagina(TypedQuery<Unidade> query, Pageable pageable) {
	        int paginaAtual = pageable.getPageNumber();
	        int totalRegistroPorPagina = pageable.getPageSize();
	        int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;
	        query.setFirstResult(primeiroRegistroDaPagina);
			query.setMaxResults(totalRegistroPorPagina);
		}

		private Predicate[] criarRestricoes(UnidadeFilter unidadeFilter, CriteriaBuilder builder, Root<Unidade> root) {
	        List<Predicate> predicates = new ArrayList<>();
	        if (!StringUtils.isEmpty(unidadeFilter.getDescricao())) {
	        	predicates.add(builder.like(builder.lower(root.get(Unidade_.descricao)),"%" + unidadeFilter.getDescricao().toLowerCase()+"%"));
//	        	predicates.add(builder.like(builder.lower(root.get("descricao")),"%" + secaoFilter.getDescricao().toLowerCase()+"%"));
	// linha 54 usando jpamodelgen linha 55 usando string normal        	
	        }
			return predicates.toArray(new Predicate[predicates.size()]);
		}
		
		private Long total(UnidadeFilter unidadeFilter) {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
	        Root<Unidade> root = criteria.from(Unidade.class);
	        Predicate[] predicates = criarRestricoes(unidadeFilter, builder, root);
	        criteria.where(predicates);
	        criteria.select(builder.count(root));
			return manager.createQuery(criteria).getSingleResult();
		}
}
