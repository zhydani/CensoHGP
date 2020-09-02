package br.unitins.censohgp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.unitins.censohgp.application.Session;
import br.unitins.censohgp.application.Util;
import br.unitins.censohgp.dao.DepartamentoDAO;
import br.unitins.censohgp.dao.PacienteDAO;
import br.unitins.censohgp.model.Departamento;
import br.unitins.censohgp.model.Paciente;
import br.unitins.censohgp.model.Pacientes;
import br.unitins.censohgp.model.Tipo;
import br.unitins.censohgp.model.TipoTransferencia;
import br.unitins.censohgp.model.TipoUsuario;
import br.unitins.censohgp.model.Usuario;

@Named
@ViewScoped
public class TransferenciaController implements Serializable {

	private static final long serialVersionUID = -5540152887879084492L;
	private Paciente paciente;// usando para db
	private Usuario usuario;// usando para db
	private List<Departamento> departamento;// usando para db

	private List<String> listaTransferencia;
	private List<String> hospitais;
	private String hospital;
	private String tipoTransferencia;
	private String observasao;

	private String numeroProntuario;
	private List<Paciente> pacientes;
	private String naoexisteNomeDepartamento;

	@Inject
	private Pacientes pacientesParabusca;

	public TransferenciaController() {
		// Flash flash =
		// FacesContext.getCurrentInstance().getExternalContext().getFlash();
		// flash.keep("pacienteFlash");
		// usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
		// paciente = (Paciente) flash.get("pacienteFlash");
		// setPaciente((Paciente) flash.get("pacienteFlash"));
		setUsuario((Usuario) Session.getInstance().getAttribute("usuarioLogado"));
	}

//OK
	public void Atualizar() {
		setPaciente(null);
		setNumeroProntuario(null);
		pacientesParabusca.init();
	}

//OK
	public void trasnferir() {
		AuxiliarTransferenciaController aux100 = new AuxiliarTransferenciaController();
		String auxObservasao = new String();
		auxObservasao = getObservasao();
		int aux = 0;
		// para verificar se os campos estão vazios
		if (!(getHospital().isEmpty() || (getNumeroProntuario().isEmpty() && getPaciente().getNome() == null))) {
			// ele salva
			// Verifica se o campo numeroProntuario e o nome do paciente estão vazio
			if (!(getNumeroProntuario().isEmpty()) && !(getPaciente().getNome() == null)) {
				if (getPaciente().getNumeroProntuario().equals(getNumeroProntuario())) {
					for (Departamento departamento2 : getDepartamento()) {
						if (getHospital().equals(departamento2.getNomeDepartamento())
								|| getHospital().equals(departamento2.getNomeHospital())) {
							aux++;
							aux100.transferir(TipoTransferencia.valueId(getTipoTransferencia()),
									getPaciente().getIdlocalTransferencia(), departamento2.getIdlocalTransferencia(),
									getPaciente().getIdpaciente(), getUsuario().getId(), auxObservasao);
							return;
						}
					}
				} else {
					Util.addMessageError("Numero de prontuario incompativel com o paciente");
				}
			}
			// ele salva
			// Verifica se o campo numeroProntuario está preenchido e o nome do paciente
			// esta vazio
			if (!(getNumeroProntuario().isEmpty()) && getPaciente().getNome() == null) {
				for (Paciente paci : getPacientes()) {
					if (getNumeroProntuario().equals(paci.getNumeroProntuario())) {
						for (Departamento departamento2 : getDepartamento()) {
							if (getHospital().equals(departamento2.getNomeDepartamento())
									|| getHospital().equals(departamento2.getNomeHospital())) {
								aux = 1;
								aux100.transferir(TipoTransferencia.valueId(getTipoTransferencia()),
										paci.getIdlocalTransferencia(), departamento2.getIdlocalTransferencia(),
										paci.getIdpaciente(), getUsuario().getId(), auxObservasao);
							}
						}
					}
				}
				if (aux != 1) {
					Util.addMessageError("Não foi encontrado um Paciente com esse codigo");
				}
			}
			// ele salva
			// Verifica se o campo nome está preenchido e o numeroProntuario do paciente
			// esta vazio
			if (!(getPaciente().getNome() == null) && getNumeroProntuario().isEmpty()) {
				for (Departamento departamento2 : getDepartamento()) {
					if (getHospital().equals(departamento2.getNomeDepartamento())
							|| getHospital().equals(departamento2.getNomeHospital())) {
						aux++;
						aux100.transferir(TipoTransferencia.valueId(getTipoTransferencia()),
								getPaciente().getIdlocalTransferencia(), departamento2.getIdlocalTransferencia(),
								getPaciente().getIdpaciente(), getUsuario().getId(), auxObservasao);
					}
				}
			}
		} else {
			// ele não salva
			Util.addMessageError("Necessario preencher para transferir");
		}
	}

//OK	
	public List<Paciente> completeTheme(String query) {
		if (pacientesParabusca.getPacientes() != null) {
			String queryLowerCase = query.toLowerCase();
			List<Paciente> pacientes = pacientesParabusca.getPacientes();
			return pacientes.stream().filter(t -> t.getNome().toLowerCase().startsWith(queryLowerCase))
					.collect(Collectors.toList());
		}
		return getPacientes();
	}

//OK
	// Carregando do banco de dados
	public List<Departamento> getDepartamento() {
		DepartamentoDAO dao = new DepartamentoDAO();
		// DAO<Departamento> dao = new DepartamentoDAO();
		departamento = new ArrayList<Departamento>();
		departamento = dao.findAllTransferencia();
		if (departamento == null) {
			departamento = new ArrayList<Departamento>();
		}
		return departamento;
	}

	// Carregando nome dos hospitais/departamentos
	public List<String> getHospitais() {
		// dep.getNomeDepartamento().isBlank() ||
		hospitais = new ArrayList<String>();
		List<String> hospitalaux = new ArrayList<String>();
		if (getTipoTransferencia().equals("Interno")) {
			hospitais = new ArrayList<String>();
			for (Departamento dep : getDepartamento()) {
				if (!(dep.getNomeDepartamento().isBlank() || dep.getNomeDepartamento().isEmpty())
						&& dep.getAtivo().getValue() == 0) {
					hospitais.add(dep.getNomeDepartamento());
				}
			}
			for (String string : hospitais) {
				if (hospitalaux.isEmpty()) {
					hospitalaux.add(string);
				}
				if (!hospitalaux.contains(string)) {
					hospitalaux.add(string);
				}
			}
			return hospitalaux;
		} else if (getTipoTransferencia().equals("Externo")) {
			hospitais = new ArrayList<String>();
			getDepartamento();
			for (Departamento dep : getDepartamento()) {
				if ((dep.getNomeDepartamento().isBlank() || dep.getNomeDepartamento().isEmpty())
						&& dep.getAtivo().getValue() == 0) {
					hospitais.add(dep.getNomeHospital());
				}
			}
			for (String string : hospitais) {
				if (hospitalaux.isEmpty()) {
					hospitalaux.add(string);
				}
				if (!hospitalaux.contains(string)) {
					hospitalaux.add(string);
				}
			}
			return hospitalaux;
		}
		return hospitais;
	}

	public String getHospital() {
		if (hospital == null) {
			hospital = new String();
		}
		if (hospital.isEmpty()) {
			hospital = new String();
		}
		return hospital;
	}

	public String getTipoTransferencia() {
		if (tipoTransferencia == null) {
			tipoTransferencia = new String();
			return tipoTransferencia;
		}
		return tipoTransferencia;
	}

	public List<String> getListaTransferencia() {
		if (listaTransferencia == null) {
			listaTransferencia = new ArrayList<String>();
			int x = TipoTransferencia.values().length + 1;
			for (int i = 1; i < x; i++) {
				listaTransferencia.add(TipoTransferencia.valueOf(i));
			}
			if (listaTransferencia == null) {
				Util.addMessageError("acho foi porra dessa bagaça ai");
				System.out.println("eesta vazia nao populada");
			}
		}
		return listaTransferencia;
	}

	public List<Paciente> getPacientes() {
		if (pacientes == null) {
			pacientes = new ArrayList<Paciente>();
			PacienteDAO dao = new PacienteDAO();
			pacientes = dao.findAllParaTransferencia();
			if (pacientes == null)
				pacientes = new ArrayList<Paciente>();
		}
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public void setHospitais(List<String> hospitais) {
		this.hospitais = hospitais;
	}

	public void setTipoTransferencia(String tipoTransferencia) {
		this.tipoTransferencia = tipoTransferencia;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setDepartamento(List<Departamento> departamento) {
		this.departamento = departamento;
	}

	public void setListaTransferencia(List<String> listaTransferencia) {
		this.listaTransferencia = listaTransferencia;
	}

	public String getObservasao() {
		if (observasao == null) {
			observasao = new String();
		}
		return observasao;
	}

	public void setObservasao(String observasao) {
		this.observasao = observasao;
	}

	public String getNumeroProntuario() {
		if (numeroProntuario == null) {
			numeroProntuario = new String();
		}
		return numeroProntuario;
	}

	public void setNumeroProntuario(String numeroProntuario) {
		this.numeroProntuario = numeroProntuario;
	}

	public String getNaoexisteNomeDepartamento() {
		return naoexisteNomeDepartamento;
	}

	public void setNaoexisteNomeDepartamento(String naoexisteNomeDepartamento) {
		this.naoexisteNomeDepartamento = naoexisteNomeDepartamento;
	}

}
