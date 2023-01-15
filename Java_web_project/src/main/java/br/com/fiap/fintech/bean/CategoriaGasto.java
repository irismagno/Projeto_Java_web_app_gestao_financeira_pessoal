package br.com.fiap.fintech.bean;

public class CategoriaGasto {
	
	// Atributo
	
	private int codigoCategoria;	
	private String categoria;


	// Métodos construtores

	public CategoriaGasto () {
		
	}
	
	public CategoriaGasto (int codigoCategoria, String categoria) {
		this.codigoCategoria = codigoCategoria;
		this.categoria = categoria;
	}
	
	// Getters and setters

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public int getCodigoCategoria() {
		return codigoCategoria;
	}
	
	public void setCodigoCategoria(int codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}
}