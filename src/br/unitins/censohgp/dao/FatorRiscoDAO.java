package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.FatorRisco;

public class FatorRiscoDAO extends DAO<FatorRisco> {

	public FatorRiscoDAO(Connection conn) {
		super(conn);
	}

	@Override
	public void create(FatorRisco fatorRisco) throws SQLException {
		int key = 0;
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement("INSERT INTO " + " fatorRisco "
				+ " ( nome, cpf, rg, ativo, idsituacao, idgenero, nome_mae, data_nascimento, observacao, numero_prontuario, iddepartamento) "
				+ " VALUES " + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);

//		stat.setString(1, fatorRisco.);

		stat.execute();

		ResultSet rs = stat.getGeneratedKeys();


	}



	@Override
	public List<FatorRisco> findAll() {
//		Connection conn = getConnection();
//		if (conn == null)
//			return null;
//
//		try {
//			PreparedStatement stat = conn.prepareStatement(
//					"SELECT " + " idfatorRisco, " + "nome," + " cpf," + " rg," + " ativo," + " nome_mae," + " idsituacao,"
//							+ " data_nascimento," + " observacao," + " numero_prontuario  " + "FROM " + "  fatorRisco ");
//
//			ResultSet rs = stat.executeQuery();
//
//			List<FatorRisco> listaFatorRisco = new ArrayList<FatorRisco>();
//
//			while (rs.next()) {
//				FatorRisco fatorRisco = new FatorRisco();
//				fatorRisco.setIdfatorRisco(rs.getInt("idfatorRisco"));
//				fatorRisco.setNome(rs.getString("nome"));
//				fatorRisco.setCpf(rs.getInt("cpf"));
//				fatorRisco.setRg(rs.getInt("rg"));
//				fatorRisco.setAtivo(rs.getBoolean("ativo"));
//				fatorRisco.setNomeMae(rs.getString("nome_mae"));
//				fatorRisco.setDataNascimento(
//						rs.getDate("data_nascimento") == null ? null : (rs.getDate("data_nascimento").toLocalDate()));
//				fatorRisco.setObservacao(rs.getString("observacao"));
//				fatorRisco.setNumeroProntuario(rs.getInt("numero_prontuario"));
//
//				listaFatorRisco.add(fatorRisco);
//
//			}
//
//			if (listaFatorRisco.isEmpty())
//				return null;
//			return listaFatorRisco;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return null;
	}


	@Override
	public void update(FatorRisco entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}
}
