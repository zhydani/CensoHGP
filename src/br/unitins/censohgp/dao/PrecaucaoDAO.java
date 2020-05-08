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
		// tchê papai ... cria uma nova conexao
		super(null);
	}
	

	@Override
	public void create(Precaucao pre) throws SQLException {
		
		Connection  conn = getConnection();
			
		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
			    " public.precuacao " +
			    " ( idprecuacao, aerossois, contato, goticulas, padrao ) " +
				"VALUES " +
			    " (?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
		stat.setInt(1, pre.getIdprecaucao());
		stat.setBoolean(2, pre.getAerossol());
		stat.setBoolean(3, pre.getContato());
		stat.setBoolean(4, pre.getGoticulas());
		stat.setBoolean(5, pre.getPadrao());
		
		stat.execute();
		stat.close();
			
	}

	@Override
	public void update(Precaucao pre) throws SQLException {
		Connection  conn = getConnection();
		
		PreparedStatement stat = conn.prepareStatement(
				"UPDATE public.precuacao SET " +
			    " aerossois = ?, " +
			    " contato = ?, " +
			    " goticulas= ?, " +
			    " padrao = ?, " +
				"WHERE " +
			    " idprecuacao = ? ");
		
		stat.setBoolean(1, pre.getAerossol());
		stat.setBoolean(2, pre.getContato());
		stat.setBoolean(3, pre.getGoticulas());
		stat.setBoolean(4, pre.getPadrao());
		stat.setInt(5, pre.getIdprecaucao());	
		stat.execute();
		
		
	}

	@Override
	public void delete(int id) throws SQLException {

		Connection  conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"DELETE FROM public.precuacao WHERE idprecuacao = ?");
		stat.setInt(1, id);

		stat.execute();
		stat.close();
			
	}

	@Override
	public List<Precaucao> findAll() {
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  idprecuacao, " +
					"  aerossois, " +
					"  contato, " +
					"  goticulas, " +
					"  padrao " +
					"FROM " +
					"  public.precuacao");
			ResultSet rs = stat.executeQuery();
			
			List<Precaucao> listaPrecuacao = new ArrayList<Precaucao>();
			
			while(rs.next()) {
				Precaucao precuacao = new Precaucao();
				precuacao.setIdprecaucao(rs.getInt("idprecuacao"));
				precuacao.setAerossol(rs.getBoolean("aerossois"));
				precuacao.setContato(rs.getBoolean("contato"));
				precuacao.setGoticulas(rs.getBoolean("goticulas"));
				precuacao.setPadrao(rs.getBoolean("padrao"));
				
				
				listaPrecuacao.add(precuacao);
			}
			
			if (listaPrecuacao.isEmpty())
				return null;
			return listaPrecuacao;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Precaucao findId(Integer id) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
							"  idprecuacao, " +
							"  aerossois, " +
							"  contato, " +
							"  goticulas, " +
							"  padrao " +
							"FROM " +
							"  public.precuacao" +
					"WHERE idprecuacao = ? ");
			
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			Precaucao precaucao = null;
			
			if(rs.next()) {
				precaucao = new Precaucao();
				Precaucao precuacao = new Precaucao();
				precuacao.setIdprecaucao(rs.getInt("idprecuacao"));
				precuacao.setAerossol(rs.getBoolean("aerossois"));
				precuacao.setContato(rs.getBoolean("contato"));
				precuacao.setGoticulas(rs.getBoolean("goticulas"));
				precuacao.setPadrao(rs.getBoolean("padrao"));
			}
			
			return precaucao;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
