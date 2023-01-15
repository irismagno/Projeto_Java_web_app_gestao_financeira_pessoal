package br.com.fiap.fintech.bean;

public abstract class Conta {
	
	// Atributos
	
	private int numeroAgencia;
	private int numeroConta;
	private InstituicaoFinanceira instituicao;
	private Usuario user;
		
	// Métodos construtores
	
	public Conta () {
		
	}
	
	public Conta (int numeroAgencia, int numeroConta, InstituicaoFinanceira instituicao, Usuario user) {
		this.numeroAgencia = numeroAgencia;
		this.numeroConta = numeroAgencia;
		this.instituicao = instituicao;
		this.user = user;
	}
	
	// Getters and setters

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
