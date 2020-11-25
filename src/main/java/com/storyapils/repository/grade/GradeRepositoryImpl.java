package com.storyapils.repository.grade;

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

import com.storyapils.model.Grade;
import com.storyapils.model.Grade_;
import com.storyapils.repository.filter.GradeFilter;

public class GradeRepositoryImpl implements GradeRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Grade> filtrar(GradeFilter gradeFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Grade> criteria = builder.createQuery(Grade.class);
        
        Root<Grade> root = criteria.from(Grade.class);
        
        Predicate[] predicates = criarRestricoes(gradeFilter, builder, root);
        criteria.where(predicates);
        
        TypedQuery<Grade> query = manager.createQuery(criteria);
        adicionarRestricoesDePagina(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(gradeFilter));
	}
	
	private void adicionarRestricoesDePagina(TypedQuery<Grade> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;
        query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
	}

	private Predicate[] criarRestricoes(GradeFilter gradeFilter, CriteriaBuilder builder, Root<Grade> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isEmpty(gradeFilter.getDescricao())) {
        	predicates.add(builder.like(builder.lower(root.get(Grade_.INTERVALO)),"%" + gradeFilter.getDescricao().toLowerCase()+"%"));
//        	predicates.add(builder.like(builder.lower(root.get("descricao")),"%" + secaoFilter.getDescricao().toLowerCase()+"%"));
// linha 54 usando jpamodelgen linha 55 usando string normal        	
        }
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private Long total(GradeFilter coresFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Grade> root = criteria.from(Grade.class);
        Predicate[] predicates = criarRestricoes(coresFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
