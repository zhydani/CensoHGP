package br.unitins.censohgp.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.unitins.censohgp.dao.DepartamentoDAO;

@Named
@ApplicationScoped
public class Departamentos {
	
	private List<Departamento> departamentos;

	@PostConstruct
	public void init() {
		departamentos = new ArrayList<Departamento>();
		DepartamentoDAO dao = new DepartamentoDAO();
		departamentos = dao.findAll();
		dao.closeConnection();
	}
	
	
	public List<Departamento> getDepartamentos() {
		init();
		return departamentos;
	}

}
