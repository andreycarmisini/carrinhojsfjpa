package br.edu.ifsc.carrinhojsfjpa.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifsc.carrinhojsfjpa.dao.ItemDAO;
import br.edu.ifsc.carrinhojsfjpa.modelo.Item;
import br.edu.ifsc.carrinhojsfjpa.modelo.Produto;
import br.edu.ifsc.carrinhojsfjpa.modelo.Usuario;

@ManagedBean
@ViewScoped
public class ItemBean {

	public ItemBean() {
		init();
	}

	@PostConstruct
	public void init() {
	}

	public void adiciona(Produto produto) {
		Item item = new Item();
		item.setProduto(produto);
		item.setQuantidade(Integer.valueOf(produto.getQuantidade()));
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario u = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		ItemDAO.getInstance().adiciona(u, item);

		produto.setQuantidade("1");
	}

}
