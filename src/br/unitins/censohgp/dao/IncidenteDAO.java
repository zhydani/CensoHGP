package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public void delete(int id) throws SQLException {

	}

	@Override
	public void create(Incidente entity) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Incidente entity) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Incidente> findAll() {
		Connection conn = getConnection();
		if (conn == null)
			return null;

		try {
			PreparedStatement stat = conn
					.prepareStatement("SELECT " + "  idincidente, " + "  nome " + "FROM " + "  public.incidente");
			ResultSet rs = stat.executeQuery();

			List<Incidente> listaIncidente = new ArrayList<Incidente>();

			while (rs.next()) {
				Incidente incidente = new Incidente();
				incidente.setIdincidente(rs.getInt("idincidente"));
				incidente.setNome(rs.getString("nome"));

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

}
