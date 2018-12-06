package br.com.tastyfast.tastfastadmin.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.tastyfast.tastyfastadmin.model.Cliente;
import br.com.tastyfast.tastyfastadmin.model.Restaurante;

@ManagedBean(name = "mbRelatorio")
@RequestScoped
public class RelatorioBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	private List<Cliente> clientes;
	private Restaurante restaurante;
	private HttpSession session;
	private String tipoRelatorio;
	
	public RelatorioBean() {
		cliente = new Cliente();
		restaurante = new Restaurante();
		clientes = new ArrayList<>();
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		restaurante = (Restaurante) session.getAttribute("restauranteLogado");
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public String getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}
	
	public void geraRelatorios(){
		FacesContext fc = FacesContext.getCurrentInstance();
		
		if (tipoRelatorio.equals("clientes"))
			listaClientes();
		else if (tipoRelatorio.equals(""))
			fc.addMessage(null,  new FacesMessage("Selecione um relatório para continuar!"));
		else {
			fc.addMessage(null,  new FacesMessage("Relatório ainda não implementado!"));
		}
	}

	public List<Cliente> listaClientes(){
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			if(clientes == null){
				clientes = new ArrayList<>();
			}
				Gson gson = new Gson();
				Client c = Client.create();
				WebResource resource = c.resource("http://localhost:8080/tastyfastservice/api/cliente/relatorio/" + restaurante.getIdRestaurante());
				String resposta = resource.get(String.class);
				clientes = gson.fromJson(resposta, new TypeToken<List<Object>>(){}.getType());
				System.out.println(clientes);
		} catch(Exception ex){
			ex.printStackTrace();
			fc.addMessage(null, new FacesMessage("Problemas ao recuperar dados!"));
		}
		return clientes;
	}
}
