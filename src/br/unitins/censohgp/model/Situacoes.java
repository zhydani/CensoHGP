package br.unitins.censohgp.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.unitins.censohgp.dao.SituacaoDAO;

@Named
@ApplicationScoped
public class Situacoes {
	
	private List<Situacao> situacoes;

	@PostConstruct
	public void init() {
		situacoes = new ArrayList<Situacao>();
		SituacaoDAO dao = new SituacaoDAO();
		situacoes = dao.findAll();
		dao.closeConnection();
	}
	
	
	public List<Situacao> getSituacoes() {
		init();
		return situacoes;
	}

}
