package br.com.tastyfast.tastfastadmin.manager;

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

import br.com.tastyfast.tastyfastadmin.model.Restaurante;
import br.com.tastyfast.tastyfastadmin.util.MD5;

@ManagedBean(name = "mbLogin")
@SessionScoped
public class LoginBean {

	private Restaurante restaurante;
	private Restaurante restLogado;
	private List<Restaurante> restaurantes;
	private HttpSession session;
	
	public LoginBean() {
		restaurante = new Restaurante();
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	
	public Restaurante getRestLogado() {
		return restLogado;
	}

	public void setRestLogado(Restaurante restLogado) {
		this.restLogado = restLogado;
	}

	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}

	public String login() {
		restaurante.setSenha(new MD5().criptografa(restaurante.getSenha()));
		Gson gson = new Gson();
		Client c = Client.create();
		WebResource resource = c.resource("http://localhost:8080/tastyfastservice/api/restaurante/" + restaurante.getEmail() + "/" + restaurante.getSenha());
		//String restauranteJSON = new Gson().toJson(restaurante);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		String resposta = resource.get(String.class);
		restLogado = gson.fromJson(resposta, new TypeToken<Restaurante>(){}.getType()); 
		
		if(restLogado == null){
			exibeMensagem("Usuário ou senha inválidos!");
			restLogado = new Restaurante();
			return "login.jsf";
		}
		
		if(restaurante.getEmail().equals(restLogado.getEmail()) && restaurante.getSenha().equals(restLogado.getSenha())){
			session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.setAttribute("restauranteLogado", restLogado);
			return "index.xhtml?faces-redirect=true";
		} else{
			return "login.jsf?faces-redirect=true";
		}

	}
	
	private void exibeMensagem(String mensagem) {
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, new FacesMessage(mensagem));
	}

	public String logout() { 
		session.invalidate();
		return "login.jsf?faces-redirect=true";
	}

}
