package com.storyapils.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Marca.class)
public abstract class Marca_ {

	public static volatile SingularAttribute<Marca, Long> codigo;
	public static volatile SingularAttribute<Marca, Boolean> statusmarca;
	public static volatile SingularAttribute<Marca, String> fotomarca;
	public static volatile SingularAttribute<Marca, String> descricao;

	public static final String CODIGO = "codigo";
	public static final String STATUSMARCA = "statusmarca";
	public static final String FOTOMARCA = "fotomarca";
	public static final String DESCRICAO = "descricao";

}

