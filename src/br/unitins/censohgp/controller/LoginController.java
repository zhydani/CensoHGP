package br.unitins.censohgp.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.censohgp.application.Session;
import br.unitins.censohgp.application.Util;
import br.unitins.censohgp.dao.TipoDAO;
import br.unitins.censohgp.dao.UsuarioDAO;
import br.unitins.censohgp.model.Usuario;

@Named
@RequestScoped
public class LoginController {
	private Usuario usuario;

	@SuppressWarnings("static-access")
	public String logar() {
		UsuarioDAO dao = new UsuarioDAO();
		TipoDAO daotipo = new TipoDAO();
		String hashSenha = Util.hashSHA256(getUsuario().getSenha());
		Usuario usuario = dao.login(getUsuario().getMatricula(), hashSenha);

		if (usuario != null) {
			// armazenando um usuario na sessao
			if (daotipo.findId(usuario.getTipo().getId()).getNome().equals("administrador")) {
				Session.getInstance().setAttribute("usuarioLogado", usuario);
				return "administrador/index.xhtml?faces-redirect=true";
			}
			if (daotipo.findId(usuario.getTipo().getId()).getNome().equals("usuario")) {
				Session.getInstance().setAttribute("usuarioLogado", usuario);
				return "usuario/index.xhtml?faces-redirect=true";
			}

		}
		Util.addMessageError("Usuário ou Senha Inválido.");
		return null;
	}


	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
