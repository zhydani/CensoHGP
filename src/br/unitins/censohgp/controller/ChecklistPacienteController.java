package br.unitins.censohgp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.censohgp.dao.DAO;
import br.unitins.censohgp.dao.PrecaucaoDAO;
import br.unitins.censohgp.model.Paciente;
import br.unitins.censohgp.model.Precaucao;

@Named
@ViewScoped
public class ChecklistPacienteController implements Serializable {

	private static final long serialVersionUID = -8186554046373598240L;
	private Paciente paciente = null;
	private List<SelectItem> listaProcedimento = null;
	private List<SelectItem> listaFatorRisco = null;
	private List<SelectItem> listaIncidente = null;

	private String inputArea = null;

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public void getListaChecklist() {
		if (listaProcedimento == null) {

		}

	}

	public void limpar() {
//		setInputArea(null);
	}

	public List<SelectItem> getlistaProcedimento() {
		if (listaProcedimento == null) {
			listaProcedimento = new ArrayList<SelectItem>();

			DAO<Precaucao> dao = new PrecaucaoDAO();
			List<Precaucao> precaucaoLista = dao.findAll();

			if (precaucaoLista != null && !precaucaoLista.isEmpty()) {
				SelectItem item;

				for (Precaucao precaucao : precaucaoLista) {
					item = new SelectItem(precaucao, precaucao.getNome());
					listaProcedimento.add(item);
				}
			}
		}

		return listaProcedimento;
	}

	public List<SelectItem> getlistaFatorRisco() {
		if (listaFatorRisco == null) {
			listaFatorRisco = new ArrayList<SelectItem>();

			DAO<Precaucao> dao = new PrecaucaoDAO();
			List<Precaucao> precaucaoLista = dao.findAll();

			if (precaucaoLista != null && !precaucaoLista.isEmpty()) {
				SelectItem item;

				for (Precaucao precaucao : precaucaoLista) {
					item = new SelectItem(precaucao, precaucao.getNome());
					listaFatorRisco.add(item);
				}
			}
		}

		return listaFatorRisco;
	}

	public List<SelectItem> getlistaIncidente() {
		if (listaIncidente == null) {
			listaIncidente = new ArrayList<SelectItem>();

			DAO<Precaucao> dao = new PrecaucaoDAO();
			List<Precaucao> precaucaoLista = dao.findAll();

			if (precaucaoLista != null && !precaucaoLista.isEmpty()) {
				SelectItem item;

				for (Precaucao precaucao : precaucaoLista) {
					item = new SelectItem(precaucao, precaucao.getNome());
					listaIncidente.add(item);
				}
			}
		}

		return listaIncidente;
	}
}
