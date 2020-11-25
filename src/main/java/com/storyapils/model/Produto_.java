package com.storyapils.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produto.class)
public abstract class Produto_ {

	public static volatile SingularAttribute<Produto, String> codigoBarraProduto;
	public static volatile SingularAttribute<Produto, BigDecimal> valorDesconto;
	public static volatile SingularAttribute<Produto, String> referenciaProduto;
	public static volatile SingularAttribute<Produto, String> ncmshProduto;
	public static volatile SingularAttribute<Produto, LocalDate> DataCadastro;
	public static volatile SingularAttribute<Produto, Unidade> unidade;
	public static volatile SingularAttribute<Produto, Long> codigo_produtof;
	public static volatile SingularAttribute<Produto, String> descricaoProduto;
	public static volatile SingularAttribute<Produto, Long> codigo_produto;
	public static volatile SingularAttribute<Produto, Marca> marca;
	public static volatile SingularAttribute<Produto, BigDecimal> valorCusto;
	public static volatile SingularAttribute<Produto, Cores> cores;
	public static volatile SingularAttribute<Produto, String> fotoProduto3;
	public static volatile SingularAttribute<Produto, BigDecimal> valorFrete;
	public static volatile SingularAttribute<Produto, String> fotoProduto2;
	public static volatile SingularAttribute<Produto, String> fotoProduto1;
	public static volatile SingularAttribute<Produto, BigDecimal> margemGanho;
	public static volatile SingularAttribute<Produto, Boolean> statusProduto;
	public static volatile SingularAttribute<Produto, BigDecimal> saldoMinimo;
	public static volatile SingularAttribute<Produto, Long> codigo;
	public static volatile SingularAttribute<Produto, Localizacao> localizacao;
	public static volatile SingularAttribute<Produto, GeneroProduto> generoProduto;
	public static volatile SingularAttribute<Produto, BigDecimal> saldoAtual;
	public static volatile SingularAttribute<Produto, BigDecimal> vendaVista;
	public static volatile SingularAttribute<Produto, Modelo> modelo;
	public static volatile SingularAttribute<Produto, BigDecimal> vendaPrazo;
	public static volatile SingularAttribute<Produto, Grade> grade;
	public static volatile SingularAttribute<Produto, BigDecimal> valorImposto;
	public static volatile SingularAttribute<Produto, Fornecedor> fornecedor;
	public static volatile SingularAttribute<Produto, Secao> secao;
	public static volatile SingularAttribute<Produto, String> informacoes;
	public static volatile SingularAttribute<Produto, BigDecimal> vendaPromocao;

	public static final String CODIGO_BARRA_PRODUTO = "codigoBarraProduto";
	public static final String VALOR_DESCONTO = "valorDesconto";
	public static final String REFERENCIA_PRODUTO = "referenciaProduto";
	public static final String NCMSH_PRODUTO = "ncmshProduto";
	public static final String DATA_CADASTRO = "DataCadastro";
	public static final String UNIDADE = "unidade";
	public static final String CODIGO_PRODUTOF = "codigo_produtof";
	public static final String DESCRICAO_PRODUTO = "descricaoProduto";
	public static final String CODIGO_PRODUTO = "codigo_produto";
	public static final String MARCA = "marca";
	public static final String VALOR_CUSTO = "valorCusto";
	public static final String CORES = "cores";
	public static final String FOTO_PRODUTO3 = "fotoProduto3";
	public static final String VALOR_FRETE = "valorFrete";
	public static final String FOTO_PRODUTO2 = "fotoProduto2";
	public static final String FOTO_PRODUTO1 = "fotoProduto1";
	public static final String MARGEM_GANHO = "margemGanho";
	public static final String STATUS_PRODUTO = "statusProduto";
	public static final String SALDO_MINIMO = "saldoMinimo";
	public static final String CODIGO = "codigo";
	public static final String LOCALIZACAO = "localizacao";
	public static final String GENERO_PRODUTO = "generoProduto";
	public static final String SALDO_ATUAL = "saldoAtual";
	public static final String VENDA_VISTA = "vendaVista";
	public static final String MODELO = "modelo";
	public static final String VENDA_PRAZO = "vendaPrazo";
	public static final String GRADE = "grade";
	public static final String VALOR_IMPOSTO = "valorImposto";
	public static final String FORNECEDOR = "fornecedor";
	public static final String SECAO = "secao";
	public static final String INFORMACOES = "informacoes";
	public static final String VENDA_PROMOCAO = "vendaPromocao";

}

