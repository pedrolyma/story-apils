package com.storyapils.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Fornecedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
//	@NotBlank
	private String razaosocial;
	private String nomefantasia;
	private String cnpjFornecedor;
	private String inscricaoFornecedor;
	private String telefoneFornecedor;
	private String whatsAppFornecedor;
	private String emailFornecedor;
	private String cepFornecedor;
	private String logradouroFornecedor;
	private String bairroFornecedor;
	private String cidadeFornecedor;
	private String estadoFornecedor;
	private String siteFornecedor;
	private String bancoFornecedor;
	private String agenciaFornecedor;
	private String contaFornecedor;
	private String titularFornecedor;
	private Boolean statusFornecedor;

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getRazaosocial() {
		return razaosocial;
	}
	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}
	public String getNomefantasia() {
		return nomefantasia;
	}
	public void setNomefantasia(String nomefantasia) {
		this.nomefantasia = nomefantasia;
	}
	public String getCnpjFornecedor() {
		return cnpjFornecedor;
	}
	public void setCnpjFornecedor(String cnpjFornecedor) {
		this.cnpjFornecedor = cnpjFornecedor;
	}
	public String getInscricaoFornecedor() {
		return inscricaoFornecedor;
	}
	public void setInscricaoFornecedor(String inscricaoFornecedor) {
		this.inscricaoFornecedor = inscricaoFornecedor;
	}
	public String getTelefoneFornecedor() {
		return telefoneFornecedor;
	}
	public void setTelefoneFornecedor(String telefoneFornecedor) {
		this.telefoneFornecedor = telefoneFornecedor;
	}
	public String getWhatsAppFornecedor() {
		return whatsAppFornecedor;
	}
	public void setWhatsAppFornecedor(String whatsAppFornecedor) {
		this.whatsAppFornecedor = whatsAppFornecedor;
	}
	public String getEmailFornecedor() {
		return emailFornecedor;
	}
	public void setEmailFornecedor(String emailFornecedor) {
		this.emailFornecedor = emailFornecedor;
	}
	public String getCepFornecedor() {
		return cepFornecedor;
	}
	public void setCepFornecedor(String cepFornecedor) {
		this.cepFornecedor = cepFornecedor;
	}
	public String getLogradouroFornecedor() {
		return logradouroFornecedor;
	}
	public void setLogradouroFornecedor(String logradouroFornecedor) {
		this.logradouroFornecedor = logradouroFornecedor;
	}
	public String getBairroFornecedor() {
		return bairroFornecedor;
	}
	public void setBairroFornecedor(String bairroFornecedor) {
		this.bairroFornecedor = bairroFornecedor;
	}
	public String getCidadeFornecedor() {
		return cidadeFornecedor;
	}
	public void setCidadeFornecedor(String cidadeFornecedor) {
		this.cidadeFornecedor = cidadeFornecedor;
	}
	public String getEstadoFornecedor() {
		return estadoFornecedor;
	}
	public void setEstadoFornecedor(String estadoFornecedor) {
		this.estadoFornecedor = estadoFornecedor;
	}
	public String getSiteFornecedor() {
		return siteFornecedor;
	}
	public void setSiteFornecedor(String siteFornecedor) {
		this.siteFornecedor = siteFornecedor;
	}
	public String getBancoFornecedor() {
		return bancoFornecedor;
	}
	public void setBancoFornecedor(String bancoFornecedor) {
		this.bancoFornecedor = bancoFornecedor;
	}
	public String getAgenciaFornecedor() {
		return agenciaFornecedor;
	}
	public void setAgenciaFornecedor(String agenciaFornecedor) {
		this.agenciaFornecedor = agenciaFornecedor;
	}
	public String getContaFornecedor() {
		return contaFornecedor;
	}
	public void setContaFornecedor(String contaFornecedor) {
		this.contaFornecedor = contaFornecedor;
	}
	public String getTitularFornecedor() {
		return titularFornecedor;
	}
	public void setTitularFornecedor(String titularFornecedor) {
		this.titularFornecedor = titularFornecedor;
	}
	
	public Boolean getStatusFornecedor() {
		return statusFornecedor;
	}
	public void setStatusFornecedor(Boolean statusFornecedor) {
		this.statusFornecedor = statusFornecedor;
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
		Fornecedor other = (Fornecedor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
}
