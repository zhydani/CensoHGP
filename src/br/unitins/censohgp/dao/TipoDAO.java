package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.Tipo;

public class TipoDAO extends DAO<Tipo> {

	public TipoDAO(Connection conn) {
		super(conn);
	}

	public TipoDAO() {
		super(null);
	}

	@Override
	public void create(Tipo entity) throws SQLException {

	}

	@Override
	public void update(Tipo entity) throws SQLException {
	}

	@Override
	public void delete(int id) throws SQLException {
	}

	@Override
	public List<Tipo> findAll() {
		Connection conn = getConnection();
		if (conn == null)
			return null;

		try {
			PreparedStatement stat = conn
					.prepareStatement("SELECT " + " idtipo_tipo, " + " nome  " + " FROM " + "  public.tipo_tipo ");

			ResultSet rs = stat.executeQuery();

			List<Tipo> listaTipo = new ArrayList<Tipo>();

			while (rs.next()) {
				Tipo sexo = new Tipo();
				sexo.setId(rs.getInt("idtipo_tipo"));
				sexo.setNome(rs.getString("nome"));

				listaTipo.add(sexo);

			}

			if (listaTipo.isEmpty())
				return null;
			return listaTipo;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Tipo findId(Integer id) {
		Connection conn = getConnection();
		if (conn == null)
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " + " idtipo_tipo, " + "  nome " + "FROM " + "  public.tipo_tipo " + "WHERE id = ? ");

			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();

			Tipo tipo = null;

			if (rs.next()) {
				tipo = new Tipo();
				tipo.setId(rs.getInt("idtipo_tipo"));
				tipo.setNome(rs.getString("nome"));
			}

			return tipo;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
