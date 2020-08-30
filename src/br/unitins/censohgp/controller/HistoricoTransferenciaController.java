package br.unitins.censohgp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.censohgp.application.Session;
import br.unitins.censohgp.dao.DepartamentoDAO;
import br.unitins.censohgp.dao.PacienteDAO;
import br.unitins.censohgp.dao.TransferenciaDAO;
import br.unitins.censohgp.dao.UsuarioDAO;
import br.unitins.censohgp.model.Departamento;
import br.unitins.censohgp.model.HistoricoTransferencia;
import br.unitins.censohgp.model.HistoricoTransferenciaNomes;
import br.unitins.censohgp.model.Paciente;
import br.unitins.censohgp.model.TipoTransferencia;
import br.unitins.censohgp.model.Usuario;

@Named
@ViewScoped
public class HistoricoTransferenciaController implements Serializable  {
	private static final long serialVersionUID = 7808836461736792592L;
	private List<HistoricoTransferencia> trans;
	private List<HistoricoTransferenciaNomes> transNomes;
	private Paciente paciente;
	private Usuario usuario;
	
	public HistoricoTransferenciaController() {
		 Flash flash =FacesContext.getCurrentInstance().getExternalContext().getFlash();
		 flash.keep("pacienteFlash");
		 setPaciente((Paciente) flash.get("pacienteFlash"));
		 carregar();
	}

	public void carregar() {
		TransferenciaDAO dao = new TransferenciaDAO();
		setTrans(dao.findAll());
		for (HistoricoTransferencia historicoTransferencia : getTrans()) {
			HistoricoTransferenciaNomes aux1 = new HistoricoTransferenciaNomes();
			DepartamentoDAO  dao1 = new DepartamentoDAO();
			PacienteDAO dao2 = new PacienteDAO();
			UsuarioDAO dao3 = new UsuarioDAO();
			Departamento aux2 = new Departamento();
			Paciente aux3 = new Paciente();
			Usuario aux4 = new Usuario();
			if(getPaciente().getIdpaciente().equals(historicoTransferencia.getIdPaciente())) {
				if(historicoTransferencia.getIdLocalOrigem().equals(0)) {
					aux3 = dao2.findById(historicoTransferencia.getIdPaciente());
					aux1.setNomePaciente(aux3.getNome());
					
					int i = historicoTransferencia.getIdTipoDeTransferencia();
					aux1.setTipoDeTransferencia(TipoTransferencia.valueOf(i));
					
					
					aux2 =dao1.findById2(historicoTransferencia.getIdLocalDestino());
					if(aux2.getNomeDepartamento() == null) {
						aux1.setLocalDeDestino(aux2.getNomeHospital());
					}else {
						aux1.setLocalDeDestino(aux2.getNomeDepartamento());
					}
					
					aux1.setLocalDeOrigem("Não cadastrado");
					
					aux1.setDataHora(historicoTransferencia.getDataHora());
					System.out.println(aux1.getDataHora()+"sfj");
					aux4 = dao3.findId(historicoTransferencia.getIdUsuario());
					aux1.setNomeUsuario(aux4.getNome());
					
					aux1.setObservasao(historicoTransferencia.getObservasao());
					getTransNomes().add(aux1);
				}else {
					System.out.println(historicoTransferencia.getIdTipoDeTransferencia());
					aux3 = dao2.findById(historicoTransferencia.getIdPaciente());
					aux1.setNomePaciente(aux3.getNome());
					
					int i = historicoTransferencia.getIdTipoDeTransferencia();
					aux1.setTipoDeTransferencia(TipoTransferencia.valueOf(i));
					
					
					aux2 =dao1.findById2(historicoTransferencia.getIdLocalDestino());
					if(aux2.getNomeDepartamento() == null) {
						aux1.setLocalDeDestino(aux2.getNomeHospital());
					}else {
						aux1.setLocalDeDestino(aux2.getNomeDepartamento());
					}
					
					aux2 = new Departamento();
					
					aux2 =dao1.findById2(historicoTransferencia.getIdLocalOrigem());
					if(aux2.getNomeDepartamento() == null) {
						aux1.setLocalDeOrigem(aux2.getNomeHospital());
					}else {
						aux1.setLocalDeOrigem(aux2.getNomeDepartamento());
					}
					
					aux1.setDataHora(historicoTransferencia.getDataHora());
					
					aux4 = dao3.findId(historicoTransferencia.getIdUsuario());
					aux1.setNomeUsuario(aux4.getNome());
					
					aux1.setObservasao(historicoTransferencia.getObservasao());
					getTransNomes().add(aux1);
				}
			}
		}
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<HistoricoTransferencia> getTrans() {
		if(trans == null) {
			trans = new ArrayList<HistoricoTransferencia>();
		}
		return trans;
	}

	public void setTrans(List<HistoricoTransferencia> trans) {
		this.trans = trans;
	}

	public List<HistoricoTransferenciaNomes> getTransNomes() {
		if(transNomes == null) {
			transNomes = new ArrayList<HistoricoTransferenciaNomes>();
		}
		return transNomes;
	}

	public void setTransNomes(List<HistoricoTransferenciaNomes> transNomes) {
		this.transNomes = transNomes;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	
}
