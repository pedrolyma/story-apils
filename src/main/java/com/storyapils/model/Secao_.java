package com.storyapils.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Secao.class)
public abstract class Secao_ {

	public static volatile SingularAttribute<Secao, Long> codigo;
	public static volatile SingularAttribute<Secao, Boolean> statussecao;
	public static volatile SingularAttribute<Secao, String> descricao;

	public static final String CODIGO = "codigo";
	public static final String STATUSSECAO = "statussecao";
	public static final String DESCRICAO = "descricao";

}

