package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.unitins.censohgp.model.EstadoDepartamento;

public class EstadoDepartamentoDAO extends DAO<EstadoDepartamento> {

	public EstadoDepartamentoDAO(Connection conn) {
		super(conn);
	}

	public EstadoDepartamentoDAO() {
		super(null);
	}

	@Override
	public void create(EstadoDepartamento entity) throws SQLException {
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " + " public.estado " + " ( idestado, estado ) " + "VALUES " + " ( ?, ? ) ");
		stat.setInt(1, entity.getIdestado());
		stat.setString(2, entity.getEstado());

		stat.execute();
		stat.close();

	}

	@Override
	public void update(EstadoDepartamento entity) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(int id) throws SQLException {
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement("DELETE FROM public.estado WHERE idestado =  ?");
		stat.setInt(1, id);

		stat.execute();
		stat.close();
	}

	@Override
	public List<EstadoDepartamento> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public EstadoDepartamento findById(Integer id) {
		Connection conn = getConnection();

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " + "  idestado, " + "  estado  " + "FROM " + "  public.estado" + "WHERE idestado = ? ");

			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();

			EstadoDepartamento estado = null;

			if (rs.next()) {
				estado = new EstadoDepartamento();
				estado.setIdestado(rs.getInt("idestado"));
				estado.setEstado(rs.getString("estado"));
			}

			return estado;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public EstadoDepartamento findByIdParaTransferencia(Integer id) {
		Connection conn = getConnection();

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " + "  idestado, " + "  estado  " + "FROM " + "  public.estado " + "WHERE idestado = ? ");

			stat.setInt(1, id);
			ResultSet rs = stat.executeQuery();

			EstadoDepartamento estado = new EstadoDepartamento();

			if (rs.next()) {
				estado.setIdestado(rs.getInt("idestado"));
				estado.setEstado(rs.getString("estado"));
			}
			return estado;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
