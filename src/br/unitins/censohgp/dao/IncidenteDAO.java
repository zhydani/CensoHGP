package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.Departamento;
import br.unitins.censohgp.model.Incidente;
import br.unitins.censohgp.model.Paciente;
import br.unitins.censohgp.model.Precaucao;
import br.unitins.censohgp.model.Sexo;
import br.unitins.censohgp.model.Situacao;

public class IncidenteDAO extends DAO<Incidente>{
	
	public IncidenteDAO(Connection conn) {
		super(conn);
	}

	@Override
	public void create(Incidente incidente) throws SQLException {
		int key = 0;
		Connection  conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
						" incidente " +
						" ( nome, cpf, rg, ativo, idsituacao, idgenero, nome_mae, data_nascimento, observacao, numero_prontuario, iddepartamento) " +
						" VALUES " +
						" (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);

//		stat.setString(1, incidente.getNome());
//		stat.setInt(2, incidente.getCpf());
//		stat.setInt(3, incidente.getRg());
//		stat.setBoolean(4, incidente.getAtivo());
//		stat.setInt(5, incidente.getSituacao().getIdsituacao());
//		stat.setInt(6, incidente.getSexo().getIdsexo());
//		stat.setString(7, incidente.getNomeMae());
//		Date date = Date.valueOf(incidente.getDataNascimento());
//		stat.setDate(8, date);
//		stat.setString(9, incidente.getObservacao());
//		stat.setInt(10, incidente.getNumeroProntuario());
//		stat.setInt(11, incidente.getIdlocalTransferencia().getIdlocalTransferencia());
//		stat.execute();
//
		ResultSet rs = stat.getGeneratedKeys();
//
//		if (rs.next()) {
//		    key = rs.getInt(1);
//		}
//			
//		createAux( key , incidente.getPrecaucoes());


	}



	@Override
	public List<Incidente> findAll() {
//		Connection conn = getConnection();
//		if (conn == null) 
//			return null;
//
//		try {
//			PreparedStatement stat = conn.prepareStatement(
//					"SELECT " +
//							" idincidente, "
//							+ "nome,"
//							+ " cpf,"
//							+ " rg,"
//							+ " ativo,"
//							+ " nome_mae,"
//							+ " idsituacao,"
//							+ " data_nascimento,"
//							+ " observacao,"
//							+ " numero_prontuario  " +
//							"FROM " +
//					"  incidente ");
//
//			ResultSet rs = stat.executeQuery();
//
//			List<Paciente> listaPaciente = new ArrayList<Paciente>();
//
//			while(rs.next()) {
//				Paciente incidente = new Paciente();
//				incidente.setIdincidente(rs.getInt("idincidente"));
//				incidente.setNome(rs.getString("nome"));
//				incidente.setCpf(rs.getInt("cpf"));
//				incidente.setRg(rs.getInt("rg"));
//				incidente.setAtivo(rs.getBoolean("ativo"));
//				incidente.setNomeMae(rs.getString("nome_mae"));
//				incidente.setDataNascimento(rs.getDate("data_nascimento") == null ? null : (rs.getDate("data_nascimento").toLocalDate()));
//				incidente.setObservacao(rs.getString("observacao"));
//				incidente.setNumeroProntuario(rs.getInt("numero_prontuario"));
//
//				listaPaciente.add(incidente);
//
//			}			 			
//
//			if (listaPaciente.isEmpty())
//				return null;
//			return listaPaciente;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return null;
	}

	@Override
	public void update(Incidente entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
}
