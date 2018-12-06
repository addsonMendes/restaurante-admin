package br.com.tastyfast.tastfastadmin.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.tastyfast.tastyfastadmin.model.Reserva;
import br.com.tastyfast.tastyfastadmin.model.Restaurante;
import br.com.tastyfast.tastyfastadmin.model.StatusReserva;

@ManagedBean(name = "mbReserva")
@SessionScoped
public class ReservaBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Reserva reserva;
	private List<Reserva> reservas;
	private Restaurante restaurante;
	private HttpSession session;
	
	public ReservaBean() {
		reserva = new Reserva();
		reservas = new ArrayList<>();
		restaurante = new Restaurante();
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		restaurante = (Restaurante) session.getAttribute("restauranteLogado");
	}


	public Reserva getReserva() {
		return reserva;
	}


	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}


	public List<Reserva> getReservas() {
		return reservas;
	}


	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}


	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}


	public List<Reserva> listaReservas(){
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			if(reservas == null){
				reservas = new ArrayList<>();
			}
				Gson gson = new Gson();
				Client c = Client.create();
				WebResource resource = c.resource("http://localhost:8080/tastyfastservice/api/reserva/" + restaurante.getIdRestaurante());
				String resposta = resource.get(String.class);
				reservas = gson.fromJson(resposta, new TypeToken<List<Reserva>>(){}.getType());
				System.out.println(reservas);
		} catch(Exception ex){
			ex.printStackTrace();
			fc.addMessage(null, new FacesMessage("Problemas ao recuperar dados!"));
		}
		return reservas;
	}
	
	public void alteraEstadoReserva(Reserva r){
		System.out.println("Alterou o estado..");
	}
	
	public void confirmaReserva(Reserva r){
		FacesContext fc = FacesContext.getCurrentInstance();
		String resultado = "";
		r.setStatus(StatusReserva.Confirmada);
		try{
			Client c = Client.create();
			WebResource resource = c.resource("http://localhost:8080/tastyfastservice/api/reserva");
			String reservaJSON = new Gson().toJson(r);
			ClientResponse response = resource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, reservaJSON);
			resultado = response.getEntity(String.class);
			fc.addMessage(null, new FacesMessage(resultado));
			
		} catch(Exception ex){
			fc.addMessage(null, new FacesMessage("Problemas ao alterar dados!\n" + ex.getMessage()));
		}
	}
	
	public void cancelarReserva(Reserva r){
		FacesContext fc = FacesContext.getCurrentInstance();
		String resultado = "";
		r.setStatus(StatusReserva.Cancelada);
		try{
			Client c = Client.create();
			WebResource resource = c.resource("http://localhost:8080/tastyfastservice/api/reserva");
			String reservaJSON = new Gson().toJson(r);
			ClientResponse response = resource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, reservaJSON);
			resultado = response.getEntity(String.class);
			fc.addMessage(null, new FacesMessage(resultado));
			
		} catch(Exception ex){
			fc.addMessage(null, new FacesMessage("Problemas ao alterar dados!\n" + ex.getMessage()));
		}
	}
	
}
