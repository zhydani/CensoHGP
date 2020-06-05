package br.unitins.censohgp.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.censohgp.application.Session;
import br.unitins.censohgp.application.Util;
import br.unitins.censohgp.dao.TipoDAO;
import br.unitins.censohgp.dao.UsuarioDAO;
import br.unitins.censohgp.model.Usuario;

@Named
@RequestScoped
public class LoginController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2422984558619001146L;
	private Usuario usuario;

	public String logar() {
		UsuarioDAO dao = new UsuarioDAO();
		TipoDAO daotipo = new TipoDAO();
		String hashSenha = Util.hashSHA256(getUsuario().getSenha());
		Usuario usuario = dao.login(getUsuario().getMatricula(), hashSenha);
		Integer administrador = daotipo.findId(1).getId();
		Integer usu = daotipo.findId(2).getId();
		if (usuario != null) {

			// armazenando um usuario na sessao
			
			if (usuario.getTipo().getId().equals(usu)) {
				Session.getInstance().setAttribute("usuarioLogado", usuario);

				return "usuario/index.xhtml?faces-redirect=true";
			}
			if (usuario.getTipo().getId().equals(administrador)) {
				Session.getInstance().setAttribute("usuarioLogado", usuario);

				return "administrador/index.xhtml?faces-redirect=true";

			}

		}

		else {
			Util.addMessageError("Usuario ou Senha Invalido.");
			return null;
		}
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
