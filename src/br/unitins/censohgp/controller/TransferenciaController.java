package br.unitins.censohgp.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.unitins.censohgp.application.Session;
import br.unitins.censohgp.application.Util;
import br.unitins.censohgp.dao.DAO;
import br.unitins.censohgp.dao.DepartamentoDAO;
import br.unitins.censohgp.dao.PacienteDAO;
import br.unitins.censohgp.dao.TransferenciaDAO;
import br.unitins.censohgp.model.Departamento;
import br.unitins.censohgp.model.HistoricoTransferencia;
import br.unitins.censohgp.model.Paciente;
import br.unitins.censohgp.model.Pacientes;
import br.unitins.censohgp.model.Tipo;
import br.unitins.censohgp.model.TipoTransferencia;
import br.unitins.censohgp.model.Usuario;
@Named
@ViewScoped
public class TransferenciaController implements Serializable{

	private static final long serialVersionUID = -5540152887879084492L;
	private Paciente paciente;//usando para db
	private Usuario usuario;//usando para db
	private List<Departamento> departamento;//usando para db
	private Departamento departament;
	
	private List<String> listaTransferencia;
	private List<String> hospitais;
	private String hospital;
	private String tipoTransferencia;
	private String observasao;
    private List<String> nomeDepartamento;
    
    private String numeroProntuario;
    private List<Paciente> pacientes;
    private String naoexisteNomeDepartamento;
    
	@Inject
	private Pacientes pacientesParabusca;
    
	public TransferenciaController() {
		//Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		//flash.keep("pacienteFlash");
		//usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
		//paciente = (Paciente) flash.get("pacienteFlash");
		//setPaciente((Paciente) flash.get("pacienteFlash"));
		//setUsuario((Usuario) Session.getInstance().getAttribute("usuarioLogado"));
	}
//OK
	public void Atualizar() {
		setPaciente(null);
		setNumeroProntuario(null);
		pacientesParabusca.init();
	}
//OK
	public void trasnferir() {
		
		//!!!!!!!!!!!!!!!DANGER!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//quando implementar apagar esse campo!!!!!!!!!!!!!!!
		HistoricoTransferencia trans = new HistoricoTransferencia();
		DAO<HistoricoTransferencia> dao = new TransferenciaDAO();
		TransferenciaDAO dao3 = new TransferenciaDAO();
		PacienteDAO dao2 = new PacienteDAO();
		int aux =0;
		if(!(getHospital().isEmpty() || getDepartament().getNomeDepartamento().isEmpty()|| (getNumeroProntuario().isEmpty() && getPaciente().getNome() == null))) {
			//ele salva
			if(!(getNumeroProntuario().isEmpty()) && !(getPaciente().getNome() == null)) {
				if(getPaciente().getNumeroProntuario().equals(getNumeroProntuario())) {
					
					for (Departamento departamento2 : getDepartamento()) {
						if(getDepartament().getNomeDepartamento().equals("não definido") && departamento2.getNomeDepartamento().isEmpty() && 
								getHospital().equals(departamento2.getNomeHospital())) {
							trans.setIdTipoDeTransferencia(TipoTransferencia.valueId(getTipoTransferencia()));
							Departamento depaux = getPaciente().getIdlocalTransferencia();
							trans.setIdLocalOrigem(depaux.getIdlocalTransferencia());
							trans.setIdLocalDestino(departamento2.getIdlocalTransferencia());
							trans.setIdPaciente(getPaciente().getIdpaciente());
							//para observação
							//trans.setObservasao(getObservasao());
							trans.setIdUsuario(getUsuario().getId());
							trans.setObservasao(getObservasao());
							try {
								dao.create(trans);
								dao.getConnection().commit();
								dao2.updateTrasnferencia(departamento2.getIdlocalTransferencia(), getPaciente().getIdpaciente());
								dao2.getConnection().commit();
								Util.addMessageInfo("Operação realizada com sucesso.");
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								Util.addMessageError("não foi possivel inserir no banco de dados");
								e.printStackTrace();
							}
							
						}
						if(getDepartament().getNomeDepartamento().equals(departamento2.getNomeDepartamento()) && 
								getHospital().equals(departamento2.getNomeHospital())) {
							aux ++;
							//Pegar tipo de transferencia como id OK
							trans.setIdTipoDeTransferencia(TipoTransferencia.valueId(getTipoTransferencia()));
    							
							//idLocal de origem voce vai buscar da classe paciente
							Departamento depaux = getPaciente().getIdlocalTransferencia();
							trans.setIdLocalOrigem(depaux.getIdlocalTransferencia());
							
							
							//idLocalDestino voce vai pegar do id do departamento
							trans.setIdLocalDestino(departamento2.getIdlocalTransferencia());
							
							//idpaciente vai buscar do paciente
							trans.setIdPaciente(getPaciente().getIdpaciente());
							
							//idUsuario vai biscar do usuario
							trans.setIdUsuario(getUsuario().getId());
							trans.setObservasao(getObservasao());
							//para observação
							//trans.setObservasao(getObservasao());
							
							
							//setar novo valor de departamento no paciente
							//paciaux.setIdlocalTransferencia(departamento2);
							//setPaciente(paciaux);
							// tem q salvar no banco de dados o novo departamento e paciente
							try {
								dao.create(trans);
								dao.getConnection().commit();
								dao2.updateTrasnferencia(departamento2.getIdlocalTransferencia(), getPaciente().getIdpaciente());
								dao2.getConnection().commit();
								Util.addMessageInfo("Operação realizada com sucesso.");
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								Util.addMessageError("não foi possivel inserir no banco de dados");
								e.printStackTrace();
							}
							
							//Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash(); 
							//flash.put("pacienteFlash", getPaciente()); 
							//retono pra tela que me chamou antes dessa
						}
							
						
					}	
				}else {
					Util.addMessageError("Numero de prontuario incompativel com o paciente");
				}
			}
			if(!(getNumeroProntuario().isEmpty()) && getPaciente().getNome() == null) {
				for (Paciente paci : getPacientes()) {
					if(getNumeroProntuario().equals(paci.getNumeroProntuario())) {
							for (Departamento departamento2 : getDepartamento()) {
								if(getDepartament().getNomeDepartamento().equals("não definido") && departamento2.getNomeDepartamento().isEmpty() && 
										getHospital().equals(departamento2.getNomeHospital())) {
									aux =1;
									trans.setIdTipoDeTransferencia(TipoTransferencia.valueId(getTipoTransferencia()));
									Departamento depaux = paci.getIdlocalTransferencia();
									trans.setIdLocalOrigem(depaux.getIdlocalTransferencia());
									trans.setIdLocalDestino(departamento2.getIdlocalTransferencia());
									trans.setIdPaciente(paci.getIdpaciente());
									trans.setIdUsuario(getUsuario().getId());
									trans.setObservasao(getObservasao());
									//para observação
									//trans.setObservasao(getObservasao());
									try {
										dao.create(trans);
										dao.getConnection().commit();
										dao2.updateTrasnferencia(departamento2.getIdlocalTransferencia(),  paci.getIdpaciente());
										dao2.getConnection().commit();
										Util.addMessageInfo("Operação realizada com sucesso.");
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										Util.addMessageError("não foi possivel inserir no banco de dados");
										e.printStackTrace();
									}
									
								}
								if(getDepartament().getNomeDepartamento().equals(departamento2.getNomeDepartamento()) && 
										getHospital().equals(departamento2.getNomeHospital())) {
									aux =1;
									//Pegar tipo de transferencia como id OK
									trans.setIdTipoDeTransferencia(TipoTransferencia.valueId(getTipoTransferencia()));
		    							
									//idLocal de origem voce vai buscar da classe paciente
									Departamento depaux = paci.getIdlocalTransferencia();
									trans.setIdLocalOrigem(depaux.getIdlocalTransferencia());
									
									
									//idLocalDestino voce vai pegar do id do departamento
									trans.setIdLocalDestino(departamento2.getIdlocalTransferencia());
									
									//idpaciente vai buscar do paciente
									trans.setIdPaciente(paci.getIdpaciente());
									
									//idUsuario vai biscar do usuario
									trans.setIdUsuario(getUsuario().getId());
									trans.setObservasao(getObservasao());
									//para observação
									//trans.setObservasao(getObservasao());
									
									//setar novo valor de departamento no paciente
									//paciaux.setIdlocalTransferencia(departamento2);
									//setPaciente(paciaux);
									// tem q salvar no banco de dados o novo departamento e paciente
									try {
										dao.create(trans);
										dao.getConnection().commit();
										dao2.updateTrasnferencia(departamento2.getIdlocalTransferencia(), paci.getIdpaciente());
										dao2.getConnection().commit();
										Util.addMessageInfo("Operação realizada com sucesso.");
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										Util.addMessageError("não foi possivel inserir no db");
										e.printStackTrace();
									}
									
									//Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash(); 
									//flash.put("pacienteFlash", getPaciente()); 
									//retono pra tela que me chamou antes dessa
								}
							}
					}
				}if(aux!=1) {
					Util.addMessageError("Não foi encontrado um Paciente com esse codigo");
				}
			}
			if(!(getPaciente().getNome() == null) && getNumeroProntuario().isEmpty()) {
				//salvar pelo nome do paciente
				for (Departamento departamento2 : getDepartamento()) {
					if(getDepartament().getNomeDepartamento().equals("não definido") && departamento2.getNomeDepartamento().isEmpty() && 
							getHospital().equals(departamento2.getNomeHospital())) {
						trans.setIdTipoDeTransferencia(TipoTransferencia.valueId(getTipoTransferencia()));
						Departamento depaux = getPaciente().getIdlocalTransferencia();
						trans.setIdLocalOrigem(depaux.getIdlocalTransferencia());
						trans.setIdLocalDestino(departamento2.getIdlocalTransferencia());
						trans.setIdPaciente(getPaciente().getIdpaciente());
						trans.setIdUsuario(getUsuario().getId());
						trans.setObservasao(getObservasao());
						//para observação
						//trans.setObservasao(getObservasao());
						try {
							dao.create(trans);
							dao.getConnection().commit();
							dao2.updateTrasnferencia(departamento2.getIdlocalTransferencia(), getPaciente().getIdpaciente());
							dao2.getConnection().commit();
							Util.addMessageInfo("Operação realizada com sucesso.");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							Util.addMessageError("não foi possivel inserir no banco de dados");
							e.printStackTrace();
						}
						
					}

					if(getDepartament().getNomeDepartamento().equals(departamento2.getNomeDepartamento()) && 
							getHospital().equals(departamento2.getNomeHospital())) {
						aux ++;
						//Pegar tipo de transferencia como id OK
						trans.setIdTipoDeTransferencia(TipoTransferencia.valueId(getTipoTransferencia()));
						//idLocal de origem voce vai buscar da classe paciente
						Departamento depaux = getPaciente().getIdlocalTransferencia();
						trans.setIdLocalOrigem(depaux.getIdlocalTransferencia());
						
						//idLocalDestino voce vai pegar do id do departamento
						trans.setIdLocalDestino(departamento2.getIdlocalTransferencia());
						
						//idpaciente vai buscar do paciente
						trans.setIdPaciente(getPaciente().getIdpaciente());
						
						//idUsuario vai biscar do usuario
						trans.setIdUsuario(getUsuario().getId());
						trans.setObservasao(getObservasao());
						//para observação
						//trans.setObservasao(getObservasao());
						
						//setar novo valor de departamento no paciente
						//paciaux.setIdlocalTransferencia(departamento2);
						//setPaciente(paciaux);
						// tem q salvar no banco de dados o novo departamento e paciente
						try {
							dao3.create(trans);
							dao3.getConnection().commit();
							dao2.updateTrasnferencia(departamento2.getIdlocalTransferencia(), getPaciente().getIdpaciente());
							dao2.getConnection().commit();
							Util.addMessageInfo("Operação realizada com sucesso.");
						} catch (SQLException e) {
							Util.addMessageError("não foi possivel inserir no db");
							e.printStackTrace();
						}
						
						//Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash(); 
						//flash.put("pacienteFlash", getPaciente()); 
						//retono pra tela que me chamou antes dessa
					}
				}
				
			}
		}
		else {
			//ele não salva
			Util.addMessageError("Necessario preencher para transferir");
		}
	}
//OK	
	public List<Paciente> completeTheme(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Paciente> pacientes =  pacientesParabusca.getPacientes();
        return pacientes.stream().filter(t -> t.getNome().toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }	
//OK
	//Carregando do banco de dados
	public List<Departamento> getDepartamento() {
			DepartamentoDAO dao = new DepartamentoDAO();
			//DAO<Departamento> dao = new DepartamentoDAO();
			departamento = new ArrayList<Departamento>();
			departamento = dao.findAllTransferencia();
			if (departamento == null) {
				departamento = new ArrayList<Departamento>();
				System.out.println("dep ta nullo");
			}
		return departamento;
	}
	//Vinculado Objeto Carregado do DB para ser carregado no banco
	public Departamento getDepartament() {
		if(departament == null) {
			departament = new Departamento();
		}
		if(getHospital() != null) {
			for (Departamento departamento2 : getDepartamento()) {
				if(departament.getNomeDepartamento() == departamento2.getNomeDepartamento() && getHospital() == departamento2.getNomeHospital()) {
					setDepartament(departamento2);
				}
			}
			return departament;
		}
		System.out.println("ta saindo aq em departamento");
		return departament;
	}
	//Carregando nome dos hospitais 
	public List<String> getHospitais() {
		hospitais = new ArrayList<String>();
		if(getTipoTransferencia().equals("Interno")) {
			hospitais = new ArrayList<String>();
			String nomeHospital = "Hospital Geral de Palmas -HGP";
			hospitais.add(nomeHospital);
		}
		else if(getTipoTransferencia().equals("Externo")) {
			hospitais = new ArrayList<String>();
			List<String> hospitalaux = new ArrayList<String>();
			getDepartamento();
			for (Departamento dep : getDepartamento()) {
				if(!(dep.getNomeHospital().equals("Hospital Geral de Palmas -HGP")) && dep.getAtivo().getValue() == 1) {
					hospitais.add(dep.getNomeHospital());
				}
			}
			for (String string : hospitais) {
				if(hospitalaux.isEmpty()) {
					hospitalaux.add(string);
				}
				else if(!hospitalaux.contains(string)) {
					hospitalaux.add(string);
				}
			}
			return hospitalaux;
		}
		//System.out.println(hospital);
		return hospitais;
	}
	public String getHospital() {
		if(hospital == null) {
			hospital = new String();
		}
		if(hospital.isEmpty()) {
			hospital = new String();
		}
		return hospital;
	}
	public String getTipoTransferencia() {
		if(tipoTransferencia == null) {
			tipoTransferencia = new String();
			return tipoTransferencia;
		}
		return tipoTransferencia;
	}
	public List<String> getListaTransferencia() {
		if(listaTransferencia == null) {
			listaTransferencia = new ArrayList<String>();
			int x = TipoTransferencia.values().length + 1;
			for (int i = 1; i < x; i++) {
				listaTransferencia.add(TipoTransferencia.valueOf(i));
			}
			if(listaTransferencia == null) {
				Util.addMessageError("acho foi porra dessa bagaça ai");
				System.out.println("eesta vazia nao populada");
			}
		}
		return listaTransferencia;
	}
	//questão de departamento nullo
	public List<String> getNomeDepartamento() {
		List<String> aux = new ArrayList<String>();
		nomeDepartamento = new ArrayList<String>();
		if(getTipoTransferencia().equals("Interno") && getHospital().equals("Hospital Geral de Palmas -HGP")) {
			aux = new ArrayList<String>();
			for (Departamento departamento2 : getDepartamento()) {
				if(departamento2.getNomeHospital().equals("Hospital Geral de Palmas -HGP") && departamento2.getAtivo().getValue() == 1) {
					aux.add(departamento2.getNomeDepartamento());
				}
			}
			return aux;
		}
		else if(getTipoTransferencia().equals("Externo") && getHospital() != null && !(getHospital().equals("Hospital Geral de Palmas -HGP"))) {
			aux = new ArrayList<String>();
			naoexisteNomeDepartamento = "";
			for (Departamento departamento2 : getDepartamento()) {
				if(departamento2.getNomeHospital().equals(getHospital()) && departamento2.getAtivo().getValue() == 1) {
					if(!departamento2.getNomeDepartamento().isEmpty()) {
						aux.add(departamento2.getNomeDepartamento());
					}
					else if(getNaoexisteNomeDepartamento().isEmpty() && departamento2.getNomeDepartamento().isEmpty()) {
						naoexisteNomeDepartamento = "não definido";
						aux.add(naoexisteNomeDepartamento);
					}
				}
			}
			if(aux.isEmpty()) {
				nomeDepartamento = null;
				return nomeDepartamento;
			}
			return aux;
		}
		return nomeDepartamento;
	}

	public List<Paciente> getPacientes() {
		if(pacientes == null) {
			PacienteDAO dao = new PacienteDAO();
			pacientes = dao.findAllParaTransferencia();
			if(pacientes == null)
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
	
	public void setDepartament(Departamento departament) {
		this.departament = departament;
	}

	public void setTipoTransferencia(String tipoTransferencia) {
		this.tipoTransferencia = tipoTransferencia;
	}

	public Paciente getPaciente() {
		if(paciente == null) {
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
		if(observasao == null) {
			observasao = new String();
		}
		return observasao;
	}

	public void setObservasao(String observasao) {
		this.observasao = observasao;
	}

	public void setNomeDepartamento(List<String> nomeDepartamento) {
		this.nomeDepartamento = nomeDepartamento;
	}
	public String getNumeroProntuario() {
		if(numeroProntuario == null) {
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
