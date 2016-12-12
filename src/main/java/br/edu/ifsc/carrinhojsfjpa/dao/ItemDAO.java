package br.edu.ifsc.carrinhojsfjpa.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifsc.carrinhojsfjpa.modelo.Item;
import br.edu.ifsc.carrinhojsfjpa.modelo.Usuario;
import br.edu.ifsc.carrinhojsfjpa.util.JPAUtil;

public class ItemDAO extends DAO<Item> {

	private static ItemDAO instancia;

	public static synchronized ItemDAO getInstance() {
		if (instancia == null) {
			instancia = new ItemDAO();
		}
		return instancia;
	}

	private ItemDAO() {
		super(Item.class);
	}

	@SuppressWarnings("unchecked")
	public List<Item> buscaItem(Usuario usuario) {
		EntityManager manager = new JPAUtil().getEntityManager();

		Query query = manager.createNativeQuery("SELECT e.* FROM item e WHERE e.carrinho_id = ? ", Item.class);

		query.setParameter(1, usuario.getCarrinho().getId());

		List<Item> list = query.getResultList();

		manager.close();

		return list;
	}

	private void geraIdEAdiciona(Usuario u, Item it) {

		List<Item> its = buscaItem(u);
		boolean novoProd = true;
		for (Item i : its) {
			if (i.getProduto().getId().equals(it.getProduto().getId())) {
				novoProd = false;
				i.setQuantidade(i.getQuantidade() + it.getQuantidade());
				i.setTotal(i.getProduto().getPreco().multiply(new BigDecimal(i.getQuantidade())));
				i = atualizar(i);
				CarrinhoDAO.getInstance().atualizar(u);
			}
		}

		if (novoProd) {
			it.setTotal(it.getProduto().getPreco().multiply(new BigDecimal(it.getQuantidade())));
			it.setCarrinho(u.getCarrinho());
			// it.setUsuario(u);
			it = novo(it);

			CarrinhoDAO.getInstance().adiciona(u, it);

		}
	}

	public void adiciona(Usuario u, Item i) {
		geraIdEAdiciona(u, i);
	}

	public Item atualizar(Item i) {
		EntityManager manager = new JPAUtil().getEntityManager();

		manager.getTransaction().begin();

		manager.merge(i);

		manager.getTransaction().commit();

		manager.close();
		return i;
	}

	public Item novo(Item i) {
		EntityManager manager = new JPAUtil().getEntityManager();

		manager.getTransaction().begin();

		manager.persist(i);

		manager.getTransaction().commit();

		manager.close();
		return i;
	}

	@Override
	public void remove(Item i) {
		EntityManager manager = new JPAUtil().getEntityManager();

		manager.getTransaction().begin();

		manager.createNativeQuery("DELETE FROM item WHERE id=" + i.getId()).executeUpdate();

		manager.getTransaction().commit();

		manager.close();
	}

	@Override
	void geraDados() {

	}
}