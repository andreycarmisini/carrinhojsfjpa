package br.edu.ifsc.carrinhojsfjpa.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifsc.carrinhojsfjpa.modelo.Carrinho;
import br.edu.ifsc.carrinhojsfjpa.modelo.Item;
import br.edu.ifsc.carrinhojsfjpa.modelo.Usuario;
import br.edu.ifsc.carrinhojsfjpa.util.JPAUtil;

public class CarrinhoDAO extends DAO<Carrinho> {

	public static synchronized CarrinhoDAO getInstance() {
		if (instancia == null) {
			instancia = new CarrinhoDAO();
		}
		return instancia;
	}

	private static CarrinhoDAO instancia;

	private CarrinhoDAO() {
		super(Carrinho.class);
	}

	@SuppressWarnings("unchecked")
	public Carrinho buscaCarrinho(Usuario usuario) {
		EntityManager manager = new JPAUtil().getEntityManager();

		Query query = manager.createQuery("SELECT e FROM carrinho e WHERE e.usuario.id = ? ", Carrinho.class);

		query.setParameter(1, usuario.getId());

		List<Carrinho> list = query.getResultList();

		manager.close();

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public Carrinho buscaCarrinhoTodos(Usuario usuario) {
		EntityManager manager = new JPAUtil().getEntityManager();

		Query query = manager.createQuery("SELECT e FROM carrinho e left join fetch e.itens WHERE e.usuario.id = ? ",
				Carrinho.class);

		query.setParameter(1, usuario.getId());

		List<Carrinho> list = query.getResultList();

		manager.close();

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	public BigDecimal carrinhoTotal(Usuario usuario) {
		EntityManager manager = new JPAUtil().getEntityManager();

		Query query = manager.createNativeQuery(
				"SELECT sum(e.total) as total FROM carrinho c inner join item e on (e.carrinho_id = c.id) WHERE c.usuario_id = ? ");

		query.setParameter(1, usuario.getId());

		BigDecimal list = (BigDecimal) query.getSingleResult();

		manager.close();
		if (list == null) {
			return new BigDecimal(0);
		}
		return list;
	}

	public Carrinho atualizar(Usuario u) {
		EntityManager manager = new JPAUtil().getEntityManager();
		Carrinho c = u.getCarrinho();

		c.setTotal(carrinhoTotal(u));

		manager.getTransaction().begin();

		manager.merge(c);

		manager.getTransaction().commit();

		manager.close();
		return c;
	}

	public Carrinho adiciona(Usuario u, Item i) {
		EntityManager manager = new JPAUtil().getEntityManager();
		Carrinho c = buscaCarrinhoTodos(u);

		c.getItens().add(i);
		c.setTotal(c.getTotal().add(i.getTotal()));

		manager.getTransaction().begin();

		manager.merge(c);

		manager.getTransaction().commit();
		manager.close();
		return c;
	}

	public Carrinho novo(Usuario usuario) {
		Carrinho c = new Carrinho(new ArrayList<Item>(), new BigDecimal(0), usuario);

		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();

		manager.persist(c);

		manager.getTransaction().commit();
		manager.close();
		return c;
	}

	public Carrinho remove(Usuario u, Item p) {

		Carrinho c = buscaCarrinhoTodos(u);
		if (p.getQuantidade().equals(p.getQuantidadeRemover())) {
			ItemDAO.getInstance().remove(p);
			c.getItens().remove(p);
		} else {
			p.setQuantidade(p.getQuantidade() - p.getQuantidadeRemover());
			p.setTotal(p.getProduto().getPreco().multiply(new BigDecimal(p.getQuantidade())));
			ItemDAO.getInstance().atualizar(p);
			p.setQuantidadeRemover(1);
		}

		atualizar(u);
		c = buscaCarrinhoTodos(u);
		return c;

	}

	@Override
	void geraDados() {

	}

}