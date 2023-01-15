package br.com.fiap.fintech.bean;

import java.util.Calendar;

import br.com.fiap.store.util.CriptografiaUtils;

public class Usuario {
	
	// Atributos
	
	private int id;
	private String nome;
	private String sobrenome;
	private int genero;
	private Calendar dataNascimento;
	private String email;
	private String senha;
	
	// M�todos construtores
	
	public Usuario () {
		
	}
	
	public Usuario (int id, String nome, String sobrenome, int genero, Calendar dataNascimento, String email, String senha) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.genero = genero;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.senha = senha;
	}
	
	// M�todo para retornar o print dos dados do usu�rio (fins did�ticos)
	
	public void getDadosUsuario () {
		System.out.println("O nome do usu�rio �: " + nome);
		System.out.println("O sobrenome do usu�rio �: " + sobrenome);
		System.out.println("O g�nero do usu�rio �: " + genero);
		System.out.println("A data de nascimento do usu�rio �: " + dataNascimento);
		System.out.println("O email do usu�rio �: " + email);
		System.out.println("O id do usu�rfio � " + id);
	}
	
	// Getters and setters
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		try {
			this.senha = CriptografiaUtils.criptografar(senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
}