package br.com.tastyfast.tastfastadmin.manager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "mbMenu")
@SessionScoped
public class MenuBean {

    private String pagina = "index.xhtml";

    public void entrarIndex() {
        pagina = "index.xhtml";
    }

    public void entrarCadastroRestaurante() {
        pagina = "cadastroRestaurante.xhtml";
    }
    
    public void entrarMeusDados() {
    	pagina = "meusDados.xhtml";
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }
}
