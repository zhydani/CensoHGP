package br.unitins.censohgp.controller;

import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.censohgp.application.Session;
import br.unitins.censohgp.application.Util;
import br.unitins.censohgp.dao.DAO;
import br.unitins.censohgp.dao.UsuarioDAO;
import br.unitins.censohgp.model.Usuario;

@Named
@ViewScoped
public class PerfilController implements Serializable {

	private static final long serialVersionUID = -6998638931332554108L;
	private Usuario usuario;
	private Usuario usuarioLogado = null;
	private String senha;
	private String senhanova;
	private String confirmar;

	public void alterar() {
		if (validarDados()) {
			UsuarioDAO dao = new UsuarioDAO();
			try {
				usuarioLogado = (Usuario) Session.getInstance().getAttribute("usuarioLogado");	
				
				if(senhanova.equals(confirmar)) {
					setSenha(Util.hashSHA256(senha));
					setSenhanova(Util.hashSHA256(senhanova));
					dao.updateSenha(usuarioLogado.getEmail(), getSenha(), getSenhanova());
					dao.getConnection().commit();
					Util.addMessageInfo("Inclusao realizada com sucesso.");
					limpar();
				}
				else {
					Util.addMessageError("As senhas nao condizem.");
				}
				

				
			} catch (SQLException e) {
				dao.rollbackConnection();
				dao.closeConnection();
				Util.addMessageError("Erro ao incluir o Usuario no Banco de Dados.");
				e.printStackTrace();
			}
		}
	}
	
	private boolean validarDados() {
		if (senha == null || senha.trim().equals("")) {
			Util.addMessageError("O campo senha antiga deve ser informado.");
			return false;
		}
		if (senhanova == null || senhanova.trim().equals("")) {
			Util.addMessageError("O campo senha nova deve ser informado.");
			return false;
		}
		return true;
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void limpar() {
		usuario = null;
	}



	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenhanova() {
		return senhanova;
	}

	public void setSenhanova(String senhanova) {
		this.senhanova = senhanova;
	}

	public String getConfirmar() {
		return confirmar;
	}

	public void setConfirmar(String confirmar) {
		this.confirmar = confirmar;
	}

	

}
