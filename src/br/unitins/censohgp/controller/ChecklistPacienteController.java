package br.unitins.censohgp.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.censohgp.dao.ChecklistDAO;
import br.unitins.censohgp.model.Checklist;

@Named
@ViewScoped
public class ChecklistPacienteController implements Serializable {

	private static final long serialVersionUID = -9042867479794257960L;
	private String nome;
	private Checklist checklist;

	private List<Checklist> listaChecklist = null;

	public Checklist getChecklist() {
		return checklist;
	}

	public void setChecklist(Checklist checklist) {
		this.checklist = checklist;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

//	public String buscar(String nome) {
//		ChecklistDAO dao = new ChecklistDAO();
//		Checklist checklist = (Checklist) dao.findByNome(nome);
//		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
//		flash.put("checklistFlash", checklist);
//
//		return "listabuscachecklist.xhtml?faces-redirect=true";
//	}

	public void buscar() {
		listaChecklist = null;
		System.out.println("oi");
	}


	public List<Checklist> getListaChecklist() {
		if (listaChecklist == null) {
			ChecklistDAO dao = new ChecklistDAO();
//			listaChecklist = dao.findByNome(getNome());
			if (listaChecklist == null)
				listaChecklist = new ArrayList<Checklist>();
			dao.closeConnection();
		}
		return listaChecklist;
	}

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
//	public String visualizarHistoricoChecklist() {
//		if (listaChecklist == null) {
//			ChecklistDAO dao = new ChecklistDAO();
//			listaChecklist = dao.findByNome(getNome());
//			if (listaChecklist == null)
//				listaChecklist = new ArrayList<Checklist>();
//			dao.closeConnection();
//		}
//		return "checklist.xhtml?faces-redirect=true";
//	}
//
//	public String baixarChecklist() {
//		return "true";
//	}

}
