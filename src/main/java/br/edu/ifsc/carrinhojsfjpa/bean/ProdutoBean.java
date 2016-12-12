package br.edu.ifsc.carrinhojsfjpa.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifsc.carrinhojsfjpa.dao.ProdutoDAO;
import br.edu.ifsc.carrinhojsfjpa.modelo.Produto;

@ManagedBean
@ViewScoped
public class ProdutoBean {

	@PostConstruct
	public void init() {
		produto = new Produto();
		produtos = ProdutoDAO.getInstance().lista();
	}

	private List<Produto> produtos;
	private Produto produto;

	public ProdutoBean() {
		init();
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
