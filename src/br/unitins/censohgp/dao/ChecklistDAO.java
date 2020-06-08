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

		
	}
	
	public void createAuxFatorRisco(int id, List<Checklist> checklists) throws SQLException {

		Connection  conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " + 
						" checklist_fator_risco " +
						" ( idchecklist, idfator_risco ) " +
						" VALUES " +
						" (? , ?) ", Statement.RETURN_GENERATED_KEYS);

		for (Checklist checklist : checklists) {

			stat.setInt(1, id );
			stat.setInt(2, checklist.getFatorRisco().getIdfatorRisco());			
			stat.execute();
		}

	}
	
	public void createAuxIncidente(int id, List<Checklist> checklists) throws SQLException {

		Connection  conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " + 
						" checklist_incidente " +
						" ( idchecklist, idincidente ) " +
						" VALUES " +
						" (? , ?) ", Statement.RETURN_GENERATED_KEYS);

		for (Checklist checklist : checklists) {

			stat.setInt(1, id );
			stat.setInt(2, checklist.getIncidente().getIdincidente());			
			stat.execute();
		}

	}
	
	public void createAuxProcedimento(int id, List<Checklist> checklists) throws SQLException {

		Connection  conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " + 
						" checklist_ " +
						" ( idchecklist, idprocedimento ) " +
						" VALUES " +
						" (? , ?) ", Statement.RETURN_GENERATED_KEYS);

		for (Checklist checklist : checklists) {

			stat.setInt(1, id );
			stat.setInt(2, checklist.getProcedimento().getIdprocedimento());			
			stat.execute();
		}

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
