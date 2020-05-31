package br.unitins.censohgp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.censohgp.model.Procedimento;

@Named
@ViewScoped
public class ChecklistPacienteController implements Serializable {

	private static final long serialVersionUID = -8186554046373598240L;
	private boolean fatorRisco = false;
	private boolean procedimento = false;
	private boolean incidente = false;
	private List<Procedimento> listaProcedimento = null;

	private String inputArea = null;

	public void getListaChecklist() {
		if (listaProcedimento == null) {

		}

	}
	public void limpar() {
//		setInputArea(null);
	}

	
}
