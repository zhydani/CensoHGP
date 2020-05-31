package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.unitins.censohgp.model.Checklist;

public class ChecklistDAO extends DAO<Checklist> {

	public ChecklistDAO(Connection conn) {
		super(conn);
	}

	public ChecklistDAO() {
		super(null);
	}

	@Override
	public void create(Checklist checklist) throws SQLException {
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
				" checklist "+ 
				" (observacao, "+
				" idpaciente, "+
				" idusuario, "+
				" data_hora, "+
		        " VALUES " + 
				" (?, ?, ?, ?) ",
		        Statement.RETURN_GENERATED_KEYS);

		stat.setString(1, checklist.getObservacao());
		stat.setInt(2, checklist.getPaciente().getIdpaciente());
		stat.setInt(3, checklist.getUsuario().getId());
		Date date = Date.valueOf(checklist.getDataHora());
		stat.setDate(4, date);
		stat.execute();
		
		ResultSet rs = stat.getGeneratedKeys();
		Integer value = rs.getInt("id");
		rs.next();
		checklist.getIncidente().setIdincidente(value);
		IncidenteDAO incidente = new IncidenteDAO(conn);
		incidente.create(checklist.getIncidente());
		
		checklist.getFatorRisco().setIdfatorRisco(value);
		FatorRiscoDAO fatorRisco = new FatorRiscoDAO();
		fatorRisco.create(checklist.getFatorRisco());
		
		checklist.getProcedimento().setIdprocedimento(value);
		ProcedimentoDAO procedimento = new ProcedimentoDAO();
		procedimento.create(checklist.getProcedimento());
		
	}
	
	public void createChecklistFatorRisco(Checklist checklist) throws SQLException {

		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
				" checklist "+ 
				" (observacao, "+
				" idpaciente, "+
				" idusuario, "+
				" data_hora, "+
		        " VALUES " + 
				" (?, ?, ?, ?) ",
		        Statement.RETURN_GENERATED_KEYS);

		stat.setString(1, checklist.getObservacao());
		stat.setInt(2, checklist.getPaciente().getIdpaciente());
		stat.setInt(3, checklist.getUsuario().getId());
		Date date = Date.valueOf(checklist.getDataHora());
		stat.setDate(4, date);
		stat.execute();
		
		ResultSet rs = stat.getGeneratedKeys();
		Integer value = rs.getInt("id");
		rs.next();
		checklist.getFatorRisco().setIdfatorRisco(value);
		FatorRiscoDAO fatorRisco = new FatorRiscoDAO();
		fatorRisco.create(checklist.getFatorRisco());
		
		
	}

	public void createChecklistProcedimento(Checklist checklist) throws SQLException {

		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
				" checklist "+ 
				" (observacao, "+
				" idpaciente, "+
				" idusuario, "+
				" data_hora, "+
		        " VALUES " + 
				" (?, ?, ?, ?) ",
		        Statement.RETURN_GENERATED_KEYS);

		stat.setString(1, checklist.getObservacao());
		stat.setInt(2, checklist.getPaciente().getIdpaciente());
		stat.setInt(3, checklist.getUsuario().getId());
		Date date = Date.valueOf(checklist.getDataHora());
		stat.setDate(4, date);
		stat.execute();
		
		ResultSet rs = stat.getGeneratedKeys();
		Integer value = rs.getInt("id");
		rs.next();
		checklist.getProcedimento().setIdprocedimento(value);
		ProcedimentoDAO procedimento = new ProcedimentoDAO();
		procedimento.create(checklist.getProcedimento());
		
		
	}

	public void createChecklistIncidente(Checklist checklist) throws SQLException {

		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
				" checklist "+ 
				" (observacao, "+
				" idpaciente, "+
				" idusuario, "+
				" data_hora, "+
		        " VALUES " + 
				" (?, ?, ?, ?) ",
		        Statement.RETURN_GENERATED_KEYS);

		stat.setString(1, checklist.getObservacao());
		stat.setInt(2, checklist.getPaciente().getIdpaciente());
		stat.setInt(3, checklist.getUsuario().getId());
		Date date = Date.valueOf(checklist.getDataHora());
		stat.setDate(4, date);
		stat.execute();
		
		ResultSet rs = stat.getGeneratedKeys();
		Integer value = rs.getInt("id");
		rs.next();
		checklist.getIncidente().setIdincidente(value);
		IncidenteDAO incidente = new IncidenteDAO(conn);
		incidente.create(checklist.getIncidente());
		
	}


	@Override
	public List<Checklist> findAll() {
		return null;
	}

	@Override
	public void update(Checklist checklist) throws SQLException {

	}

	@Override
	public void delete(int id) throws SQLException {

	}



}
