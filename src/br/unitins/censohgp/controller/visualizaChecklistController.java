package br.unitins.censohgp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.censohgp.dao.DAO;
import br.unitins.censohgp.dao.FatorRiscoDAO;
import br.unitins.censohgp.dao.IncidenteDAO;
import br.unitins.censohgp.dao.ProcedimentoDAO;
import br.unitins.censohgp.model.Checklist;
import br.unitins.censohgp.model.FatorRisco;
import br.unitins.censohgp.model.Incidente;
import br.unitins.censohgp.model.Procedimento;

@Named
@ViewScoped
public class visualizaChecklistController implements Serializable {

	private static final long serialVersionUID = -9042867479794257960L;
	private List<Checklist> listaChecklist = null;
	private List<Procedimento> listaProcedimento;
	private List<FatorRisco> listaFatorRisco;
	private List<Incidente> listaIncidente;
	private Checklist checklist;

	public visualizaChecklistController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("pacienteFlash");
		checklist = (Checklist) flash.get("checklistFlash");
		System.out.println("entrei no visualizar checklist");
		System.out.println("id checklist:" + checklist.toString());
	}

	public List<Procedimento> getListaProcedimento() {
		if (listaProcedimento == null) 
			listaProcedimento = new ArrayList<Procedimento>();
		DAO<Procedimento> dao = new ProcedimentoDAO();
		listaProcedimento = ((ProcedimentoDAO) dao).findByIdChecklist(checklist.getIdchecklist());
		dao.closeConnection();
		return listaProcedimento;
	}

	public List<Incidente> getListaIncidente() {
		if (listaIncidente == null) 
			listaIncidente = new ArrayList<Incidente>();
		DAO<Incidente> dao = new IncidenteDAO();
		listaIncidente = ((IncidenteDAO) dao).findByIdChecklist(checklist.getIdchecklist());
		dao.closeConnection();
		return listaIncidente;
	}

	public List<FatorRisco> getlistaFatorRisco() {
		if (listaFatorRisco == null) 
			listaFatorRisco = new ArrayList<FatorRisco>();
		DAO<FatorRisco> dao = new FatorRiscoDAO();
		listaFatorRisco = ((FatorRiscoDAO) dao).findByIdChecklist(checklist.getIdchecklist());
		dao.closeConnection();
		return listaFatorRisco;
	}

	public void setListaChecklist(List<Checklist> listaChecklist) {
		this.listaChecklist = listaChecklist;
	}

	public List<Checklist> getListaChecklist() {
		return listaChecklist;
	}
	public Checklist getChecklist() {
		if (checklist == null)
			checklist = new Checklist();
		return checklist;
	}

	public void setChecklist(Checklist checklist) {
		this.checklist = checklist;
	}
}