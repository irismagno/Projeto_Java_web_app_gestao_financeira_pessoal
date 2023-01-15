package br.com.fiap.fintech.bean;

import java.util.Calendar;

public class Gastos {

	private int codigoGasto;
	private Usuario user;
	private CategoriaGasto categoria;
	private double valor;
	private Calendar data;
	private String descricao;
	private InstituicaoFinanceira instituicao;
	private ContaCorrente conta;
	
	public Gastos() {
		
	}
	
	public Gastos(int codigoGasto, Usuario user, CategoriaGasto categoria, double valor, Calendar data, String descricao, InstituicaoFinanceira instituicao, ContaCorrente conta) {
		this.codigoGasto = codigoGasto;
		this.user = user;
		this.categoria = categoria;
		this.valor = valor;
		this.data = data;
		this.descricao = descricao;
		this.instituicao = instituicao;
		this.conta = conta;
	}

	public int getCodigoGasto() {
		return codigoGasto;
	}

	public void setCodigoGasto(int codigoGasto) {
		this.codigoGasto = codigoGasto;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public CategoriaGasto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaGasto categoria) {
		this.categoria = categoria;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public InstituicaoFinanceira getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoFinanceira instituicao) {
		this.instituicao = instituicao;
	}

	public ContaCorrente getConta() {
		return conta;
	}

	public void setConta(ContaCorrente conta) {
		this.conta = conta;
	}
	
	
	
	
}
