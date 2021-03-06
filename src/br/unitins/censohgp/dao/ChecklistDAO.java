package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import br.unitins.censohgp.model.Checklist;
import br.unitins.censohgp.model.FatorRisco;
import br.unitins.censohgp.model.Incidente;
import br.unitins.censohgp.model.Paciente;
import br.unitins.censohgp.model.Procedimento;
import br.unitins.censohgp.model.Usuario;

public class ChecklistDAO extends DAO<Checklist> {

	public ChecklistDAO(Connection conn) {
		super(conn);
	}

	public ChecklistDAO() {
		super(null);
	}

	@Override
	public void create(Checklist checklist) throws SQLException {
		int key = 0;
		Connection conn = getConnection();
		conn.setAutoCommit(false);// n�o pode dar commit enquanto n�o finalizar todas as inser��es
		try {

			PreparedStatement stat = conn.prepareStatement(
					" INSERT INTO public.checklist " + 
					" (observacao, idpaciente, idusuario, data_hora) " + 
					"	VALUES ( ? , ? , ? , current_timestamp); ",
					Statement.RETURN_GENERATED_KEYS);
			stat.setString(1, checklist.getObservacao());
			stat.setInt(2, checklist.getPaciente().getIdpaciente());
			stat.setInt(3, checklist.getUsuario().getId());
			stat.execute();
			ResultSet rs = stat.getGeneratedKeys();
			if (rs.next()) {
				key = rs.getInt(1);
			}
			createAuxProcedimento(key, checklist.getProcedimentos());
			createAuxIncidente(key, checklist.getIncidentes());
			createAuxFatoresRisco(key, checklist.getFatoresRisco());
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void createAuxProcedimento(int id, List<Procedimento> procedimentos) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
				" checklist_procedimento "	+
				" ( idchecklist, idprocedimento ) " + 
				" VALUES " + 
				" (? , ?) "
		, Statement.RETURN_GENERATED_KEYS);
		for (Procedimento procedimento : procedimentos) {
			stat.setInt(1, id);
			stat.setInt(2, procedimento.getIdprocedimento());
			stat.execute();
		}
	}

	public void createAuxIncidente(int id, List<Incidente> incidentes) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement stat = conn.prepareStatement(
					"INSERT INTO " +
					" checklist_incidente " +
					" ( idchecklist, idincidente ) " +
					" VALUES " +
					" (? , ?) ",
				Statement.RETURN_GENERATED_KEYS);
		for (Incidente incidente : incidentes) {
			stat.setInt(1, id);
			stat.setInt(2, incidente.getIdincidente());
			stat.execute();
		}
	}

	public void createAuxFatoresRisco(int id, List<FatorRisco> fatoresrisco) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement stat = conn.prepareStatement(
					"INSERT INTO " +
					" checklist_fator_risco " +
					" ( idchecklist, idfator_risco ) " +
					" VALUES " +
					" (? , ?) ",
					Statement.RETURN_GENERATED_KEYS);
		for (FatorRisco fatorrisco : fatoresrisco) {
			stat.setInt(1, id);
			stat.setInt(2, fatorrisco.getIdfatorRisco());
			stat.execute();
		}
	}

	public List<Checklist> findByIdPaciente(Integer idPaciente) {
		Connection conn = getConnection();
		if (conn == null)
			return null;
		try {
			PreparedStatement stat = conn.prepareStatement("SELECT " +
					" c.idchecklist, " +
					" c.observacao,"	+
					" c.idusuario," + 
					" c.data_hora," +
					" c.idpaciente," +
					" u.idusuario as usuario," + 
					" u.nome,"+ 
					"p.idpaciente as paciente"+
					" FROM " + " public.checklist c," + 
					"  public.usuario u, "+"public.paciente p" +
					"  WHERE c.idpaciente = ?"	+ 
					" AND c.idusuario = u.idusuario" + 
					" AND c.idpaciente = p.idpaciente order by data_hora desc");
			stat.setInt(1, idPaciente);
			List<Checklist> listaChecklist = new ArrayList<Checklist>();
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Checklist checklist = new Checklist();
				checklist.setIdchecklist(rs.getInt("idchecklist"));
				checklist.setObservacao(rs.getString("observacao"));
				DateFormat transforma= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
				checklist.setData_hora(transforma.format(rs.getTimestamp("data_hora")));
				if (checklist.getPaciente() == null)
					checklist.setPaciente(new Paciente());
				checklist.getPaciente().setIdpaciente(rs.getInt("paciente"));
				if (checklist.getUsuario() == null)
					checklist.setUsuario(new Usuario());
				checklist.getUsuario().setId(rs.getInt("usuario"));
				checklist.getUsuario().setNome(rs.getString("nome"));
				listaChecklist.add(checklist);
			}
			if (listaChecklist.isEmpty())
				return null;
			return listaChecklist;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Checklist findById(Integer idPaciente) {
		Connection conn = getConnection();
		if (conn == null)
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					" idchecklist, " +
					" observacao,"	+
					" idusuario," + 
					" data_hora," +
					" idpaciente" +
					" FROM " +
					" public.checklist" +
					"  WHERE idpaciente = ?");
			stat.setInt(1, idPaciente);
			Checklist checklist = new Checklist();
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				checklist.setIdchecklist(rs.getInt("idchecklist"));
				checklist.setObservacao(rs.getString("observacao"));
				DateFormat transforma= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
				checklist.setData_hora(transforma.format(rs.getTimestamp("data_hora")));
				if (checklist.getPaciente() == null)
					checklist.setPaciente(new Paciente());
				checklist.getPaciente().setIdpaciente(rs.getInt("idpaciente"));
			}
			return checklist;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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