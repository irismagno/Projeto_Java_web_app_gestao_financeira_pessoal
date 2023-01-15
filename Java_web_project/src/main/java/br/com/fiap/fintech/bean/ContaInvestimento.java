package br.com.fiap.fintech.bean;

import java.util.Calendar;

import br.com.fiap.fintech.exception.SaldoInsuficiente;

public class ContaInvestimento {
	
	// Atributos
	
	private int tipoDeInvestimento;
	private double taxaDeJuros;
	private int prazo;
	private Calendar dataAplicacao;
	private double rendimento;
	private double saldo;
	private String descricao;
	private int codigoInvest;
	private int numeroAgencia;
	private int numeroConta;
	private InstituicaoFinanceira instituicao;
	private Usuario user;
	
	// Métodos construtores

	public ContaInvestimento() {
		
	}
	
	public ContaInvestimento (int codigoInvest, Usuario user, InstituicaoFinanceira instituicao, int numeroAgencia, int numeroConta, int tipoDeInvestimento, double taxaDeJuros, int prazo, String descricao, Calendar dataAplicacao, double saldo) {
		this.numeroAgencia = numeroAgencia;
		this.numeroConta = numeroConta;
		this.instituicao = instituicao;
		this.user = user;
		this.codigoInvest = codigoInvest;
		this.tipoDeInvestimento = tipoDeInvestimento;
		this.taxaDeJuros = taxaDeJuros;
		this.prazo = prazo;
		this.descricao = descricao;
		this.dataAplicacao = dataAplicacao;
		this.saldo = saldo;
	}
	
	// Outros métodos
	
	public void aplicar (double valor) {
		this.saldo += valor;
	}
	
		
	public void resgatar (double valor) throws SaldoInsuficiente {		
		if (this.saldo >= valor) {
			this.saldo -= valor;
		} else {
			throw new SaldoInsuficiente();
		}
	
	}
		
	public double calcularRendimento () {
		this.rendimento = saldo * Math.pow((1 + this.taxaDeJuros/100), prazo);
		return rendimento;
	}

	// Getters and setters
	
	public int getTipoDeInvestimento() {
		return tipoDeInvestimento;
	}

	public void setTipoDeInvestimento(int tipoDeInvestimento) {
		this.tipoDeInvestimento = tipoDeInvestimento;
	}

	public double getTaxaDeJuros() {
		return taxaDeJuros;
	}

	public void setTaxaDeJuros(double taxaDeJuros) {
		this.taxaDeJuros = taxaDeJuros;
	}

	public int getPrazo() {
		return prazo;
	}

	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}

	public double getRendimento() {
		return rendimento;
	}

	public void setRendimento(double rendimento) {
		this.rendimento = rendimento;
	}

	public double getSaldo() {
		return saldo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getDataAplicacao() {
		return dataAplicacao;
	}

	public void setDataAplicacao(Calendar dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}

	public int getCodigoInvest() {
		return codigoInvest;
	}

	public void setCodigoInvest(int codigoInvest) {
		this.codigoInvest = codigoInvest;
	}

	public int getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(int numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	public InstituicaoFinanceira getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoFinanceira instituicao) {
		this.instituicao = instituicao;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
}