package br.unitins.censohgp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.censohgp.application.Util;
import br.unitins.censohgp.dao.PacienteDAO;
import br.unitins.censohgp.model.Paciente;

@Named
@ViewScoped
public class BuscaPacienteController implements Serializable {

	private static final long serialVersionUID = -9042867479794257960L;
	private String pesquisa = null;
	private Paciente paciente = null;
	private List<Paciente> listaPaciente = null;
	private List<Paciente> listaBusca = null;
	private Integer console = null;

	public Integer getConsole() {
		return console;
	}

	public void setConsole(Integer console) {
		this.console = console;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public void buscar() {
		listaPaciente = null;
		getListaPacienteBusca();
	}

	public String checklist(int idpaciente) {
		PacienteDAO dao = new PacienteDAO();
		paciente = dao.findById(idpaciente);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("pacienteFlash", paciente);

		return "checklistpaciente.xhtml?faces-redirect=true";
	}

	public String editar(int idpaciente) {
		PacienteDAO dao = new PacienteDAO();
		paciente = dao.findByIdEsp(idpaciente);
		System.out.println(paciente.toString());
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("pacienteFlash", paciente);

		return "alterarpaciente.xhtml?faces-redirect=true";
	}

//	public BuscaPacienteController() {
//
//		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
//		flash.keep("pacienteFlash");
//		paciente = (Paciente) flash.get("pacienteFlash");
//	}

	public String buscarChecklist(int idpaciente) {
		PacienteDAO dao = new PacienteDAO();
		paciente = dao.findById(idpaciente);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("pacienteFlash", paciente);
		return "buscarchecklist.xhtml?faces-redirect=true";

	}
	public String historicoTransferencia(int idpaciente) {
		PacienteDAO dao = new PacienteDAO();
		paciente = dao.findById(idpaciente);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("pacienteFlash", paciente);
		
		return "historicotransferencia.xhtml?faces-redirect=true";
	}
	public List<Paciente> getListaPaciente() {
		if (listaBusca == null)
			return listaPaciente;

		return listaBusca;
	}

	public List<Paciente> getListaPacienteBusca() {
		if (listaPaciente == null) {
			PacienteDAO dao = new PacienteDAO();
			if (getConsole() == 1) {

				listaPaciente = dao.findByNome(getPesquisa());
				if (listaPaciente == null)
					listaPaciente = new ArrayList<Paciente>();
				dao.closeConnection();
				return listaBusca = listaPaciente;
			}
			if (getConsole() == 2) {

				listaPaciente = dao.findByCpf(getPesquisa());
				if (listaPaciente == null)
					listaPaciente = new ArrayList<Paciente>();
				dao.closeConnection();
				return listaBusca = listaPaciente;
			}

			if (getConsole() == 3) {

				listaPaciente = dao.findByNumeroProntuario(getPesquisa());
				if (listaPaciente == null)
					listaPaciente = new ArrayList<Paciente>();
				dao.closeConnection();
				return listaBusca = listaPaciente;
			}

			if (getConsole() == 4) {

				listaPaciente = dao.findByNomeMae(getPesquisa());
				if (listaPaciente == null)
					listaPaciente = new ArrayList<Paciente>();
				dao.closeConnection();

				return listaBusca = listaPaciente;
			}
		}
		return listaBusca = listaPaciente;

	}

	public void limpar() {
		pesquisa = null;
	}

	public void fazerCadastro() {
		Util.redirect("cadastropaciente.xhtml");
	}

}