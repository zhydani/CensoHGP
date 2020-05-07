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
public class ListaBuscaController implements Serializable {

	private static final long serialVersionUID = -7213113738459475840L;

//	public ListaBuscaController() {
//		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
//		flash.keep("pacienteFlash");
//		paciente = (Paciente) flash.get("pacienteFlash");
//	}


}
