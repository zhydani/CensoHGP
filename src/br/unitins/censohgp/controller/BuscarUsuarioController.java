package br.unitins.censohgp.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.censohgp.application.Util;
import br.unitins.censohgp.dao.DAO;
import br.unitins.censohgp.dao.UsuarioDAO;
import br.unitins.censohgp.model.Usuario;

@Named
@ViewScoped
public class BuscarUsuarioController implements Serializable {

	private static final long serialVersionUID = -9042867479794257960L;
	private String nome = null;
	private String matricula = null;
	private Usuario usuario = null;
	private List<Usuario> listaBusca = null;
	private List<Usuario> listaUsuario = null;
	
	
	public List<Usuario> getListaUsuarioBusca() {
		if (listaUsuario == null) {
			UsuarioDAO dao = new UsuarioDAO();
			listaUsuario = dao.findByName(getNome(), getMatricula());
			if (listaUsuario == null) {
				listaUsuario = new ArrayList<Usuario>();
				dao.closeConnection();
			}
			dao.closeConnection();
		}
		return listaBusca = listaUsuario;
	}
	
	public String editar(int id) {
		UsuarioDAO dao = new UsuarioDAO();
		usuario = dao.findId(id);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("usuarioFlash", usuario);

		return "cadastrousuario.xhtml?faces-redirect=true";
	}
	
	
	public boolean excluir(int id) {
		DAO<Usuario> dao = new UsuarioDAO();
		try {
			dao.delete(id);
			dao.getConnection().commit();
			Util.addMessageInfo("Exclusao realizada com sucesso.");
			listaBusca = null;
			getListaUsuarioBusca();
			return true;
		} catch (SQLException e) {
			dao.rollbackConnection();
			Util.addMessageInfo("Erro ao excluir o Produto no Banco de Dados.");
			e.printStackTrace();
			return false;
		} finally {
			listaBusca = null;
			getListaUsuarioBusca();
			dao.closeConnection();
		}
	}
	
	public List<Usuario> getListaUsuario() {
		if (listaBusca == null) {
			return listaUsuario;
		}
		return listaBusca;
	}
	public void buscar() {
		listaUsuario = null;
		getListaUsuarioBusca();
	}
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public void limpar() {
		nome = null;
		matricula = null;
	}
	
	

}
