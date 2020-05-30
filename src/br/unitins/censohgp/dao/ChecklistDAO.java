package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.Checklist;
import br.unitins.censohgp.model.Paciente;
import br.unitins.censohgp.model.FatorRisco;
import br.unitins.censohgp.model.Incidente;
import br.unitins.censohgp.model.Procedimento;
import br.unitins.censohgp.model.Usuario;

public class ChecklistDAO extends DAO<Checklist>{
	
	public ChecklistDAO(Connection conn) {
		super(conn);
	}

	public ChecklistDAO() {
		super(null);
	}

	@Override
	public void create(Checklist checklist) throws SQLException {

		Connection  conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
						" checklist " +
						" ( nome, cpf, rg, ativo, idsituacao, idgenero, nome_mae, data_nascimento, observacao, numero_prontuario, iddepartamento) " +
						" VALUES " +
						" (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);

//		stat.setString(1, checklist.getNome());
//		stat.setInt(2, checklist.getCpf());
//		stat.setInt(3, checklist.getRg());
//		stat.setBoolean(4, checklist.getAtivo());
//		stat.setInt(5, checklist.getSituacao().getIdsituacao());
//		stat.setInt(6, checklist.getSexo().getIdsexo());
//		stat.setString(7, checklist.getNomeMae());
//		Date date = Date.valueOf(checklist.getDataNascimento());
//		stat.setDate(8, date);
//		stat.setString(9, checklist.getObservacao());
//		stat.setInt(10, checklist.getNumeroProntuario());
//		stat.setInt(11, checklist.getIdlocalTransferencia().getIdlocalTransferencia());
//		stat.execute();

		ResultSet rs = stat.getGeneratedKeys();



	}

//	public List<Checklist> findByNome(String nome) {
//		Connection conn = getConnection();
//		if (conn == null) 
//			return null;
//
//		try {
//			PreparedStatement stat = conn.prepareStatement(
//					"SELECT " +
//							" idchecklist, "
//							+ "nome,"
//							+ " cpf,"
//							+ " rg,"
//							+ " ativo,"
//							+ " nome_mae,"
//							+ " data_nascimento,"
//							+ " observacao,"
//							+ " numero_prontuario, " 
//							+ "  idsituacao  " +
//							"  FROM " +
//							"  checklist " +			
//							"WHERE " +
//					"  nome ilike ? ");
//
//			stat.setString(1, nome == null ? "%" : "%"+nome+"%");
//			ResultSet rs = stat.executeQuery();
//
//			List<Checklist> listaChecklistPaciente = new ArrayList<Checklist>();
//
//			while(rs.next()) {
//				Checklist checklist = new Checklist();
//				checklist.setIdchecklist(rs.getInt("idchecklist"));
//				checklist.setNome(rs.getString("nome"));
//				checklist.setCpf(rs.getInt("cpf"));
//				checklist.setRg(rs.getInt("rg"));
//				checklist.setAtivo(rs.getBoolean("ativo"));
//				checklist.setNomeMae(rs.getString("nome_mae"));
//				checklist.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
//				checklist.setObservacao(rs.getString("observacao"));
//				checklist.setNumeroProntuario(rs.getInt("numero_prontuario"));
//
//
//
//				listaChecklist.add(checklist);
//
//
//
//			}			 			
//
//			if (listaChecklist.isEmpty())
//				return null;
//			return listaChecklist;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//

	@Override
	public List<Checklist> findAll() {
//		Connection conn = getConnection();
//		if (conn == null) 
//			return null;
//
//		try {
//			PreparedStatement stat = conn.prepareStatement(
//					"SELECT " +
//							" idchecklist, "
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
//					"  checklist ");
//
//			ResultSet rs = stat.executeQuery();
//
//			List<Checklist> listaChecklist = new ArrayList<Checklist>();
//
//			while(rs.next()) {
//				Checklist checklist = new Checklist();
//				checklist.setIdchecklist(rs.getInt("idchecklist"));
//				checklist.setNome(rs.getString("nome"));
//				checklist.setCpf(rs.getInt("cpf"));
//				checklist.setRg(rs.getInt("rg"));
//				checklist.setAtivo(rs.getBoolean("ativo"));
//				checklist.setNomeMae(rs.getString("nome_mae"));
//				checklist.setDataNascimento(rs.getDate("data_nascimento") == null ? null : (rs.getDate("data_nascimento").toLocalDate()));
//				checklist.setObservacao(rs.getString("observacao"));
//				checklist.setNumeroProntuario(rs.getInt("numero_prontuario"));
//
//				listaChecklist.add(checklist);
//
//			}			 			
//
//			if (listaChecklist.isEmpty())
//				return null;
//			return listaChecklist;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return null;
	}


	@Override
	public void update(Checklist checklist) throws SQLException {
		
	}

	@Override
	public void delete(int id) throws SQLException {
		
	}

}
