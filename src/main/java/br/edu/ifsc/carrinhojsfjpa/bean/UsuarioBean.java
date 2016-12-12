package br.edu.ifsc.carrinhojsfjpa.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifsc.carrinhojsfjpa.dao.UsuarioDAO;
import br.edu.ifsc.carrinhojsfjpa.modelo.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean {

	private List<Usuario> usuarios;
	private Usuario usuario;

	public UsuarioBean() {
		init();
	}

	@PostConstruct
	public void init() {
		usuario = new Usuario();
		usuarios = UsuarioDAO.getInstance().listaTodos();
	}

	public List<Usuario> listaTodos() {
		if (usuarios == null || usuarios.isEmpty())
			usuarios = UsuarioDAO.getInstance().listaTodos();

		return usuarios;

	}

	public List<Usuario> getProdutos() {
		return usuarios;
	}

	public void setProdutos(List<Usuario> produtos) {
		this.usuarios = produtos;
	}

	public Usuario getProduto() {
		return usuario;
	}

	public void setProduto(Usuario produto) {
		this.usuario = produto;
	}
}
