package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.Incidente;

public class IncidenteDAO extends DAO<Incidente> {

	public IncidenteDAO(Connection conn) {
		super(conn);
	}

	public IncidenteDAO() {
		super(null);
	}

	@Override
	public void create(Incidente incidente) throws SQLException {
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " + 
				" public.incidente " + 
				" (idincidente, "+
				" nome, " +
				" descricao, "+ 
				" ativo) "	+
				" VALUES " + 
				" (?, ?, ?, ?) ");
		stat.setInt(1, incidente.getIdincidente());
		stat.setString(2, incidente.getNome());
		stat.setString(3, incidente.getDescricao());
		stat.setBoolean(4, incidente.isAtivo());
		stat.execute();
		stat.close();
		
	}

	@Override
	public List<Incidente> findAll() {
		Connection conn = getConnection();
		if (conn == null) 
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					" idincidente, " +
					"nome, " +
					" descricao, " + 
					" ativo "+
					"FROM " +
					" public.incidente ");

			ResultSet rs = stat.executeQuery();

			List<Incidente> listaIncidente = new ArrayList<Incidente>();

			while(rs.next()) {
				Incidente incidente = new Incidente();
				incidente.setIdincidente(rs.getInt("idincidente"));
				incidente.setNome(rs.getString("nome"));
				incidente.setDescricao(rs.getString("descricao"));
				incidente.setAtivo(rs.getBoolean("ativo"));

				listaIncidente.add(incidente);

			}			 			

			if (listaIncidente.isEmpty())
				return null;
			return listaIncidente;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Incidente entity) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

}
