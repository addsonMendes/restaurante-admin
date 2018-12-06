package br.com.tastyfast.tastyfastadmin.model;

import java.io.Serializable;

public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idEndereco;
	private String cep;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String estado;
	private String uf;
	
	public Endereco() {
	}

	public Integer getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "Endereco [idEndereco=" + idEndereco + ", cep=" + cep + ", logradouro=" + logradouro + ", bairro="
				+ bairro + ", cidade=" + cidade + ", estado=" + estado + ", uf=" + uf + "]";
	}	
}
