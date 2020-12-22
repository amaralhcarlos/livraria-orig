package br.com.caelum.livraria.bean;

import java.util.List;
import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	private Integer livroId;
	private Integer autorId;

	public Livro getLivro() {
		return livro;
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public List<Livro> getTodosOsLivros() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public void gravarAutor() {

		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);

		List<Autor> autores = this.getAutoresDoLivro();

		if (autores.contains(autor)) {

			System.out.println("Autor " + autor.getNome() + " já está associado ao livro " + this.livro.getTitulo());
			return;

		}

		System.out.println("Associando livro " + this.livro.getTitulo() + " ao autor(a) " + autor.getNome());

		this.livro.adicionaAutor(autor);
	}

	public void removerAutor(Autor autor) {

		System.out.println("Desassociando autor " + autor.getNome() + " do livro " + this.livro.getTitulo());
		this.livro.removeAutor(autor);

	}

	public RedirectView gravar() {

		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			// throw new RuntimeException("Livro deve ter pelo menos um Autor.");
			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor."));
			return null;
		}

		if (Objects.isNull(this.livro.getId())) {
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		} else {
			new DAO<Livro>(Livro.class).atualiza(this.livro);

		}

		return new RedirectView("livro");
	}

	public void remover(Livro livro) {
		System.out.println("Removendo livro " + this.livro.getTitulo());

		new DAO<Livro>(Livro.class).remove(livro);

	}

	public void carregar(Livro livro) {

		this.livro = livro;

	}

	public void carregarViaId() {
		this.livro = new DAO<Livro>(Livro.class).buscaPorId(this.livroId);
	}

	public String formAutor() {

		System.out.println("Chamando o formulário de Cadastro de Autor");
		return "autor?faces-redirect=true";

	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {

		String valor = value.toString();

		System.out.println("teste isbn");

		if (!valor.startsWith("1") || valor.isEmpty() || valor.isBlank()) {

			System.out.println("Erro isbn");
			throw new ValidatorException(new FacesMessage("ISBN não começa com o dígito 1"));
		}

	}

}
