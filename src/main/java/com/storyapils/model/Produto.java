package com.storyapils.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String descricaoProduto;
	private String referenciaProduto;
	private String codigoBarraProduto;
	@NotNull
	@Enumerated(EnumType.STRING)
	private GeneroProduto generoProduto;
	private String ncmshProduto;
	private Boolean statusProduto;
	private LocalDate DataCadastro;
	@ManyToOne
	@JoinColumn(name = "id_secao")
	private Secao secao;
	@ManyToOne
	@JoinColumn(name = "id_modelo")
	private Modelo modelo;
	@ManyToOne
	@JoinColumn(name = "id_cor")
	private Cores cores;
	@ManyToOne
	@JoinColumn(name = "id_marca")
	private Marca marca;
	@ManyToOne
	@JoinColumn(name = "id_grade")
	private Grade grade;
	@ManyToOne
	@JoinColumn(name = "id_unidade")
	private Unidade unidade;
	@ManyToOne
	@JoinColumn(name = "id_localizacao")
	private Localizacao localizacao;
	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	private Fornecedor fornecedor;
	
	private String informacoes;
	private String fotoProduto1;
	private String fotoProduto2;
	private String fotoProduto3;
	private Long codigo_produtof;
	
	private BigDecimal saldoAtual;
	private BigDecimal saldoMinimo;
	private BigDecimal valorCusto;
	private BigDecimal valorImposto;
	private BigDecimal valorFrete;
	private BigDecimal valorDesconto;
	private BigDecimal margemGanho;
	private BigDecimal vendaVista;
	private BigDecimal vendaPrazo;
	private BigDecimal vendaPromocao;
	private Long codigo_produto;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDescricaoProduto() {
		return descricaoProduto;
	}
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
	public String getReferenciaProduto() {
		return referenciaProduto;
	}
	public void setReferenciaProduto(String referenciaProduto) {
		this.referenciaProduto = referenciaProduto;
	}
	public String getCodigoBarraProduto() {
		return codigoBarraProduto;
	}
	public void setCodigoBarraProduto(String codigoBarraProduto) {
		this.codigoBarraProduto = codigoBarraProduto;
	}
	public String getNcmshProduto() {
		return ncmshProduto;
	}
	public void setNcmshProduto(String ncmshProduto) {
		this.ncmshProduto = ncmshProduto;
	}
	public Boolean getStatusProduto() {
		return statusProduto;
	}
	public void setStatusProduto(Boolean statusProduto) {
		this.statusProduto = statusProduto;
	}
	public LocalDate getDataCadastro() {
		return DataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		DataCadastro = dataCadastro;
	}

	public String getInformacoes() {
		return informacoes;
	}
	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}
	public String getFotoProduto1() {
		return fotoProduto1;
	}
	public void setFotoProduto1(String fotoProduto1) {
		this.fotoProduto1 = fotoProduto1;
	}
	public String getFotoProduto2() {
		return fotoProduto2;
	}
	public void setFotoProduto2(String fotoProduto2) {
		this.fotoProduto2 = fotoProduto2;
	}
	public String getFotoProduto3() {
		return fotoProduto3;
	}
	public void setFotoProduto3(String fotoProduto3) {
		this.fotoProduto3 = fotoProduto3;
	}
	public Long getCodigo_produtof() {
		return codigo_produtof;
	}
	public void setCodigo_produtof(Long codigo_produtof) {
		this.codigo_produtof = codigo_produtof;
	}
	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}
	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}
	public BigDecimal getSaldoMinimo() {
		return saldoMinimo;
	}
	public void setSaldoMinimo(BigDecimal saldoMinimo) {
		this.saldoMinimo = saldoMinimo;
	}
	public BigDecimal getValorCusto() {
		return valorCusto;
	}
	public void setValorCusto(BigDecimal valorCusto) {
		this.valorCusto = valorCusto;
	}
	public BigDecimal getValorImposto() {
		return valorImposto;
	}
	public void setValorImposto(BigDecimal valorImposto) {
		this.valorImposto = valorImposto;
	}
	public BigDecimal getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}
	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	public BigDecimal getMargemGanho() {
		return margemGanho;
	}
	public void setMargemGanho(BigDecimal margemGanho) {
		this.margemGanho = margemGanho;
	}
	public BigDecimal getVendaVista() {
		return vendaVista;
	}
	public void setVendaVista(BigDecimal vendaVista) {
		this.vendaVista = vendaVista;
	}
	public BigDecimal getVendaPrazo() {
		return vendaPrazo;
	}
	public void setVendaPrazo(BigDecimal vendaPrazo) {
		this.vendaPrazo = vendaPrazo;
	}
	public BigDecimal getVendaPromocao() {
		return vendaPromocao;
	}
	public void setVendaPromocao(BigDecimal vendaPromocao) {
		this.vendaPromocao = vendaPromocao;
	}
	public Long getCodigo_produto() {
		return codigo_produto;
	}
	public void setCodigo_produto(Long codigo_produto) {
		this.codigo_produto = codigo_produto;
	}
	public GeneroProduto getGeneroProduto() {
		return generoProduto;
	}
	public void setGeneroProduto(GeneroProduto generoProduto) {
		this.generoProduto = generoProduto;
	}
	public Secao getSecao() {
		return secao;
	}
	public void setSecao(Secao secao) {
		this.secao = secao;
	}
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	public Cores getCores() {
		return cores;
	}
	public void setCores(Cores cores) {
		this.cores = cores;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public Localizacao getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	

}
