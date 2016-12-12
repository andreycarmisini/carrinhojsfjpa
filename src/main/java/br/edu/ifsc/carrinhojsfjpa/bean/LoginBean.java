package br.edu.ifsc.carrinhojsfjpa.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifsc.carrinhojsfjpa.dao.CarrinhoDAO;
import br.edu.ifsc.carrinhojsfjpa.dao.UsuarioDAO;
import br.edu.ifsc.carrinhojsfjpa.modelo.Carrinho;
import br.edu.ifsc.carrinhojsfjpa.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {
	private Usuario usuario = new Usuario();

	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}

	public String efetuaLogin() {
		Usuario existe = UsuarioDAO.getInstance().existe(this.usuario);
		if (existe != null) {
			existe.setSenha(this.usuario.getSenha());
			Carrinho c = CarrinhoDAO.getInstance().buscaCarrinho(existe);
			if (c == null) {
				c = CarrinhoDAO.getInstance().novo(existe);
			}
			existe.setCarrinho(c);
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("usuarioLogado", existe);
			return "produtos?faces-redirect=true";
		}
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ATENÇÃO: Usuário ou senha inválidos!"));
		return "login?faces-redirect=true";
	}

	public String criarLogin() {
		Usuario existe = UsuarioDAO.getInstance().existe(this.usuario);
		if (existe == null) {
			Usuario existe2 = UsuarioDAO.getInstance().novo(this.usuario);
			Carrinho c = CarrinhoDAO.getInstance().buscaCarrinho(existe2);
			if (c == null) {
				c = CarrinhoDAO.getInstance().novo(existe2);
			}
			existe2.setCarrinho(c);
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("usuarioLogado", existe2);
			return "produtos?faces-redirect=true";
		} else {
			existe.setSenha(this.usuario.getSenha());
			FacesContext context = FacesContext.getCurrentInstance();
			Carrinho c = CarrinhoDAO.getInstance().buscaCarrinho(existe);
			if (c == null) {
				c = CarrinhoDAO.getInstance().novo(existe);
			}
			existe.setCarrinho(c);
			context.getExternalContext().getSessionMap().put("usuarioLogado", existe);

			return "produtos?faces-redirect=true";
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
