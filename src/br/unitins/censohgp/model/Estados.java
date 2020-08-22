package br.unitins.censohgp.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.unitins.censohgp.dao.EstadoDepartamentoDAO;

@Named
@ApplicationScoped
public class Estados {

	private List<EstadoDepartamento> estados;

	@PostConstruct
	public void init() {
		estados = new ArrayList<EstadoDepartamento>();
		EstadoDepartamentoDAO dao = new EstadoDepartamentoDAO();
		estados = dao.findAll();
		dao.closeConnection();
	}
	
	
	public List<EstadoDepartamento> getEstados() {
		init();
		return estados;
	}
	
}
