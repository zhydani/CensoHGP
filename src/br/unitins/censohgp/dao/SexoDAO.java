package br.unitins.censohgp.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.Sexo;
import br.unitins.censohgp.model.Tipo;

public class SexoDAO extends DAO<Sexo> {

	public SexoDAO(Connection conn) {
		super(conn);
	}


	public SexoDAO() {
		super(null);
	}


	@Override
	public void create(Sexo entity) throws SQLException {

	}

	@Override
	public void update(Sexo entity) throws SQLException {
	}

	@Override
	public void delete(int id) throws SQLException {
	}

	@Override
	public List<Sexo> findAll() {
		Connection conn = getConnection();
		if (conn == null) 
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
							" idgenero, " +
							" nome  " +
							" FROM " +
					"  public.genero ");

			ResultSet rs = stat.executeQuery();

			List<Sexo> listaSexo = new ArrayList<Sexo>();

			while(rs.next()) {
				Sexo sexo = new Sexo();
				sexo.setIdsexo(rs.getInt("idgenero"));
				sexo.setNome(rs.getString("nome"));

				listaSexo.add(sexo);

			}			 			

			if (listaSexo.isEmpty())
				return null;
			return listaSexo;

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
					"SELECT " + " idgenero, " + "  nome " + "FROM " + "  public.genero " + "WHERE idgenero = ? ");

			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();

			Tipo tipo = null;

			if (rs.next()) {
				tipo = new Tipo();
				tipo.setId(rs.getInt("idgenero"));
				tipo.setNome(rs.getString("nome"));
			}

			return tipo;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
