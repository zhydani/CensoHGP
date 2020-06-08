package br.unitins.censohgp.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.unitins.censohgp.dao.PacienteDAO;

@Named
@ApplicationScoped
public class Pacientes {
	
	private List<Paciente> pacientes;

	@PostConstruct
	public void init() {
		pacientes = new ArrayList<Paciente>();
		PacienteDAO dao = new PacienteDAO();
		pacientes = dao.findAllParaTransferencia();
		dao.closeConnection();
	}
	
	
	public List<Paciente> getPacientes() {
		init();
		return pacientes;
	}
	
	

}
