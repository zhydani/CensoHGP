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

import br.unitins.censohgp.application.Util;
import br.unitins.censohgp.dao.DAO;
import br.unitins.censohgp.dao.PacienteDAO;
import br.unitins.censohgp.dao.PrecaucaoDAO;
import br.unitins.censohgp.dao.SexoDAO;
import br.unitins.censohgp.dao.SituacaoDAO;
import br.unitins.censohgp.model.Paciente;
import br.unitins.censohgp.model.Precaucao;
import br.unitins.censohgp.model.Sexo;
import br.unitins.censohgp.model.Situacao;

@Named
@ViewScoped
public class AlterarPacienteController implements Serializable {

	private static final long serialVersionUID = -248709330902702727L;
	private Paciente paciente;
	private List<Paciente> listaPaciente;
	private List<Sexo> listaSexo;
	private List<Situacao> listasituacao;
	private List<SelectItem> listaprecaucao;

	public AlterarPacienteController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("pacienteFlash");
		paciente = (Paciente) flash.get("pacienteFlash");
	}

	public void alterar() {
		if (validarDados()) {
			DAO<Paciente> dao = new PacienteDAO();
			// faz a alteracao no banco de dados
			try {
				// gerando um hash da senha
//				getUsuario().setSenha(Util.hashSHA256(getUsuario().getSenha()));
				dao.update(getPaciente());
				dao.getConnection().commit();
				Util.addMessageInfo("Alteração realizada com sucesso.");
				limpar();
				listaPaciente = null;
			} catch (SQLException e) {
				dao.rollbackConnection();
				dao.closeConnection();
				Util.addMessageInfo("Erro ao alterar o Paciente no Banco de Dados.");
				e.printStackTrace();
			}

		}
	}

	private boolean validarDados() {
		if (getPaciente().getNome().isBlank()) {
			Util.addMessageWarn("O campo Nome deve ser informado.");
			return false;
		}
//		if (getUsuario().getSenha() == null || 
//				getUsuario().getSenha().trim().equals("") ) {
//			Util.addMessageError("O campo senha deve ser informado.");
//			return false;
//		}
		return true;

	}

	public Paciente getPaciente() {
		if (paciente == null) {
			paciente = new Paciente();
			paciente.setSituacao(new Situacao());
		}
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Paciente> getListaPaciente() {
		if (listaPaciente == null) {
			DAO<Paciente> dao = new PacienteDAO();
			listaPaciente = dao.findAll();
			if (listaPaciente == null)
				listaPaciente = new ArrayList<Paciente>();
		}
		return listaPaciente;
	}

	public void limpar() {
		paciente = null;
	}

	public List<Sexo> getListaSexo() {
		if (listaSexo == null) {
			SexoDAO dao = new SexoDAO();
			listaSexo = dao.findAll();
		}
		return listaSexo;
	}

	public List<Situacao> getListaSituacao() {
		if (listasituacao == null) {
			SituacaoDAO dao = new SituacaoDAO();
			listasituacao = dao.findAll();
		}
		return listasituacao;
	}

	public List<SelectItem> getlistaprecaucao() {
		if (listaprecaucao == null) {
			listaprecaucao = new ArrayList<SelectItem>();

			DAO<Precaucao> dao = new PrecaucaoDAO();
			List<Precaucao> precaucaoLista = dao.findAll();

			if (precaucaoLista != null && !precaucaoLista.isEmpty()) {
				SelectItem item;

				for (Precaucao precaucao : precaucaoLista) {
					item = new SelectItem(precaucao, precaucao.getNome());
					listaprecaucao.add(item);
				}
			}
		}

		return listaprecaucao;
	}

}
