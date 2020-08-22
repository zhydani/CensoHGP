package br.unitins.censohgp.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.unitins.censohgp.dao.CidadeDepartamentoDAO;

@Named
@ApplicationScoped
public class Cidades {
	
	private List<CidadeDepartamento> cidades;

	@PostConstruct
	public void init() {
		cidades = new ArrayList<CidadeDepartamento>();
		CidadeDepartamentoDAO dao = new CidadeDepartamentoDAO();
		cidades = dao.findAll();
		dao.closeConnection();
	}
	
	
	public List<CidadeDepartamento> getCidades() {
		init();
		return cidades;
	}

}