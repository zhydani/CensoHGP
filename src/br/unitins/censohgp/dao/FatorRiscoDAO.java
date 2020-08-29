package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.FatorRisco;


public class FatorRiscoDAO extends DAO<FatorRisco> {
	
	public List<FatorRisco> findByIdChecklist(Integer idChecklist) {
		Connection conn = getConnection();
		if (conn == null)
			return null;
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					" f.idfator_risco, " +
					" c.nome " +
					" FROM  public.fator_risco c, " + 
					" public.checklist_fator_risco f " +
					" WHERE f.idchecklist = ? " +
					" AND c.idfator_risco = f.idfator_risco ");
			stat.setInt(1, idChecklist);
			List<FatorRisco> listaFatorRisco = new ArrayList<FatorRisco>();
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				FatorRisco fatorRisco = new FatorRisco();
				fatorRisco.setIdfatorRisco(rs.getInt("idfator_risco"));
				fatorRisco.setNome(rs.getString("nome"));
				listaFatorRisco.add(fatorRisco);
			}
			if (listaFatorRisco.isEmpty())
				return null;
			return listaFatorRisco;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<FatorRisco> findAll() {
		Connection conn = getConnection();
		if (conn == null)
			return null;
		try {
			PreparedStatement stat = conn
					.prepareStatement(
							"SELECT " + 
							"  idfator_risco, " +
							"  nome " +
							"FROM " +
							"  public.fator_risco");
			ResultSet rs = stat.executeQuery();
			List<FatorRisco> listaFatorRisco = new ArrayList<FatorRisco>();
			while (rs.next()) {
				FatorRisco fatorrisco = new FatorRisco();
				fatorrisco.setIdfatorRisco(rs.getInt("idfator_risco"));
				fatorrisco.setNome(rs.getString("nome"));
				listaFatorRisco.add(fatorrisco);
			}
			if (listaFatorRisco.isEmpty())
				return null;
			return listaFatorRisco;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public FatorRiscoDAO(Connection conn) {
		super(conn);
	}

	public FatorRiscoDAO() {
		super(null);
	}

	@Override
	public void delete(int id) throws SQLException {
	}

	@Override
	public void create(FatorRisco entity) throws SQLException {
	}

	@Override
	public void update(FatorRisco entity) throws SQLException {
	}
}