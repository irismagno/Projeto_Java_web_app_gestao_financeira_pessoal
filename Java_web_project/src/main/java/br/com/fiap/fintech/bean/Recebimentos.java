package br.com.fiap.fintech.bean;

import java.util.Calendar;

public class Recebimentos {

	private int codigoRecebimento;
	private Usuario user;
	private double valor;
	private Calendar data;
	private String descricao;
	private InstituicaoFinanceira instituicao;
	private ContaCorrente conta;
	
	public Recebimentos() {
		
	}
	
	public Recebimentos(int codigoRecebimento, Usuario user, double valor, Calendar data, String descricao, InstituicaoFinanceira instituicao, ContaCorrente conta) {
		this.setCodigoRecebimento(codigoRecebimento);
		this.user = user;
		this.valor = valor;
		this.data = data;
		this.descricao = descricao;
		this.instituicao = instituicao;
		this.conta = conta;
	}

	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
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

	public int getCodigoRecebimento() {
		return codigoRecebimento;
	}

	public void setCodigoRecebimento(int codigoRecebimento) {
		this.codigoRecebimento = codigoRecebimento;
	}
	
	
}

