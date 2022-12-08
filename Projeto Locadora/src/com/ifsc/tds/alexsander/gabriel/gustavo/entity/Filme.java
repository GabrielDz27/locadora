package com.ifsc.tds.alexsander.gabriel.gustavo.entity;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class Filme {
	
	private Long id;
	
	private String nome;
	
	private Date ano;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getAno() {
		return ano;
	}

	public void setAno(Date ano) {
		this.ano = ano;
	}
	
	public String getAnoFormatado() {
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return this.ano.toLocalDate().format(dataFormatada).toString();
	}
	
	@Override
	public String toString() {
		return this.nome;
	}
}
