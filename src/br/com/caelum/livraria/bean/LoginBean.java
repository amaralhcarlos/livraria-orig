package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.dao.UsuarioDAO;
import br.com.caelum.livraria.modelo.Usuario;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	private boolean autenticado;

	public Usuario getUsuario() {
		return usuario;
	}

	public RedirectView efetuaLogin() {
		System.out.println("Fazendo login do usuário " + this.usuario.getEmail());

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);

		boolean existe = new UsuarioDAO().existe(this.usuario);

		if (existe) {

			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);

			return new RedirectView("livro");
		}

		context.addMessage(null, new FacesMessage("Usuário não encontrado"));

		return new RedirectView("login");
	}

	public RedirectView deslogar() {

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioLogado");

		return new RedirectView("login");
	}

}
