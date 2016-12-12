package br.edu.ifsc.carrinhojsfjpa.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifsc.carrinhojsfjpa.dao.CarrinhoDAO;
import br.edu.ifsc.carrinhojsfjpa.modelo.Carrinho;
import br.edu.ifsc.carrinhojsfjpa.modelo.Item;
import br.edu.ifsc.carrinhojsfjpa.modelo.Usuario;

@ManagedBean
@ViewScoped
public class CarrinhoBean {

	private Carrinho carrinho;
	private List<Item> carrinhoItens;

	public BigDecimal carrinhoTotal() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario user = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		if (user != null)
			return CarrinhoDAO.getInstance().carrinhoTotal(user);
		return new BigDecimal(0);
	}

	public CarrinhoBean() {
		init();
	}

	@PostConstruct
	public void init() {
		getCarrinho();
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario user = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		carrinhoItens = CarrinhoDAO.getInstance().buscaCarrinhoTodos(user).getItens();
	}

	public List<Integer> listaQuantidade(Item i) {
		List<Integer> list = new ArrayList<Integer>();
		for (int j = 1; j <= i.getQuantidade(); j++) {
			list.add(j);
		}
		return list;
	}

	public Carrinho getCarrinho() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario user = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		if (user != null)
			carrinho = CarrinhoDAO.getInstance().buscaCarrinho(user);
		return carrinho;
	}

	public void remover(Item p) {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario user = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		carrinhoItens = CarrinhoDAO.getInstance().remove(user, p).getItens();
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public List<Item> getCarrinhoItens() {
		return carrinhoItens;
	}

	public void setCarrinhoItens(List<Item> carrinhoItens) {
		this.carrinhoItens = carrinhoItens;
	}

}
