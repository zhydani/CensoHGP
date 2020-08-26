package br.unitins.censohgp.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.unitins.censohgp.dao.SexoDAO;

@Named
@ApplicationScoped
public class Sexos {

	private List<Sexo> sexos;

	@PostConstruct
	public void init() {
		sexos = new ArrayList<Sexo>();
		SexoDAO dao = new SexoDAO();
		sexos = dao.findAll();
		dao.closeConnection();
	}

	public List<Sexo> getSexos() {
		init();
		return sexos;
	}

}