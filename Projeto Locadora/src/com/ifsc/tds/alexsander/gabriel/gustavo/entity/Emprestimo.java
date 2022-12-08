package com.ifsc.tds.alexsander.gabriel.gustavo.entity;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class Emprestimo {
	
	private Long id;
	
	private Date data_emprestimo;
	
	private Date data_Entrega;
	
	private int valorEmprestimo=0;
	
	private Filme filme;
	
	private Cliente cliente;

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	private String obs;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData_emprestimo() {
		return data_emprestimo;
	}

	public void setData_emprestimo(Date data_emprestimo) {
		this.data_emprestimo = data_emprestimo;
	}

	public Date getData_Entrega() {
		return data_Entrega;
	}

	public void setData_Entrega(Date data_Entrega) {
		this.data_Entrega = data_Entrega;
	}

	public int getValorEmprestimo() {
		return valorEmprestimo;
	}

	public void setValorEmprestimo(int valorEmprestimo) {
		this.valorEmprestimo = valorEmprestimo;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String getDataEntregaFormatada () {
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return this.data_Entrega.toLocalDate().format(dataFormatada).toString();
	}
	
	public String getDataEmprestimoFormatada () {
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return this.data_emprestimo.toLocalDate().format(dataFormatada).toString();
	}
}
