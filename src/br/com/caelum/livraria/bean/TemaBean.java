package br.com.caelum.livraria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class TemaBean {

	private String tema = "omega";

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String[] getTemas() {

		return new String[] { "aristo", "luna-amber", "luna-blue", "luna-green", "luna-pink", "nova-colored",
				"nova-dark", "nova-light", "omega" };

	}

}
