package br.com.tastyfast.tastyfastadmin.model;

import java.io.Serializable;

public class Horario implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer idHorario;
	private String horario;

	public Horario() {
	}

	public Integer getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(Integer idHorario) {
		this.idHorario = idHorario;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	@Override
	public String toString() {
		return "Horario [idHorario=" + idHorario + ", horario=" + horario + "]";
	}

}
