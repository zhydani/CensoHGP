package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.Precaucao;

public class PrecaucaoDAO extends DAO<Precaucao> {
	




	
	public PrecaucaoDAO(Connection conn) {
		super(conn);
	}
	
	public PrecaucaoDAO() {
		super(null);
	}
	

	@Override
	public void create(Precaucao pre) throws SQLException {
		
			
	}

	@Override
	public void update(Precaucao pre) throws SQLException {
		
	}

	@Override
	public void delete(int id) throws SQLException {

	}

	@Override
	public List<Precaucao> findAll() {
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  idprecaucao, " +
					"  nome " +
					"FROM " +
					"  public.precaucao");
			ResultSet rs = stat.executeQuery();
			
			List<Precaucao> listaPrecaucao = new ArrayList<Precaucao>();
			
			while(rs.next()) {
				Precaucao precaucao = new Precaucao();
				precaucao.setIdprecaucao(rs.getInt("idprecaucao"));
				precaucao.setNome(rs.getString("nome"));
				
				
				listaPrecaucao.add(precaucao);
			}
			
			if (listaPrecaucao.isEmpty())
				return null;
			return listaPrecaucao;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
