package br.com.fiap.fintech.bean;

public class InstituicaoFinanceira {
	
	// Atributos
	
	private String nomeInstituicao;
	private int numeroInstituicao;


	// Métodos construtores
	
	public InstituicaoFinanceira( ) {
		
	}
	
	public InstituicaoFinanceira (String nomeInstituicao, int numeroInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
		this.numeroInstituicao = numeroInstituicao;
	}
	
	//Getters and setters

	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}


		public int getNumeroInstituicao() {
		return numeroInstituicao;
	}

	public void setNumeroInstituicao(int numeroInstituicao) {
		this.numeroInstituicao = numeroInstituicao;
	}


}