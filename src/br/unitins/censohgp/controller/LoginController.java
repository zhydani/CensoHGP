package br.unitins.censohgp.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginController {
	@SuppressWarnings("static-access")
	public String logar() {
//		UsuarioDAO dao = new UsuarioDAO();
//		String hashSenha = Util.hashSHA256(getUsuario().getSenha());
//		Usuario usuario = dao.login(getUsuario().getLogin(), hashSenha);
//
//		if (usuario != null) {
//			// armazenando um usuario na sessao
//			if (usuario.getPerfil().equals(getUsuario().getPerfil().valueOf(1))) {
//				Session.getInstance().setAttribute("usuarioLogado", usuario);
//				return "menuadm.xhtml?faces-redirect=true";
//			}
//			if (usuario.getPerfil().equals(getUsuario().getPerfil().valueOf(2))) {
//				Session.getInstance().setAttribute("usuarioLogado", usuario);
//				return "menusu.xhtml?faces-redirect=true";
//			}

//		}
//		Util.addMessageError("Usuário ou Senha Inválido.");
		return null;
	}

	public String cadastrar() {
		return "usuario.xhtml?faces-redirect=true";

	}

	public void limpar() {
//		setUsuario(new Usuario());
//		usuario = new Usuario();
	}
//
//	public Usuario getUsuario() {
//		if (usuario == null)
//			usuario = new Usuario();
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}

}
