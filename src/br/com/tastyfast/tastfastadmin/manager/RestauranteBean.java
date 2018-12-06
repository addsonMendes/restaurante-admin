package br.com.tastyfast.tastfastadmin.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.tastyfast.tastyfastadmin.model.Cardapio;
import br.com.tastyfast.tastyfastadmin.model.Endereco;
import br.com.tastyfast.tastyfastadmin.model.Horario;
import br.com.tastyfast.tastyfastadmin.model.Mesa;
import br.com.tastyfast.tastyfastadmin.model.Restaurante;

@ManagedBean(name = "mbRestaurante")
@ViewScoped
public class RestauranteBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Restaurante restaurante;
	private List<Restaurante> restaurantes;
	private Endereco endereco;
	private Horario horario;
	private Cardapio cardapio;
	private Mesa mesa;
	private HttpSession session;
	
	public RestauranteBean() {
		restaurante = new Restaurante();
		horario = new Horario();
		cardapio = new Cardapio();
		mesa = new Mesa();
		endereco = new Endereco();
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		if(session.getAttribute("restauranteLogado") == null){
			restaurante = new Restaurante();
		} else{
			restaurante = (Restaurante) session.getAttribute("restauranteLogado");
		}
		
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public void cadastraRestaurante(){
		FacesContext fc = FacesContext.getCurrentInstance();
		String resultado = "";
		restaurante.setEndereco(endereco);
		try{
			Client c = Client.create();
			WebResource resource = c.resource("http://localhost:8080/tastyfastservice/api/restaurante");
			String restauranteJSON = new Gson().toJson(restaurante);
			ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, restauranteJSON);
			resultado = response.getEntity(String.class);
			fc.addMessage(null, new FacesMessage(resultado));
			limpaCampos();
		} catch(Exception ex){
			fc.addMessage(null, new FacesMessage("Problemas ao gravar dados!\n" + ex.getMessage()));
		}
	}

	public void listaRestaurantes(){
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			if(restaurantes == null){
				restaurantes = new ArrayList<>();
			} else{
				Gson gson = new Gson();
				Client c = Client.create();
				WebResource resource = c.resource("http://localhost:8080/tastyfastservice/api/restaurante");
				String resposta = resource.get(String.class);
				restaurantes = gson.fromJson(resposta, new TypeToken<List<Restaurante>>(){}.getType());
				fc.addMessage(null, new FacesMessage("Dados recebidos com sucesso!"));
			}
		} catch(Exception ex){
			fc.addMessage(null, new FacesMessage("Problemas ao recuperar dados!"));
		}
	}
	
	public void excluirRestaurante(){
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			Client c = Client.create();
			WebResource resource = c.resource("http://localhost:8080/tastyfastservice/api/restaurante");
			ClientResponse response = resource.type(MediaType.APPLICATION_JSON).delete(ClientResponse.class, restaurante);
			String resultado = response.getEntity(String.class);
			fc.addMessage(null, new FacesMessage(resultado));
			listaRestaurantes();
		} catch(Exception ex){
			
			fc.addMessage(null, new FacesMessage("Problemas ao excluir registros!\n" + ex.getMessage()));
		}
	}
	
	public void buscaCep(){
        try{
            Gson gson = new Gson();
            Client c = Client.create();
            WebResource resource = c.resource("http://api.postmon.com.br/v1/cep/" + endereco.getCep());
            String resposta = resource.get(String.class);
            endereco = gson.fromJson(resposta, new TypeToken<Endereco>(){}.getType());
        } catch(Exception ex){
            ex.printStackTrace();
        }
 }
	
	public void alterarRestaurante(){
		FacesContext fc = FacesContext.getCurrentInstance();
		String resultado = "";
		//restaurante.setEndereco(endereco);
		try{
			Client c = Client.create();
			WebResource resource = c.resource("http://localhost:8080/tastyfastservice/api/restaurante");
			String restauranteJSON = new Gson().toJson(restaurante);
			ClientResponse response = resource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, restauranteJSON);
			resultado = response.getEntity(String.class);
			fc.addMessage(null, new FacesMessage(resultado));
			limpaDadosBasicos();
		} catch(Exception ex){
			fc.addMessage(null, new FacesMessage("Problemas ao alterar dados!\n" + ex.getMessage()));
		}
	}
	
	public void limpaCampos() {
		restaurante = new Restaurante();
		endereco = new Endereco();
	}
	
	public void limpaDadosBasicos(){
		
	}
	
	public void addHorario(){
		restaurante.addHorario(horario);
		horario = new Horario();
		System.out.println(horario);
		System.out.println(restaurante.getHorarios());
	}
	
	public void removeHorario(Horario h){
		restaurante.getHorarios().remove(h);
		horario = new Horario();
		restaurante.getHorarios();
		System.out.println("removeu");
	}
	
	public void addCardapio(){
		restaurante.addCardapio(cardapio);
		//restaurante.getHorarios();
		cardapio = new Cardapio();
		System.out.println(cardapio);
		System.out.println(restaurante.getCardapios());
	}
	
	public void removeCardapio(Cardapio c){
		/*restaurante.getHorarios().remove(h);
		horario = new Horario();
		restaurante.getHorarios();*/
		System.out.println(c);
		
	}
	
	public void addMesa(){
		restaurante.addMesa(mesa);
		//restaurante.getHorarios();
		mesa = new Mesa();
		System.out.println(mesa);
		System.out.println(restaurante.getMesas());
	}
	
	public void removeMesa(Mesa m){
		/*restaurante.getHorarios().remove(h);
		horario = new Horario();
		restaurante.getHorarios();*/
		System.out.println(m);
		
	}
	
}
