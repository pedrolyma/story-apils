package com.storyapils.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Modelo.class)
public abstract class Modelo_ {

	public static volatile SingularAttribute<Modelo, Long> codigo;
	public static volatile SingularAttribute<Modelo, Secao> secao;
	public static volatile SingularAttribute<Modelo, Boolean> statusmodelo;
	public static volatile SingularAttribute<Modelo, String> descricao;

	public static final String CODIGO = "codigo";
	public static final String SECAO = "secao";
	public static final String STATUSMODELO = "statusmodelo";
	public static final String DESCRICAO = "descricao";

}

