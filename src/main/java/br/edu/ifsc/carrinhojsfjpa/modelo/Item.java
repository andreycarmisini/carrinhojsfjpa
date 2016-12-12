package br.edu.ifsc.carrinhojsfjpa.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity(name = "item")
public class Item extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Produto produto;

	private Integer quantidade;

	private BigDecimal total;

	@ManyToOne
	private Carrinho carrinho;

	@Transient
	private Integer quantidadeRemover;

	public Item() {
		quantidadeRemover = 1;
	}

	public Item(Produto produto, Integer quantidade) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
		quantidadeRemover = 1;
	}

	public Item(Long id, Produto produto, Integer quantidade, BigDecimal total) {
		super();
		this.id = id;
		this.produto = produto;
		this.quantidade = quantidade;
		this.total = total;
		quantidadeRemover = 1;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public Integer getQuantidadeRemover() {
		return quantidadeRemover;
	}

	public void setQuantidadeRemover(Integer quantidadeRemover) {
		this.quantidadeRemover = quantidadeRemover;
	}
}
