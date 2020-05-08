package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.Paciente;
import br.unitins.censohgp.model.Precaucao;

public class PacienteDAO extends DAO<Paciente>{

	public PacienteDAO(Connection conn) {
		super(conn);
	}

	public PacienteDAO() {
		super(null);
	}

	@Override
	public void create(Paciente paciente) throws SQLException {

		Connection  conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
						" paciente " +
						" ( nome, cpf, rg, ativo, idsituacao, idgenero, nome_mae, data_nascimento, observacao, numero_prontuario, idlocal_transferencia ) " +
						" VALUES " +
						" (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);

		stat.setString(1, paciente.getNome());
		stat.setInt(2, paciente.getCpf());
		stat.setInt(3, paciente.getRg());
		stat.setBoolean(4, paciente.getAtivo());
		stat.setInt(5, paciente.getSituacao().getIdsituacao());
		stat.setInt(6, paciente.getSexo().getIdsexo());
		stat.setString(7, paciente.getNomeMae());
		Date date = Date.valueOf(paciente.getDataNascimento());
		stat.setDate(8, date);
		stat.setString(9, paciente.getObservacao());
		stat.setInt(10, paciente.getNumeroProntuario());
		stat.setInt(11, paciente.getIdlocalTransferencia().getIdlocalTransferencia());
		
		stat.execute();

		// obtendo o id gerado pela tabela do banco de dados
		ResultSet rs = stat.getGeneratedKeys();
		rs.next();
		Integer value = rs.getInt("idpaciente");

		paciente.getPrecaucao().setIdprecaucao(value);
		PrecaucaoDAO dao = new PrecaucaoDAO(conn);
		dao.create(paciente.getPrecaucao());


	}

	@Override
	public void update(Paciente paciente) throws SQLException {
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"UPDATE paciente SET " +
						"  nome = ?,"
						+ " cpf = ?,"
						+ " rg = ?,"
						+ " ativo = ?,"
						+ " nome_mae = ?,"
						+ " data_nascimento = ?,"
						+ " observacao = ?,"
						+ " numero_prontuario = ?, " 
						+ " idsituacao = ? " +
						" WHERE " +
				" idpaciente = ? ");

		stat.setString(1, paciente.getNome());
		stat.setInt(2, paciente.getCpf());
		stat.setInt(3, paciente.getRg());
		stat.setBoolean(4, paciente.getAtivo());
		stat.setString(5, paciente.getNomeMae());
		Date date = Date.valueOf(paciente.getDataNascimento());
		stat.setDate(6, date);
		stat.setString(7, paciente.getObservacao());
		stat.setInt(8, paciente.getNumeroProntuario());
		stat.setInt(9, paciente.getIdpaciente());
		stat.execute();

	}

	@Override
	public void delete(int id) throws SQLException {

		Connection conn = getConnection();

		PrecaucaoDAO dao = new PrecaucaoDAO(conn);
		dao.delete(id);

		PreparedStatement stat = conn.prepareStatement("DELETE FROM public.paciente WHERE idpaciente = ?");
		stat.setInt(1, id);

		stat.execute();

	}

	public List<Paciente> findByNome(String nome) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
							" idpaciente, "
							+ "nome,"
							+ " cpf,"
							+ " rg,"
							+ " ativo,"
							+ " nome_mae,"
							+ " data_nascimento,"
							+ " observacao,"
							+ " numero_prontuario, " 
							+ "  idsituacao  " +
							"  FROM " +
							"  paciente " +			
							"WHERE " +
					"  nome ilike ? ");

			stat.setString(1, nome == null ? "%" : "%"+nome+"%");
			ResultSet rs = stat.executeQuery();

			List<Paciente> listaPaciente = new ArrayList<Paciente>();

			while(rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setIdpaciente(rs.getInt("idpaciente"));
				paciente.setNome(rs.getString("nome"));
				paciente.setCpf(rs.getInt("cpf"));
				paciente.setRg(rs.getInt("rg"));
				paciente.setAtivo(rs.getBoolean("ativo"));
				paciente.setNomeMae(rs.getString("nome_mae"));
				paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				paciente.setObservacao(rs.getString("observacao"));
				paciente.setNumeroProntuario(rs.getInt("numero_prontuario"));

				PrecaucaoDAO dao = new PrecaucaoDAO(conn);
				paciente.setPrecaucao(dao.findId(paciente.getIdpaciente()));
				// caso o retorno do telefone seja nulo, instanciar um telefone
				if (paciente.getPrecaucao() == null)
					paciente.setPrecaucao(new Precaucao());


				listaPaciente.add(paciente);



			}			 			

			if (listaPaciente.isEmpty())
				return null;
			return listaPaciente;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<Paciente> findAll() {
		Connection conn = getConnection();
		if (conn == null) 
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
							" idpaciente, "
							+ "nome,"
							+ " cpf,"
							+ " rg,"
							+ " ativo,"
							+ " nome_mae,"
							+ " idsituacao,"
							+ " data_nascimento,"
							+ " observacao,"
							+ " numero_prontuario  " +
							"FROM " +
					"  paciente ");

			ResultSet rs = stat.executeQuery();

			List<Paciente> listaPaciente = new ArrayList<Paciente>();

			while(rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setIdpaciente(rs.getInt("idpaciente"));
				paciente.setNome(rs.getString("nome"));
				paciente.setCpf(rs.getInt("cpf"));
				paciente.setRg(rs.getInt("rg"));
				paciente.setAtivo(rs.getBoolean("ativo"));
				paciente.setNomeMae(rs.getString("nome_mae"));
				paciente.setDataNascimento(rs.getDate("data_nascimento") == null ? null : (rs.getDate("data_nascimento").toLocalDate()));
				paciente.setObservacao(rs.getString("observacao"));
				paciente.setNumeroProntuario(rs.getInt("numero_prontuario"));

				PrecaucaoDAO dao = new PrecaucaoDAO(conn);
				paciente.setPrecaucao(dao.findId(paciente.getIdpaciente()));
				// caso o retorno do telefone seja nulo, instanciar um telefone
				if (paciente.getPrecaucao() == null)
					paciente.setPrecaucao(new Precaucao());

				listaPaciente.add(paciente);

			}			 			

			if (listaPaciente.isEmpty())
				return null;
			return listaPaciente;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Paciente findById(Integer id) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
							" idpaciente, "
							+ " nome,"
							+ " cpf,"
							+ " rg,"
							+ " ativo,"
							+ " nome_mae,"
							+ " idsituacao,"
							+ " data_nascimento,"
							+ " observacao,"
							+ " numero_prontuario " +
							" FROM " +
							"  paciente "
							+ "  WHERE idpaciente = ? ");

			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();

			Paciente paciente = null;

			if(rs.next()) {
				paciente = new Paciente();
				paciente.setIdpaciente(rs.getInt("idpaciente"));
				paciente.setNome(rs.getString("nome"));
				paciente.setCpf(rs.getInt("cpf"));
				paciente.setRg(rs.getInt("rg"));
				paciente.setAtivo(rs.getBoolean("ativo"));
				paciente.setNomeMae(rs.getString("nome_mae"));
				paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				paciente.setObservacao(rs.getString("observacao"));
				paciente.setNumeroProntuario(rs.getInt("numero_prontuario"));

				PrecaucaoDAO dao = new PrecaucaoDAO(conn);
				paciente.setPrecaucao(dao.findId(paciente.getIdpaciente()));
				// caso o retorno do telefone seja nulo, instanciar um telefone
				if (paciente.getPrecaucao() == null)
					paciente.setPrecaucao(new Precaucao());

			}


			return paciente;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}