package br.com.tastyfast.tastyfastadmin.model;

import java.io.Serializable;

public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer idCliente;
	private String nome;
	private String email;
	private String senha;
	private String tokenAparelho;
	
	public Cliente() {
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		this.senha = senha;
	}

	public String getTokenAparelho() {
		return tokenAparelho;
	}

	public void setTokenAparelho(String tokenAparelho) {
		this.tokenAparelho = tokenAparelho;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nome=" + nome + ", email=" + email + "]";
	}
}
