package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.dao.UsuarioDAO;
import br.com.caelum.livraria.modelo.Usuario;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public RedirectView efetuaLogin() {
		System.out.println("Fazendo login do usu�rio " + this.usuario.getEmail());

		boolean existe = new UsuarioDAO().existe(this.usuario);

		if (existe) {
			return new RedirectView("livro");
		}

		return null;
	}

}
