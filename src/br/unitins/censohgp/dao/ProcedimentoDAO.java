package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.Procedimento;

public class ProcedimentoDAO extends DAO<Procedimento> {

	public List<Procedimento> findByIdChecklist(Integer idChecklist) {
		Connection conn = getConnection();
		if (conn == null)
			return null;
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					" c.idprocedimento, " +
					" p.nome " +
					" FROM  public.checklist_procedimento c, " + 
					" public.procedimento p " +
					" WHERE c.idchecklist = ? " +
					" AND p.idprocedimento = c.idprocedimento ");
			stat.setInt(1, idChecklist);
			List<Procedimento> listaProcedimento = new ArrayList<Procedimento>();
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Procedimento procedimento = new Procedimento();
				procedimento.setIdprocedimento(rs.getInt("idprocedimento"));
				procedimento.setNome(rs.getString("nome"));
				listaProcedimento.add(procedimento);
			}
			if (listaProcedimento.isEmpty())
				return null;
			return listaProcedimento;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Procedimento> findAll() {
		Connection conn = getConnection();
		if (conn == null)
			return null;
		try {
			PreparedStatement stat = conn
					.prepareStatement(
							"SELECT " +
							"  idprocedimento, " +
							"  nome " +
							"FROM " +
							"  public.procedimento");
			ResultSet rs = stat.executeQuery();
			List<Procedimento> listaProcedimento = new ArrayList<Procedimento>();
			while (rs.next()) {
				Procedimento procedimento = new Procedimento();
				procedimento.setIdprocedimento(rs.getInt("idprocedimento"));
				procedimento.setNome(rs.getString("nome"));
				listaProcedimento.add(procedimento);
			}
			if (listaProcedimento.isEmpty())
				return null;
			return listaProcedimento;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ProcedimentoDAO(Connection conn) {
		super(conn);
	}

	public ProcedimentoDAO() {
		super(null);
	}

	@Override
	public void delete(int id) throws SQLException {
	}

	@Override
	public void create(Procedimento entity) throws SQLException {
	}

	@Override
	public void update(Procedimento entity) throws SQLException {
	}
}