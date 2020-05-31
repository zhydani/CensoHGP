package br.unitins.censohgp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.censohgp.dao.FatorRiscoDAO;
import br.unitins.censohgp.dao.IncidenteDAO;
import br.unitins.censohgp.dao.ProcedimentoDAO;
import br.unitins.censohgp.model.FatorRisco;
import br.unitins.censohgp.model.Incidente;
import br.unitins.censohgp.model.Procedimento;

@Named
@ViewScoped
public class ChecklistPacienteController implements Serializable {

	private static final long serialVersionUID = -8186554046373598240L;
	private boolean fatorRisco = false;
	private boolean procedimento = false;
	private boolean incidente = false;
	private List<Procedimento> listaProcedimento = null;
	private List<Incidente> listaIncidente = null;
	private List<FatorRisco> listaFatorRisco = null;
	private String inputArea = null;

	public void getListaChecklist() {
		if (listaProcedimento == null && listaIncidente == null && listaFatorRisco == null) {
			ProcedimentoDAO proc = new ProcedimentoDAO();
			IncidenteDAO inci = new IncidenteDAO();
			FatorRiscoDAO fator = new FatorRiscoDAO();
			if (isProcedimento() == true) {

				listaProcedimento = proc.findAll();
				if (listaProcedimento == null)
					listaProcedimento = new ArrayList<Procedimento>();
				proc.closeConnection();
			}
			if (isFatorRisco() == true) {

				listaFatorRisco = fator.findAll();
				if (listaFatorRisco == null)
					listaFatorRisco = new ArrayList<FatorRisco>();
				proc.closeConnection();
			}

			if (isIncidente() == true) {

				listaIncidente = inci.findAll();
				if (listaIncidente == null)
					listaIncidente = new ArrayList<Incidente>();
				proc.closeConnection();
			}
		}

	}
	public void limpar() {
		setInputArea(null);
	}
	public boolean isFatorRisco() {
		return fatorRisco;
	}

	public void setFatorRisco(boolean fatorRisco) {
		this.fatorRisco = fatorRisco;
	}

	public boolean isProcedimento() {
		return procedimento;
	}

	public void setProcedimento(boolean procedimento) {
		this.procedimento = procedimento;
	}

	public boolean isIncidente() {
		return incidente;
	}

	public void setIncidente(boolean incidente) {
		this.incidente = incidente;
	}

	public List<Procedimento> getListaProcedimento() {
		return listaProcedimento;
	}

	public void setListaProcedimento(List<Procedimento> listaProcedimento) {
		this.listaProcedimento = listaProcedimento;
	}

	public List<Incidente> getListaIncidente() {
		return listaIncidente;
	}

	public void setListaIncidente(List<Incidente> listaIncidente) {
		this.listaIncidente = listaIncidente;
	}

	public List<FatorRisco> getListaFatorRisco() {
		return listaFatorRisco;
	}

	public void setListaFatorRisco(List<FatorRisco> listaFatorRisco) {
		this.listaFatorRisco = listaFatorRisco;
	}

	public String getInputArea() {
		return inputArea;
	}

	public void setInputArea(String inputArea) {
		this.inputArea = inputArea;
	}
}
