package br.unitins.censohgp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.censohgp.dao.PacienteDAO;
import br.unitins.censohgp.model.Paciente;

@Named
@ViewScoped
public class BuscaPacienteController implements Serializable {


	private static final long serialVersionUID = -3155462932614885228L;
	private String nome;
	private Paciente paciente;

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	private List<Paciente> listaPaciente = null;

	
	public void buscar() {
		
//		if (listaPaciente == null) {
//			PacienteDAO dao = new PacienteDAO();
//			listaPaciente = dao.findByNome(getNome());
//			if (listaPaciente == null)
//				listaPaciente = new ArrayList<Paciente>();
//			dao.closeConnection();
//		}
		listaPaciente = null;
	}

	public String editar(int id) {
		PacienteDAO dao = new PacienteDAO();
		Paciente paciente = dao.findById(id);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("pacienteFlash", paciente);

		return "cadastropaciente.xhtml?faces-redirect=true";
	}

	public List<Paciente> getListaPaciente() {
		if (listaPaciente == null) {
			PacienteDAO dao = new PacienteDAO();
			listaPaciente = dao.findByNome(getNome());
			if (listaPaciente == null)
				listaPaciente = new ArrayList<Paciente>();
			dao.closeConnection();
		}
		return listaPaciente;
	}

//	public String gerarChecklist(int id) {
//		 
//		DAO<Checklist> dao = new ChecklistDAO();
//		try {
//			dao.create(getCheclist());
//			dao.getConnection().commit();
//			Util.addMessageInfo("Inclusão realizada com sucesso.");
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
//	public String visualizarHistoricoPaciente() {
//		if (listaPaciente == null) {
//			PacienteDAO dao = new PacienteDAO();
//			listaPaciente = dao.findByNome(getNome());
//			if (listaPaciente == null)
//				listaPaciente = new ArrayList<Paciente>();
//			dao.closeConnection();
//		}
//		return "checklist.xhtml?faces-redirect=true";
//	}
//
//	public String baixarPaciente() {
//		return "true";
//	}

}
