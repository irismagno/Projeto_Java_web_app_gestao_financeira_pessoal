package br.com.fiap.fintech.bean;

import br.com.fiap.fintech.exception.SaldoInsuficiente;

public class ContaCorrente {
	
	// Atributos
	
	private String descricao;
	private double saldo;
	private int numeroAgencia;
	private int numeroConta;
	private InstituicaoFinanceira instituicao;
	private Usuario user;
	
	// Métodos construtores
	
	public ContaCorrente () {
		
	}
	
	public ContaCorrente (int numeroAgencia, int numeroConta, InstituicaoFinanceira instituicao, Usuario user, String descricao, double saldo) {
		this.numeroAgencia = numeroAgencia;
		this.numeroConta = numeroConta;
		this.instituicao = instituicao;
		this.user = user;
		this.descricao = descricao;
		this.saldo = saldo;
	}
	
		
	// Outros métodos
	
	public void incluirRecebimento (double valor) {
		this.saldo += valor;
	}
	
	public void incluirGasto (double valor) {       // throws SaldoInsuficiente {
	//	if (this.saldo >= valor) {
			this.saldo -= valor;
	//	} else {
	//		throw new SaldoInsuficiente();
	//	}
		
	}
	
	// Getters and setters

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getSaldo() {
		return saldo;
	}

	public int getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(int numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public InstituicaoFinanceira getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoFinanceira instituicao) {
		this.instituicao = instituicao;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

}
