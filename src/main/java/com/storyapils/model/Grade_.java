package com.storyapils.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Grade.class)
public abstract class Grade_ {

	public static volatile SingularAttribute<Grade, Long> codigo;
	public static volatile SingularAttribute<Grade, String> Intervalo;
	public static volatile SingularAttribute<Grade, String> iniciograde;
	public static volatile SingularAttribute<Grade, String> fimgrade;
	public static volatile SingularAttribute<Grade, Integer> qtdeitensgrade;

	public static final String CODIGO = "codigo";
	public static final String INTERVALO = "Intervalo";
	public static final String INICIOGRADE = "iniciograde";
	public static final String FIMGRADE = "fimgrade";
	public static final String QTDEITENSGRADE = "qtdeitensgrade";

}

