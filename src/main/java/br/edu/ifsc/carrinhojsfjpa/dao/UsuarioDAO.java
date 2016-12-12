package br.edu.ifsc.carrinhojsfjpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifsc.carrinhojsfjpa.modelo.Usuario;
import br.edu.ifsc.carrinhojsfjpa.util.JPAUtil;

public class UsuarioDAO extends DAO<Usuario> {
	public static synchronized UsuarioDAO getInstance() {
		if (instancia == null) {
			instancia = new UsuarioDAO();
		}
		return instancia;
	}

	private static UsuarioDAO instancia;

	private UsuarioDAO() {
		super(Usuario.class);
	}

	@SuppressWarnings("unchecked")
	public Usuario existe(Usuario usuario) {
		EntityManager manager = new JPAUtil().getEntityManager();

		Query query = manager.createNativeQuery("SELECT e.* FROM usuario e WHERE e.email = ? AND e.senha = ?",
				Usuario.class);

		query.setParameter(1, usuario.getEmail());
		query.setParameter(2, usuario.getSenha());

		List<Usuario> list = query.getResultList();

		manager.close();
		if (list.isEmpty()) {
			return null;
		}

		return list.get(0);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> listaTodos() {
		EntityManager manager = new JPAUtil().getEntityManager();

		Query query = manager.createNativeQuery("SELECT e.* FROM usuario e ", Usuario.class);

		List<Usuario> list = query.getResultList();

		manager.close();
		return list;
	}

	@Override
	void geraDados() {
	}

	public Usuario novo(Usuario usuario) {
		EntityManager manager = new JPAUtil().getEntityManager();

		manager.getTransaction().begin();

		manager.persist(usuario);

		manager.getTransaction().commit();

		manager.close();
		return usuario;
	}
}
