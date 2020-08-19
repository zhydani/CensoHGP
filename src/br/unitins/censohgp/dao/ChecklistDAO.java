package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.Checklist;
import br.unitins.censohgp.model.Precaucao;
import br.unitins.censohgp.model.Procedimento;

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

		PreparedStatement stat = conn.prepareStatement("INSERT INTO " + " public.checklist "
				+ " ( observacao, idpaciente, idusuario, data_hora) " + " VALUES " + " (?,?,?,?) ",
				Statement.RETURN_GENERATED_KEYS);

		stat.setString(1, checklist.getObservacao());
		stat.setInt(2, checklist.getPaciente().getIdpaciente());
		stat.setInt(3, checklist.getUsuario().getId());
		LocalDate dateNow = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(),
				LocalDate.now().getDayOfMonth());
		Date date = Date.valueOf(dateNow);
		stat.setDate(4, date);
		stat.execute();
		ResultSet rs = stat.getGeneratedKeys();
		if (rs.next()) {
			//iury, verifica depois se essse 1 aqui precisa ser id ou se pode ser so 1 mesmo
			key = rs.getInt(1);
		}
		createAuxProcedimento(key, checklist.getProcedimentos());

	}

	public void createAuxProcedimento(int id, List<Procedimento> procedimentos) throws SQLException {

		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement("INSERT INTO " + " checklist_procedimento "
				+ " ( idchecklist, idprocedimento ) " + " VALUES " + " (? , ?) ", Statement.RETURN_GENERATED_KEYS);

		for (Procedimento procedimento : procedimentos) {

			stat.setInt(1, id);
			stat.setInt(2, procedimento.getIdprocedimento());
			stat.execute();
		}

	}

	@Override
	public List<Checklist> findAll() {
		Connection conn = getConnection();
		if (conn == null)
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement("SELECT " + " idchecklist, " + "observacao," + " idpaciente,"
					+ " idusuario," + " data_hora " + "FROM " + " checklist ");

			ResultSet rs = stat.executeQuery();

			List<Checklist> listaChecklist = new ArrayList<Checklist>();

			while (rs.next()) {
				Checklist checklist = new Checklist();
				checklist.getPaciente().setIdpaciente(rs.getInt("idpaciente"));
				checklist.setObservacao(rs.getString("observacao"));
				checklist.setIdchecklist(rs.getInt("idchecklist"));

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

	@Override
	public void update(Checklist checklist) throws SQLException {

	}

	@Override
	public void delete(int id) throws SQLException {

	}

}
