package br.edu.ifsc.carrinhojsfjpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifsc.carrinhojsfjpa.modelo.Produto;
import br.edu.ifsc.carrinhojsfjpa.util.JPAUtil;

public class ProdutoDAO extends DAO<Produto> {

	private static ProdutoDAO instancia;

	public static synchronized ProdutoDAO getInstance() {
		if (instancia == null) {
			instancia = new ProdutoDAO();
		}
		return instancia;
	}

	private ProdutoDAO() {
		super(Produto.class);
	}

	@Override
	public void atualiza(Produto p) {
	}

	@SuppressWarnings("unchecked")
	public Produto find(Long id) {
		EntityManager manager = new JPAUtil().getEntityManager();
		Query query = manager.createNativeQuery("SELECT e.* FROM produto e WHERE e.id = ?", Produto.class);

		query.setParameter(1, id);

		List<Produto> list = query.getResultList();
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Produto> lista() {
		EntityManager manager = new JPAUtil().getEntityManager();
		List<Produto> list = new ArrayList<Produto>();
		Query query = manager.createQuery("SELECT e FROM produto e ");
		list = query.getResultList();
		manager.close();

		return list;
	}

	@Override
	public void remove(Long id) {
	}

	@Override
	public void remove(Produto p) {
	}

	@Override
	void geraDados() {
	}
}