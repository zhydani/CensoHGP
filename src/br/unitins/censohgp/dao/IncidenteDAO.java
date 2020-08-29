package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.Incidente;


public class IncidenteDAO extends DAO<Incidente> {
	
	public List<Incidente> findByIdChecklist(Integer idChecklist) {
		Connection conn = getConnection();
		if (conn == null)
			return null;
		try {
			PreparedStatement stat = conn.prepareStatement(
					  "SELECT " +
					  " p.idincidente, " +
					  " i.nome " +
					  " FROM public.incidente i, " +
					  " public.checklist_incidente p " +
					  " WHERE p.idchecklist = ? " + 
					  " AND i.idincidente = p.idincidente ");
			stat.setInt(1, idChecklist);
			List<Incidente> listaIncidente = new ArrayList<Incidente>();
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Incidente incidente = new Incidente();
				incidente.setIdincidente(rs.getInt("idincidente"));
				incidente.setNome(rs.getString("nome"));
				listaIncidente.add(incidente);
			}
			if (listaIncidente.isEmpty())
				return null;
			System.out.println(listaIncidente.toString());
			return listaIncidente;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Incidente> findAll() {
		Connection conn = getConnection();
		if (conn == null)
			return null;
		try {
			PreparedStatement stat = conn
					.prepareStatement(
					"SELECT " +
					"  idincidente, " +
					"  nome " +
					"FROM " +
					"  public.incidente");
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
	}
	
	@Override
	public void update(Incidente entity) throws SQLException {
	}
}