package br.unitins.censohgp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.censohgp.dao.ChecklistDAO;
import br.unitins.censohgp.dao.PacienteDAO;
import br.unitins.censohgp.dao.UsuarioDAO;
import br.unitins.censohgp.model.Checklist;
import br.unitins.censohgp.model.Paciente;
import br.unitins.censohgp.model.Usuario;

@Named
@ViewScoped
public class BuscaChecklistController implements Serializable {

	private static final long serialVersionUID = -9042867479794257960L;
	private List<Checklist> listaChecklist = null;
	private Paciente paciente;
	private Checklist checklist;

	public BuscaChecklistController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("pacienteFlash");
		paciente = (Paciente) flash.get("pacienteFlash");
	}

	public String visualizar(int idPaciente) {
		ChecklistDAO dao = new ChecklistDAO();
		checklist = dao.findById(idPaciente);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("checklistFlash", checklist);
		return "visualizarchecklist.xhtml?faces-redirect=true";
	}

	public void setListaChecklist(List<Checklist> listaChecklist) {
		this.listaChecklist = listaChecklist;
	}

	public List<Checklist> getListaChecklist() {
		if (listaChecklist == null)
			listaChecklist = new ArrayList<Checklist>();
		ChecklistDAO dao = new ChecklistDAO();
		listaChecklist = dao.findByIdPaciente(getPaciente().getIdpaciente());
		dao.closeConnection();
		return listaChecklist;
	}

	public Paciente getPaciente() {
		if (paciente == null)
			paciente = new Paciente();
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
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