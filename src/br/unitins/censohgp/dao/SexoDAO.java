package br.unitins.censohgp.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.Sexo;
import br.unitins.censohgp.model.Situacao;

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
							" idsexo, " +
							" nome  " +
							" FROM " +
					"  public.tipo_sexo ");

			ResultSet rs = stat.executeQuery();

			List<Sexo> listaSexo = new ArrayList<Sexo>();

			while(rs.next()) {
				Sexo sexo = new Sexo();
				sexo.setIdsexo(rs.getInt("idsexo"));
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

	public Sexo findById(Integer id) {
		Connection conn = getConnection();

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
							"  idsexo, " +
							"  nome  " +
							"  FROM " +
							"  public.tipo_sexo " +
					"WHERE idsexo = ? ");

			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();

			Sexo sexo = null;

			if(rs.next()) {
				sexo = new Sexo();
				sexo.setIdsexo(rs.getInt("idsexo"));
				sexo.setNome(rs.getString("nome"));
			}

			return sexo;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
