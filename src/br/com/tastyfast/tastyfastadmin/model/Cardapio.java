package br.com.tastyfast.tastyfastadmin.model;

import java.io.Serializable;

public class Cardapio implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer idCardapio;
	
	private String prato;
	private Double valor;
	
	public Cardapio() {
	}

	public Integer getIdCardapio() {
		return idCardapio;
	}

	public void setIdCardapio(Integer idCardapio) {
		this.idCardapio = idCardapio;
	}

	public String getPrato() {
		return prato;
	}

	public void setPrato(String prato) {
		this.prato = prato;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Cardapio [idCardapio=" + idCardapio + ", prato=" + prato + ", valor=" + valor + "]";
	}
}
