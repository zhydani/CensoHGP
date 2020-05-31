package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.FatorRisco;

public class FatorRiscoDAO extends DAO<FatorRisco> {

	public FatorRiscoDAO(Connection conn) {
		super(conn);
	}
	
	public FatorRiscoDAO() {
		super(null);
	}
	
	@Override
	public void create(FatorRisco fatorRisco) throws SQLException {
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " + 
				" public.fator_risco " +
				" (idfator_risco, " +
				" nome, " +
				" descricao, " +
				" ativo)" +
				" VALUES " +
				" (?, ?, ?, ?) ");

		stat.setInt(1, fatorRisco.getIdfatorRisco());
		stat.setString(2, fatorRisco.getNome());
		stat.setString(3, fatorRisco.getDescricao());
		stat.setBoolean(4, fatorRisco.isAtivo());
		stat.execute();
		stat.close();
		
	}

	@Override
	public List<FatorRisco> findAll() {
		Connection conn = getConnection();
		if (conn == null)
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					" idfator_risco, " + 
					"nome," + 
					" descricao," + 
					" ativo," +
					"FROM " +
					" public.fatorRisco ");

			ResultSet rs = stat.executeQuery();

			List<FatorRisco> listaFatorRisco = new ArrayList<FatorRisco>();

			while (rs.next()) {
				FatorRisco fatorRisco = new FatorRisco();
				fatorRisco.setIdfatorRisco(rs.getInt("idfator_risco"));
				fatorRisco.setNome(rs.getString("nome"));
				fatorRisco.setDescricao(rs.getString("descricao"));
				fatorRisco.setAtivo(rs.getBoolean("ativo"));
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
	public void update(FatorRisco entity) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub

	}
}
