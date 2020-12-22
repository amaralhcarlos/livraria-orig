package br.com.caelum.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Usuario;

public class UsuarioDAO {

	public boolean existe(Usuario usuario) {
		EntityManager em = new JPAUtil().getEntityManager();

		TypedQuery<Usuario> query = em
				.createQuery("select u from Usuario u where u.email = :pEmail and u.senha = :pSenha", Usuario.class)
				.setParameter("pEmail", usuario.getEmail())
				.setParameter("pSenha", usuario.getSenha());
		
		Usuario result;
		
		try {
			result = query.getSingleResult();
		}
		catch(NoResultException nrex) {
			return false;
		}
				
		em.close();

		return true;
	}

}
