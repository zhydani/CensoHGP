package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.CidadeDepartamento;
import br.unitins.censohgp.model.EstadoDepartamento;
import br.unitins.censohgp.model.Tipo;

public class CidadeDepartamentoDAO extends DAO<CidadeDepartamento> {

	public CidadeDepartamentoDAO(Connection conn) {
		super(conn);
	}

	public CidadeDepartamentoDAO() {
		super(null);
	}

	@Override
	public void create(CidadeDepartamento entity) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
				" public.cidade " +
				" ( idcidade, cidade ) " + 
				"VALUES " +
				" ( ?, ? ) ");
		stat.setInt(1, entity.getIdcidade());
		stat.setString(2, entity.getCidade());
		stat.execute();
		stat.close();

	}

	@Override
	public void update(CidadeDepartamento entity) throws SQLException {
	}

	@Override
	public void delete(int id) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement stat = conn.prepareStatement("DELETE FROM public.cidade WHERE idcidade =  ?");
		stat.setInt(1, id);
		stat.execute();
		stat.close();
	}

	@Override
	public List<CidadeDepartamento> findAll() {
 		Connection conn = getConnection();
		if (conn == null) 
			return null;
		try {
			PreparedStatement stat = conn.prepareStatement(
						"SELECT " +
						" idcidade, " +
						" cidade,  " +
						" idestado  " +
						" FROM " +
						"  public.cidade ");
			ResultSet rs = stat.executeQuery();
			List<CidadeDepartamento> listaCidades = new ArrayList<CidadeDepartamento>();
			while(rs.next()) {
				CidadeDepartamento cidade = new CidadeDepartamento();
				cidade.setIdcidade(rs.getInt("idcidade"));
				cidade.setCidade(rs.getString("cidade"));
				listaCidades.add(cidade);
			}			 			
			if (listaCidades.isEmpty())
				return null;
			return listaCidades;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public CidadeDepartamento findById(Integer id) {
		Connection conn = getConnection();
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					" idcidade, " +
					" cidade  " +
					" FROM " +
					" public.cidade " +
					" WHERE idcidade = ? ");
			stat.setInt(1, id);
			ResultSet rs = stat.executeQuery();
			CidadeDepartamento cidade = null;
			if (rs.next()) {
				cidade = new CidadeDepartamento();
				cidade.setIdcidade(rs.getInt("idcidade"));
				cidade.setCidade(rs.getString("cidade"));
			}
			return cidade;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public CidadeDepartamento findByIdParaTransferencia(Integer id) {
		Connection conn = getConnection();
		try {
			PreparedStatement stat = conn.prepareStatement(
					   "SELECT " + 
					   " idcidade, " + 
					   " cidade, " +
					   " idestado  " +
					   " FROM " + 
					   " public.cidade " +
					   " WHERE idcidade = ? ");
			stat.setInt(1, id);
			ResultSet rs = stat.executeQuery();
			CidadeDepartamento cidade = new CidadeDepartamento();
			if (rs.next()) {
				EstadoDepartamento estaux = new EstadoDepartamento();
				estaux.setIdestado(rs.getInt("idestado"));
				cidade.setIdcidade(rs.getInt("idcidade"));
				cidade.setCidade(rs.getString("cidade"));
				cidade.setEstado(estaux);
				// cidade.setIdestado(rs.getInt("idestado"));
			}
			return cidade;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<CidadeDepartamento> findByEstado(Integer id) {
		Connection conn = getConnection();
		try {
			PreparedStatement stat = conn.prepareStatement(
					" SELECT " +
					" idcidade, " +
					" cidade  " + 
					" FROM " +
					" public.cidade " +
					" WHERE idestado = ? ");
			stat.setInt(1, id);
			ResultSet rs = stat.executeQuery();
			List<CidadeDepartamento> listaCidades = new ArrayList<CidadeDepartamento>();
			while(rs.next()) {
				CidadeDepartamento cidade = new CidadeDepartamento();
				cidade.setIdcidade(rs.getInt("idcidade"));
				cidade.setCidade(rs.getString("cidade"));
				listaCidades.add(cidade);
			}			 			
			if (listaCidades.isEmpty())
				return null;
			return listaCidades;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
