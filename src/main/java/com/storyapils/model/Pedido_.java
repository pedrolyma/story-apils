package com.storyapils.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pedido.class)
public abstract class Pedido_ {

	public static volatile SingularAttribute<Pedido, LocalDate> previsaoEntrega;
	public static volatile SingularAttribute<Pedido, Long> codigo;
	public static volatile SingularAttribute<Pedido, BigDecimal> qtdePedido;
	public static volatile SingularAttribute<Pedido, String> formaPagto;
	public static volatile SingularAttribute<Pedido, Produto> produto;
	public static volatile SingularAttribute<Pedido, BigDecimal> valorDesconto;
	public static volatile SingularAttribute<Pedido, BigDecimal> valorFrete;
	public static volatile SingularAttribute<Pedido, LocalDate> dataPedido;
	public static volatile SingularAttribute<Pedido, Fornecedor> fornecedor;
	public static volatile SingularAttribute<Pedido, BigDecimal> valorUnitario;

	public static final String PREVISAO_ENTREGA = "previsaoEntrega";
	public static final String CODIGO = "codigo";
	public static final String QTDE_PEDIDO = "qtdePedido";
	public static final String FORMA_PAGTO = "formaPagto";
	public static final String PRODUTO = "produto";
	public static final String VALOR_DESCONTO = "valorDesconto";
	public static final String VALOR_FRETE = "valorFrete";
	public static final String DATA_PEDIDO = "dataPedido";
	public static final String FORNECEDOR = "fornecedor";
	public static final String VALOR_UNITARIO = "valorUnitario";

}

