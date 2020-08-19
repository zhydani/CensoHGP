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
import br.unitins.censohgp.dao.ProcedimentoDAO;
import br.unitins.censohgp.model.Checklist;
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
		
				Util.addMessageInfo("Inclusao realizada com sucesso.");
			} catch (SQLException e) {
				dao.rollbackConnection();
				dao.closeConnection();
				Util.addMessageError("Erro ao incluir o Usuario no Banco de Dados.");
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
		System.out.println("id do paciente aqui: "+paciente.getIdpaciente());
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
