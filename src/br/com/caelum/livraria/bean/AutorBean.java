package br.com.caelum.livraria.bean;

import java.util.List;
import java.util.Objects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();
	private Integer autorId;

	public Autor getAutor() {
		return autor;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public List<Autor> getTodosOsAutores() {

		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (Objects.isNull(autor.getId())) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}

		this.autor = new Autor();

		return "livro?faces-redirect=true";
	}

	public void carregar(Autor autor) {
		this.autor = autor;
	}

	public void carregarViaId() {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}

	public void remover(Autor autor) {
		System.out.println("Removendo o autor " + autor.getNome());

		new DAO<Autor>(Autor.class).remove(autor);

	}
}
