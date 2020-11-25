package com.storyapils.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Localizacao.class)
public abstract class Localizacao_ {

	public static volatile SingularAttribute<Localizacao, Long> codigo;
	public static volatile SingularAttribute<Localizacao, Boolean> statuslocaliza;
	public static volatile SingularAttribute<Localizacao, String> descricao;

	public static final String CODIGO = "codigo";
	public static final String STATUSLOCALIZA = "statuslocaliza";
	public static final String DESCRICAO = "descricao";

}

