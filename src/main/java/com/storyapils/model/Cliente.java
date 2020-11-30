package com.storyapils.model;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class Cliente {
	
	private Long codigo;
	private String nomeCliente;
	private String cpfCliente;
	private String rgCliente;
	private LocalDate dataNascimento;
	private EstadoCivil estadoCivil;
	private Sexo sexo;
	private String profissao;
	private String localTrabalha;
	private LocalDate dataCadastro;
	private String natural;
	private String nomeMae;
	private String nomePai;


}
