package com.storyapils.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String iniciograde;
	private String fimgrade;
	private String Intervalo;
	private Integer qtdeitensgrade;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getIniciograde() {
		return iniciograde;
	}
	public void setIniciograde(String iniciograde) {
		this.iniciograde = iniciograde;
	}
	public String getFimgrade() {
		return fimgrade;
	}
	public void setFimgrade(String fimgrade) {
		this.fimgrade = fimgrade;
	}
	public String getIntervalo() {
		return Intervalo;
	}
	public void setIntervalo(String intervalo) {
		Intervalo = intervalo;
	}
	public Integer getQtdeitensgrade() {
		return qtdeitensgrade;
	}
	public void setQtdeitensgrade(Integer qtdeitensgrade) {
		this.qtdeitensgrade = qtdeitensgrade;
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
		Grade other = (Grade) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
