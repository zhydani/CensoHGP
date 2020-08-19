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

import br.unitins.censohgp.application.Session;
import br.unitins.censohgp.application.Util;
import br.unitins.censohgp.dao.ChecklistDAO;
import br.unitins.censohgp.dao.DAO;
import br.unitins.censohgp.dao.FatorRiscoDAO;
import br.unitins.censohgp.dao.IncidenteDAO;
import br.unitins.censohgp.dao.ProcedimentoDAO;
import br.unitins.censohgp.model.Checklist;
import br.unitins.censohgp.model.FatorRisco;
import br.unitins.censohgp.model.Incidente;
import br.unitins.censohgp.model.Paciente;
import br.unitins.censohgp.model.Procedimento;
import br.unitins.censohgp.model.Usuario;

@Named
@ViewScoped
public class ChecklistPacienteController implements Serializable {

	private static final long serialVersionUID = -8186554046373598240L;
	private Paciente paciente;
	private Checklist checklist;
	private List<SelectItem> listaProcedimento;
	private List<SelectItem> listaFatorRisco;
	private List<SelectItem> listaIncidente;
	

	public ChecklistPacienteController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("pacienteFlash");
		paciente = (Paciente) flash.get("pacienteFlash");
	}

	public void incluir() {
			DAO<Checklist> dao = new ChecklistDAO();
			try {
				dao.create(getChecklist());
				dao.getConnection().commit();
		
				Util.addMessageInfo("Checklist gerado com sucesso!");
			} catch (SQLException e) {
				dao.rollbackConnection();
				dao.closeConnection();
				Util.addMessageError("Erro ao gerar Checklist!");
				e.printStackTrace();
			}
	}
	
	
	public List<SelectItem> getListaProcedimento() {
		if (listaProcedimento == null) {
			listaProcedimento = new ArrayList<SelectItem>();

			DAO<Procedimento> dao = new ProcedimentoDAO();
			List<Procedimento> procedimentoLista = dao.findAll();

			if (procedimentoLista != null && !procedimentoLista.isEmpty()) {
				SelectItem item;

				for (Procedimento procedimento : procedimentoLista) {
					item = new SelectItem(procedimento, procedimento.getNome());
					listaProcedimento.add(item);
				}
			}
		}
		return listaProcedimento;
	}

	public List<SelectItem> getListaIncidente() {
		if (listaIncidente == null) {
			listaIncidente = new ArrayList<SelectItem>();

			DAO<Incidente> dao = new IncidenteDAO();
			List<Incidente> incidenteLista = dao.findAll();

			if (incidenteLista != null && !incidenteLista.isEmpty()) {
				SelectItem item;

				for (Incidente incidente : incidenteLista) {
					item = new SelectItem(incidente, incidente.getNome());
					listaIncidente.add(item);
				}
			}
		}
		return listaIncidente;
	}
	public List<SelectItem> getlistaFatorRisco() {
		if (listaFatorRisco == null) {
			listaFatorRisco = new ArrayList<SelectItem>();

			DAO<FatorRisco> dao = new FatorRiscoDAO();
			List<FatorRisco> fatorriscoLista = dao.findAll();

		if (fatorriscoLista != null && !fatorriscoLista.isEmpty()) {
				SelectItem item;

				for (FatorRisco fatorRisco : fatorriscoLista) {
				item = new SelectItem(fatorRisco, fatorRisco.getNome());
					listaFatorRisco.add(item);
				}
			}
		}

		return listaFatorRisco;
	}
	public Checklist getChecklist() {
		if (checklist == null) {
			checklist = new Checklist();
			checklist.setPaciente(new Paciente());
			checklist.setUsuario(new Usuario());
		}
		checklist.setUsuario((Usuario)Session.getInstance().getAttribute("usuarioLogado"));
		checklist.setPaciente(getPaciente());
		return checklist;
	}
	public Paciente getPaciente() {
		if (paciente == null) {
			paciente = new Paciente();
			
		}
		return paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public void getListaChecklist() {
		if (listaProcedimento == null) {

		}
	}

	public void setChecklist(Checklist checklist) {
		this.checklist = checklist;
	}
}
