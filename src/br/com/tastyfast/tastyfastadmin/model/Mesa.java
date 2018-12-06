package br.com.tastyfast.tastyfastadmin.model;

import java.io.Serializable;

public class Mesa implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer idMesa;
	
	private String mesa;
	private Integer qtdPessoas; 
	
	public Mesa() {
	}

	public Integer getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(Integer idMesa) {
		this.idMesa = idMesa;
	}

	public String getMesa() {
		return mesa;
	}

	public void setMesa(String mesa) {
		this.mesa = mesa;
	}

	public Integer getQtdPessoas() {
		return qtdPessoas;
	}

	public void setQtdPessoas(Integer qtdPessoas) {
		this.qtdPessoas = qtdPessoas;
	}

	@Override
	public String toString() {
		return "Mesa [idMesa=" + idMesa + ", mesa=" + mesa + ", qtdPessoas=" + qtdPessoas + "]";
	}
}
