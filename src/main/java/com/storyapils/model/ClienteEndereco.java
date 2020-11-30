package com.storyapils.model;

import javax.persistence.Entity;

@Entity
public class ClienteEndereco {
	
	private Long codigo;
	private String cep;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String estado;
	private String numero;
	private String complemento;
	private String pontoReferencia;

}
