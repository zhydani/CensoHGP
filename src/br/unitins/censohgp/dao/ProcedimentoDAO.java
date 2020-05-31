package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.Procedimento;

public class ProcedimentoDAO extends DAO<Procedimento> {

	public ProcedimentoDAO(Connection conn) {
		super(conn);
	}
	
	public ProcedimentoDAO() {
		super(null);
	}

	@Override
	public void create(Procedimento procedimento) throws SQLException {
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " + 
				" public.procedimento " + 
				" (idprocedimento, "+
				" nome, " +
				" descricao, "+ 
				" ativo) "	+
				" VALUES " + 
				" (?, ?, ?, ?) ");
		stat.setInt(1, procedimento.getIdprocedimento());
		stat.setString(2, procedimento.getNome());
		stat.setString(3, procedimento.getDescricao());
		stat.setBoolean(4, procedimento.isAtivo());
		stat.execute();
		stat.close();
		
	}

	@Override
	public List<Procedimento> findAll() {
		Connection conn = getConnection();
		if (conn == null) 
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					" idprocedimento, " +
					"nome, " +
					" descricao, " + 
					" ativo "+
					"FROM " +
					" public.procedimento ");

			ResultSet rs = stat.executeQuery();

			List<Procedimento> listaProcedimento = new ArrayList<Procedimento>();

			while(rs.next()) {
				Procedimento procedimento = new Procedimento();
				procedimento.setIdprocedimento(rs.getInt("idprocedimento"));
				procedimento.setNome(rs.getString("nome"));
				procedimento.setDescricao(rs.getString("descricao"));
				procedimento.setAtivo(rs.getBoolean("ativo"));

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
	public void update(Procedimento entity) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub

	}
}
