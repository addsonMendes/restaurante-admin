package br.com.tastyfast.tastyfastadmin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Restaurante implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer idRestaurante;
	private String nome;
	private String email;
	private String senha;
	private String tipoCulinaria;
	private String horarioFuncionamento;
	
	private Endereco endereco;
	
	private List<Horario> horarios;
	
	private List<Cardapio> cardapios;
	
	private List<Mesa> mesas;
	
	public Restaurante() {
	}

	public Restaurante(Integer idRestaurante, String nome, String email, String senha) {
		super();
		this.idRestaurante = idRestaurante;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}


	public Integer getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(Integer idRestaurante) {
		this.idRestaurante = idRestaurante;
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

	public String getTipoCulinaria() {
		return tipoCulinaria;
	}

	public void setTipoCulinaria(String tipoCulinaria) {
		this.tipoCulinaria = tipoCulinaria;
	}

	public String getHorarioFuncionamento() {
		return horarioFuncionamento;
	}

	public void setHorarioFuncionamento(String horarioFuncionamento) {
		this.horarioFuncionamento = horarioFuncionamento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}
	
	public List<Cardapio> getCardapios() {
		return cardapios;
	}

	public void setCardapios(List<Cardapio> cardapios) {
		this.cardapios = cardapios;
	}
	
	public List<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}

	public void addHorario(Horario horario){
		if(horarios == null){
			horarios = new ArrayList<Horario>();
		}
		horarios.add(horario);
	}
	
	public void addCardapio(Cardapio cardapio){
		if(cardapios == null){
			cardapios = new ArrayList<Cardapio>();
		}
		cardapios.add(cardapio);
	}
	
	public void addMesa(Mesa mesa){
		if(mesas == null){
			mesas = new ArrayList<Mesa>();
		}
		mesas.add(mesa);
	}

	@Override
	public String toString() {
		return "Restaurante [idRestaurante=" + idRestaurante + ", nome=" + nome + ", email=" + email + ", senha="
				+ senha + ", endereco=" + endereco + ", horarios=" + horarios + ", cardapios=" + cardapios + "]";
	}

}
