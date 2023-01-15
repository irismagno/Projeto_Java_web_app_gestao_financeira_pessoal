
package br.com.fiap.fintech.bean;

import java.util.Calendar;


public class Objetivo {
	
	// Atributos
	
	private double codigoObjetivo;
	private String nomeObjetivo;
	private String descricao;
	private double valor;
	private Calendar dataRealizacao; // format dd/MM/yyyy
	private Usuario user;
	
	// Métodos construtores
	
	public Objetivo () {
		
	}
	
	public Objetivo (double codigoObjetivo, Usuario user, String nomeObjetivo, double valor, String descricao, Calendar data) {
		this.codigoObjetivo = codigoObjetivo;
		this.nomeObjetivo = nomeObjetivo;
		this.descricao = descricao;
		this.valor = valor;
		this.dataRealizacao = data;
		this.user = user;
	}
	
		
	// Getters and setters

	public String getNomeObjetivo() {
		return nomeObjetivo;
	}

	public void setNomeObjetivo(String nomeObjetivo) {
		this.nomeObjetivo = nomeObjetivo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Calendar getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Calendar dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public double getCodigoObjetivo() {
		return codigoObjetivo;
	}

	public void setCodigoObjetivo(double codigoObjetivo) {
		this.codigoObjetivo = codigoObjetivo;
	}
	
}