package com.storyapils.repository.fornecedor;

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

import com.storyapils.model.Fornecedor;
import com.storyapils.model.Fornecedor_;
import com.storyapils.repository.filter.FornecedorFilter;

public class FornecedorRepositoryImpl implements FornecedorRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Fornecedor> filtrar(FornecedorFilter fornecedorFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Fornecedor> criteria = builder.createQuery(Fornecedor.class);
        
        Root<Fornecedor> root = criteria.from(Fornecedor.class);
        
        Predicate[] predicates = criarRestricoes(fornecedorFilter, builder, root);
        criteria.where(predicates);
        
        TypedQuery<Fornecedor> query = manager.createQuery(criteria);
        adicionarRestricoesDePagina(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(fornecedorFilter));
	}

	private Long total(FornecedorFilter fornecedorFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Fornecedor> root = criteria.from(Fornecedor.class);
        Predicate[] predicates = criarRestricoes(fornecedorFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePagina(TypedQuery<Fornecedor> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;
        query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
	}

	private Predicate[] criarRestricoes(FornecedorFilter fornecedorFilter, CriteriaBuilder builder,
			Root<Fornecedor> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isEmpty(fornecedorFilter.getRazaosocial())) {
        	predicates.add(builder.like(builder.lower(root.get(Fornecedor_.razaosocial)),"%" + fornecedorFilter.getRazaosocial().toLowerCase()+"%"));
//        	predicates.add(builder.like(builder.lower(root.get("descricao")),"%" + secaoFilter.getDescricao().toLowerCase()+"%"));
// linha 54 usando jpamodelgen linha 55 usando string normal        	
        }
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
}
