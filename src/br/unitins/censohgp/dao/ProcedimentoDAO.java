package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.Procedimento;

public class ProcedimentoDAO extends DAO<Procedimento> {
	
	public ProcedimentoDAO(Connection conn) {
		super(conn);
	}


	@Override
	public void create(Procedimento procedimento) throws SQLException {
//		int key = 0;
//		Connection  conn = getConnection();
//
//		PreparedStatement stat = conn.prepareStatement(
//				"INSERT INTO " +
//						" paciente " +
//						" ( nome, cpf, rg, ativo, idsituacao, idgenero, nome_mae, data_nascimento, observacao, numero_prontuario, iddepartamento) " +
//						" VALUES " +
//						" (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
//
//		stat.setString(1, paciente.getNome());
//		stat.setInt(2, paciente.getCpf());
//		stat.setInt(3, paciente.getRg());
//		stat.setBoolean(4, paciente.getAtivo());
//		stat.setInt(5, paciente.getSituacao().getIdsituacao());
//		stat.setInt(6, paciente.getSexo().getIdsexo());
//		stat.setString(7, paciente.getNomeMae());
//		Date date = Date.valueOf(paciente.getDataNascimento());
//		stat.setDate(8, date);
//		stat.setString(9, paciente.getObservacao());
//		stat.setInt(10, paciente.getNumeroProntuario());
//		stat.setInt(11, paciente.getIdlocalTransferencia().getIdlocalTransferencia());
//		stat.execute();
//
//		ResultSet rs = stat.getGeneratedKeys();
//
//		if (rs.next()) {
//		    key = rs.getInt(1);
//		}
//			
//		createAux( key , paciente.getPrecaucoes());
//

	}

	
	@Override
	public List<Procedimento> findAll() {
//		Connection conn = getConnection();
//		if (conn == null) 
//			return null;
//
//		try {
//			PreparedStatement stat = conn.prepareStatement(
//					"SELECT " +
//							" idpaciente, "
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
//					"  paciente ");
//
//			ResultSet rs = stat.executeQuery();
//
//			List<Paciente> listaPaciente = new ArrayList<Paciente>();
//
//			while(rs.next()) {
//				Paciente paciente = new Paciente();
//				paciente.setIdpaciente(rs.getInt("idpaciente"));
//				paciente.setNome(rs.getString("nome"));
//				paciente.setCpf(rs.getInt("cpf"));
//				paciente.setRg(rs.getInt("rg"));
//				paciente.setAtivo(rs.getBoolean("ativo"));
//				paciente.setNomeMae(rs.getString("nome_mae"));
//				paciente.setDataNascimento(rs.getDate("data_nascimento") == null ? null : (rs.getDate("data_nascimento").toLocalDate()));
//				paciente.setObservacao(rs.getString("observacao"));
//				paciente.setNumeroProntuario(rs.getInt("numero_prontuario"));
//
//				listaPaciente.add(paciente);
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
	public void update(Procedimento entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}
}
