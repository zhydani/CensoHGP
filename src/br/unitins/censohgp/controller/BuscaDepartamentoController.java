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
import br.unitins.censohgp.dao.DepartamentoDAO;
import br.unitins.censohgp.model.Departamento;

@Named
@ViewScoped
public class BuscaDepartamentoController implements Serializable {

	private static final long serialVersionUID = -9042867479794257960L;
	private String nomehospital = null;
	private String nomedepartamento = null;
	private List<Departamento> listaBusca = null;
	private List<Departamento> listaDepartamento = null;
	private Departamento departamento;
	
	public List<Departamento> getListaDepartamentoBusca() {
		if (listaDepartamento == null) {
			DepartamentoDAO dao = new DepartamentoDAO();
			listaDepartamento = dao.findByName(getNomeHospital(), getNomeDepartamento());
			if (listaDepartamento == null) {
				listaDepartamento = new ArrayList<Departamento>();
				dao.closeConnection();
			}
			dao.closeConnection();
		}
		return listaBusca = listaDepartamento;
	}
	public Departamento getDepartamento() {
		if (departamento == null) {
			departamento = new Departamento();
		}
		return departamento;
	}
	
	public String editar(int iddepartamento) {
		DepartamentoDAO dao = new DepartamentoDAO();
		Departamento departamento = dao.findById2(iddepartamento);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("departamentoFlash", departamento);
		return "alterardepartamento.xhtml?faces-redirect=true";
	}

	
	public String cadastrar() {
		return "cadastrardepartamento.xhtml?faces-redirect=true";
	}
	public String cancelar() {
		return "buscardepartamento.xhtml?faces-redirect=true";
	}
	public boolean excluir(int iddepartamento) {
		DAO<Departamento> dao = new DepartamentoDAO();
		try {
			dao.delete(iddepartamento);
			dao.getConnection().commit();
			Util.addMessageInfo("Exclusão realizada com sucesso.");
			listaBusca = null;
			buscar();
			return true;
		} catch (SQLException e) {
			dao.rollbackConnection();
			Util.addMessageInfo("Erro ao excluir departamento.");
			e.printStackTrace();
			return false;
		} finally {
			listaBusca = null;
			buscar();
			dao.closeConnection();
		}
	}
	
	public List<Departamento> getListaDepartamento() {
		if (listaBusca == null) {
			return listaDepartamento;
		}
		return listaBusca;
	}
	public void buscar() {
		listaDepartamento = null;
		getListaDepartamentoBusca();
	}
	
	public String getNomeHospital() {
		return nomehospital;
	}
	public String getNomeDepartamento() {
		return nomedepartamento;
	}
	
	public void setNomeHospital(String nomehospital) {
		this.nomehospital = nomehospital;
	}
	public void setNomeDepartamento(String nomedepartamento) {
		this.nomedepartamento = nomedepartamento;
	}
	public void limpar() {
		nomehospital = null;
		nomedepartamento = null;
	}

}
