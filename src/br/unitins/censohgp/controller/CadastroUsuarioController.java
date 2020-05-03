package br.unitins.censohgp.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.censohgp.application.Util;
import br.unitins.censohgp.dao.DAO;
import br.unitins.censohgp.dao.UsuarioDAO;
import br.unitins.censohgp.model.Tipo;
import br.unitins.censohgp.model.Usuario;

@Named
@ViewScoped
public class CadastroUsuarioController implements Serializable {

	private static final long serialVersionUID = -6998638931332554108L;
//	TODO: adicionar o atributo TIPO
	private Usuario usuario;
	private String nome;
	private List<Usuario> listaUsuario;

	public void pesquisar() {
		listaUsuario = null;

	}

	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null) {
			DAO<Usuario> dao = new UsuarioDAO();
			listaUsuario = dao.findAll();
			if (listaUsuario == null)
				listaUsuario = new ArrayList<Usuario>();
		}
		return listaUsuario;
	}

	public void incluir() {
		if (validarDados()) {
			DAO<Usuario> dao = new UsuarioDAO();
			// faz a inclusao no banco de dados
			try {
//				String hashSenha = Util.hashSHA256(getUsuario().getSenha());
//				getUsuario().setSenha(hashSenha);

				getUsuario().setSenha(Util.hashSHA256(getUsuario().getSenha()));

				dao.create(getUsuario());
				dao.getConnection().commit();
				Util.addMessageInfo("Inclusão realizada com sucesso.");
				limpar();
				listaUsuario = null;
			} catch (SQLException e) {
				dao.rollbackConnection();
				dao.closeConnection();
				Util.addMessageInfo("Erro ao incluir o Usuário no Banco de Dados.");
				e.printStackTrace();
			}
		}
	}

	public void alterar() {
		if (validarDados()) {
			DAO<Usuario> dao = new UsuarioDAO();
			// faz a alteracao no banco de dados
			try {
				// gerando um hash da senha
				getUsuario().setSenha(Util.hashSHA256(getUsuario().getSenha()));
				dao.update(getUsuario());
				dao.getConnection().commit();
				Util.addMessageInfo("Alteração realizada com sucesso.");
				limpar();
				listaUsuario = null;
			} catch (SQLException e) {
				dao.rollbackConnection();
				dao.closeConnection();
				Util.addMessageInfo("Erro ao alterar o Usuário no Banco de Dados.");
				e.printStackTrace();
			}

		}
	}

	public void excluir() {
		if (excluir(getUsuario()))
			limpar();
	}

	public boolean excluir(Usuario usuario) {
		DAO<Usuario> dao = new UsuarioDAO();
		// faz a exclusao no banco de dados
		try {
			dao.delete(usuario.getId());
			dao.getConnection().commit();
			Util.addMessageInfo("Exclusão realizada com sucesso.");
			limpar();
			return true;
		} catch (SQLException e) {
			dao.rollbackConnection();
			Util.addMessageInfo("Erro ao excluir o Produto no Banco de Dados.");
			e.printStackTrace();
			return false;
		} finally {
			dao.closeConnection();
		}
	}

	private boolean validarDados() {
//		if (getUsuario().getSenha().isBlank()) {
//			Util.addMessageWarn("O campo senha deve ser informado.");
//			return false;
//		}
		if (getUsuario().getSenha() == null || getUsuario().getSenha().trim().equals("")) {
			Util.addMessageError("O campo senha deve ser informado.");
			return false;
		}
		return true;
	}

	private int ultimoId() {
		int maior = 0;
		for (Usuario usuario : listaUsuario) {
			if (usuario.getId() > maior)
				maior = usuario.getId();
		}
		return maior;
	}

	public void editar(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO();
		// buscando um usuario pelo id
		Usuario usu = dao.findId(usuario.getId());
		setUsuario(usu);
//		setUsuario(dao.findId(usuario.getId()));
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

	public Tipo[] getListaTipo() {
		return Tipo.values();
	}

	public String login() {
		return "login.xhtml?faces-redirect=true";

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
