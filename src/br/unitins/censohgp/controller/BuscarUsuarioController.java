package br.unitins.censohgp.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;
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
	private String pesquisa;
	private int opcao;
	private Usuario usuario;
	private List<Usuario> listaUsuario = null;
	private List<Usuario> listaBusca = null;

	public int getOpcao() {
		return opcao;
	}

	public void setOpcao(int opcao) {
		this.opcao = opcao;
	}

	private List<SelectItem> listaValue;

	public List<SelectItem> getListaValue() {
		return listaValue;
	}

	public void setListaValue(List<SelectItem> listaValue) {
		this.listaValue = listaValue;
	}

	

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

//	public String buscar(String nome) {
//		UsuarioDAO dao = new UsuarioDAO();
//		Usuario paciente = (Usuario) dao.findByNome(nome);
//		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
//		flash.put("pacienteFlash", paciente);
//
//		return "listabuscapaciente.xhtml?faces-redirect=true";
//	}

	public void buscar() {
		listaUsuario = null;
		getListaUsuarioBusca();
	}

	public String editar(int idpaciente) {
		UsuarioDAO dao = new UsuarioDAO();
		usuario = dao.findId(idpaciente);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("pacienteFlash", usuario);

		return "cadastropaciente.xhtml?faces-redirect=true";
	}

	public boolean excluir(int idpaciente) {
		DAO<Usuario> dao = new UsuarioDAO();
		// faz a exclusao no banco de dados
		try {
			dao.delete(idpaciente);
			dao.getConnection().commit();
			Util.addMessageInfo("Exclusao realizada com sucesso.");
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

	public List<Usuario> getListaUsuario() {
		if (listaBusca == null)
			return listaUsuario;

		return listaBusca;
	}

	public List<Usuario> getListaUsuarioBusca() {
		if (listaUsuario == null) {
			UsuarioDAO dao = new UsuarioDAO();
			listaUsuario = dao.findByNome(getPesquisa());
			if (listaUsuario == null)
				listaUsuario = new ArrayList<Usuario>();
			dao.closeConnection();
		}
		return listaBusca = listaUsuario;

	}

	public void limpar() {
		pesquisa = null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

//	public List<SelectItem> getListaPesquisa() {
//		if(listaValue == null) {
//			listaValue = new ArrayList<SelectItem>();
//			
//			DAO<Usuario> dao = new UsuarioDAO();
//			List<Usuario> pacienteLista = dao.findAll();
//			SelectItem item;
//				for (Usuario paciente : pacienteLista) {
//				
//					opcao.add("nome");
//					listaValue.add("cpf");
//					listaValue.add("nomeMae");
//					listaValue.add("numeroProntuario");
//					item = new SelectItem(paciente, paciente.getNome());
//				}
//			}
//		}
//		
//		return listaSexo;
//	}

//	public String gerarChecklist(int id) {
//		 
//		DAO<Checklist> dao = new ChecklistDAO();
//		try {
//			dao.create(getCheclist());
//			dao.getConnection().commit();
//			Util.addMessageInfo("Inclus�o realizada com sucesso.");
//			limpar();
//		} catch (SQLException e) {
//			dao.rollbackConnection();
//			dao.closeConnection();
//			Util.addMessageInfo("Erro ao incluir o Roupa no Banco de Dados.");
//			e.printStackTrace();
//		}
//		
//		return "gerarchecklist.xhtml?faces-redirect=true";
//	}
//
//	public String visualizarChecklist(int id) {
//	  //ChecklistDAO dao = new CheckListDAO();
//	  //Checklist checklist = dao.findById(id);
//	  //Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
//      //flash.put("ChecklistFlash", checklist);
//
//		return "checklist.xhtml?faces-redirect=true";
//	}
//	public String visualizarHistoricoUsuario() {
//		if (listaUsuario == null) {
//			UsuarioDAO dao = new UsuarioDAO();
//			listaUsuario = dao.findByNome(getNome());
//			if (listaUsuario == null)
//				listaUsuario = new ArrayList<Usuario>();
//			dao.closeConnection();
//		}
//		return "checklist.xhtml?faces-redirect=true";
//	}
//
//	public String baixarUsuario() {
//		return "true";
//	}

}
