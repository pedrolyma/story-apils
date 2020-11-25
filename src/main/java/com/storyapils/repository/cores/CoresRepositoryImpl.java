package com.storyapils.repository.cores;

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

import com.storyapils.model.Cores;
import com.storyapils.model.Cores_;
import com.storyapils.repository.filter.CoresFilter;

public class CoresRepositoryImpl implements CoresRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Cores> filtrar(CoresFilter coresFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Cores> criteria = builder.createQuery(Cores.class);
        
        Root<Cores> root = criteria.from(Cores.class);
        
        Predicate[] predicates = criarRestricoes(coresFilter, builder, root);
        criteria.where(predicates);
        
        TypedQuery<Cores> query = manager.createQuery(criteria);
        adicionarRestricoesDePagina(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(coresFilter));
	}

	private void adicionarRestricoesDePagina(TypedQuery<Cores> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;
        query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
	}

	private Predicate[] criarRestricoes(CoresFilter coresFilter, CriteriaBuilder builder, Root<Cores> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isEmpty(coresFilter.getDescricao())) {
        	predicates.add(builder.like(builder.lower(root.get(Cores_.descricao)),"%" + coresFilter.getDescricao().toLowerCase()+"%"));
//        	predicates.add(builder.like(builder.lower(root.get("descricao")),"%" + secaoFilter.getDescricao().toLowerCase()+"%"));
// linha 54 usando jpamodelgen linha 55 usando string normal        	
        }
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private Long total(CoresFilter coresFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Cores> root = criteria.from(Cores.class);
        Predicate[] predicates = criarRestricoes(coresFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
