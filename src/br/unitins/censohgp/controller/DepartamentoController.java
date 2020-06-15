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

import br.unitins.censohgp.dao.CidadeDepartamentoDAO;
import br.unitins.censohgp.dao.DAO;
import br.unitins.censohgp.dao.DepartamentoDAO;
import br.unitins.censohgp.dao.EstadoDepartamentoDAO;
import br.unitins.censohgp.dao.PacienteDAO;
import br.unitins.censohgp.application.Util;
import br.unitins.censohgp.model.CidadeDepartamento;
import br.unitins.censohgp.model.Departamento;
import br.unitins.censohgp.model.EstadoDepartamento;
import br.unitins.censohgp.model.Paciente;
import br.unitins.censohgp.model.StatusDepartamento;

@Named
@ViewScoped
public class DepartamentoController implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2938919235906464048L;


	private EstadoDepartamento estadoSelecionado;
	private CidadeDepartamento cidadeSelecionado;
	private Departamento departamento;

	private List<Departamento> listaDepartamento;
	private List<SelectItem> listaEstados;
	private List<SelectItem> listaCidades;

	public DepartamentoController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("departamentoFlash");
		departamento = (Departamento) flash.get("departamentoFlash");
	}

	public List<Departamento> getListaDepartamento() {
		if (listaDepartamento == null) {
			DAO<Departamento> dao = new DepartamentoDAO();
			listaDepartamento = dao.findAll();
			if (listaDepartamento == null)
				listaDepartamento = new ArrayList<Departamento>();
		} 
		return listaDepartamento;
	}

	// METODO QUE REDIRECIONA PARA PAGINA DE EDIï¿½ï¿½O
	public String ver(int id) throws SQLException {
		DepartamentoDAO dao = new DepartamentoDAO();
		Departamento departamento = dao.findById(id);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("departamentoFlash", departamento);

		return "departamento.xhtml?faces-redirect=true";
	}

	public void incluir() {
		if (validarDados()) {
			DAO<Departamento> dao = new DepartamentoDAO();
			// faz a inclusao no banco de dados
			try {
				dao.create(getDepartamento());
				dao.getConnection().commit();
				Util.addMessageInfo("Inclusão realizada com sucesso.");
				limpar();
				listaDepartamento = null;
			} catch (SQLException e) {
				dao.rollbackConnection();
				dao.closeConnection();
				Util.addMessageInfo("Erro ao incluir o Departamento no Banco de Dados.");
				e.printStackTrace();
			}
		}
	}

	public void alterar() {
		if (validarDados()) {
			DAO<Departamento> dao = new DepartamentoDAO();
			// faz a alteracao no banco de dados
			try {
				// gerando um hash da senha
				//						getUsuario().setSenha(Util.hashSHA256(getUsuario().getSenha()));
				dao.update(getDepartamento());
				dao.getConnection().commit();
				Util.addMessageInfo("Alteração realizada com sucesso.");
				limpar();
				listaDepartamento = null;
			} catch (SQLException e) {
				dao.rollbackConnection();
				dao.closeConnection();
				Util.addMessageInfo("Erro ao alterar o Departamento no Banco de Dados.");
				e.printStackTrace();
			}

		}
	}

//	public void excluir() {
//		if (excluir(getDepartamento()))
//			limpar();
//	}
//
//	public boolean excluir(int iddepartamento) {
//		DAO<Departamento> dao = new DepartamentoDAO();
//		try {
//			dao.delete(iddepartamento);
//			dao.getConnection().commit();
//			Util.addMessageInfo("ExclusÃ£o realizada com sucesso.");
//			listaBusca = null;
//			buscar();
//			return true;
//		} catch (SQLException e) {
//			dao.rollbackConnection();
//			Util.addMessageInfo("Erro ao excluir departamento.");
//			e.printStackTrace();
//			return false;
//		} finally {
//			dao.closeConnection();
//		}
//	}

	private boolean validarDados() {
		if (getDepartamento().getNomeDepartamento().isBlank()) {
			Util.addMessageWarn("O campo nome do departamento deve ser informado.");
			return false;
		}
		return true;
	}

	public Departamento getDepartamento() {
		if (departamento == null) {
			departamento = new Departamento();
		}
		return departamento;
	}

	public void setUsuario(Departamento departamento) {
		this.departamento = departamento;
	}

	public void limpar() {
		departamento = null;
	}

	public List<SelectItem> getListaEstados() {
		if(listaEstados == null) {
			listaEstados = new ArrayList<SelectItem>();
			
			DAO<EstadoDepartamento> dao = new EstadoDepartamentoDAO();
			List<EstadoDepartamento> listaEstado = dao.findAll();
			
			if(listaEstado != null && !listaEstado.isEmpty()) {
				SelectItem item;
				  
				for (EstadoDepartamento estado : listaEstado) {
					item = new SelectItem(estado, estado.getEstado());
					listaEstados.add(item);
				}
			}
		}
		
		
		return listaEstados;
	}

	public EstadoDepartamento getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(EstadoDepartamento estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}
	
	public StatusDepartamento[] getListaStatusDepartamento() {
		return StatusDepartamento.values();
	}

	

	public List<SelectItem> getListaCidades() {
		if(listaCidades == null) {
			listaCidades = new ArrayList<SelectItem>();
			
			DAO<CidadeDepartamento> dao = new CidadeDepartamentoDAO();
			List<CidadeDepartamento> listaCidade = dao.findAll();
			
			if(listaCidade != null && !listaCidade.isEmpty()) {
				SelectItem item;
				  
				for (CidadeDepartamento cidade : listaCidade) {
					item = new SelectItem(cidade, cidade.getCidade());
					listaCidades.add(item);
				}
			}
		}
		
		return listaCidades;
	}

	public CidadeDepartamento getCidadeSelecionado() {
		return cidadeSelecionado;
	}

	public void setCidadeSelecionado(CidadeDepartamento cidadeSelecionado) {
		this.cidadeSelecionado = cidadeSelecionado;
	}




}
