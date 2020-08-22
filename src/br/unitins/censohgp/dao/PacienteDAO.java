package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.application.Util;
import br.unitins.censohgp.model.Departamento;
import br.unitins.censohgp.model.Paciente;
import br.unitins.censohgp.model.Precaucao;
import br.unitins.censohgp.model.Sexo;
import br.unitins.censohgp.model.Situacao;
import br.unitins.censohgp.model.Tipo;
import br.unitins.censohgp.model.TipoSexo;

public class PacienteDAO extends DAO<Paciente> {

	public PacienteDAO(Connection conn) {
		super(conn);
	}

	public PacienteDAO() {
		super(null);
	}

	@Override
	public void create(Paciente paciente) throws SQLException {
		int key = 0;
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement("INSERT INTO " + " paciente "
				+ " ( nome, cpf, rg, ativo, idsituacao, idgenero, nome_mae, data_nascimento, observacao, numero_prontuario, iddepartamento) "
				+ " VALUES " + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);

		stat.setString(1, paciente.getNome());
		stat.setString(2, paciente.getCpf());
		stat.setString(3, paciente.getRg());
		stat.setBoolean(4, paciente.getAtivo());
		stat.setInt(5, paciente.getSituacao().getIdsituacao());
		
		Integer valor = paciente.getTipoSexo().getValue();

		SexoDAO dao = new SexoDAO();
		System.out.println(valor);
		Tipo id_tipo_banco = dao.findId(valor);
		stat.setInt(6, id_tipo_banco.getId());
		
		stat.setString(7, paciente.getNomeMae());
		Date date = Date.valueOf(paciente.getDataNascimento());
		stat.setDate(8, date);
		stat.setString(9, paciente.getObservacao());
		stat.setString(10, paciente.getNumeroProntuario());
		stat.setInt(11, paciente.getIdlocalTransferencia().getIdlocalTransferencia());
		stat.execute();

		ResultSet rs = stat.getGeneratedKeys();

		if (rs.next()) {
			key = rs.getInt(1);
		}

		createAux(key, paciente.getPrecaucoes());

	}

	public void createAux(int id, List<Precaucao> precaucoes) throws SQLException {

		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " + " paciente_precaucao " + " ( idpaciente, idprecaucao ) " + " VALUES " + " (? , ?) ",
				Statement.RETURN_GENERATED_KEYS);

		for (Precaucao precaucao : precaucoes) {

			stat.setInt(1, id);
			stat.setInt(2, precaucao.getIdprecaucao());
			stat.execute();
		}

	}

	@Override
	public void update(Paciente paciente) throws SQLException {
		int key = 0;
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"UPDATE paciente SET " + "  nome = ?," + " cpf = ?," + " rg = ?," + " ativo = ?," + " idsituacao = ?,"
						+ " idgenero = ?, " + " nome_mae = ?," + " data_nascimento = ?," + " observacao = ?,"
						+ " numero_prontuario = ?," + " iddepartamento = ? " + " WHERE " + " idpaciente = ? ");

		stat.setString(1, paciente.getNome());
		stat.setString(2, paciente.getCpf());
		stat.setString(3, paciente.getRg());
		stat.setBoolean(4, paciente.getAtivo());
		stat.setInt(5, paciente.getSituacao().getIdsituacao());
		stat.setInt(6, paciente.getSexo().getIdsexo());
		stat.setString(7, paciente.getNomeMae());
		Date date = Date.valueOf(paciente.getDataNascimento());
		stat.setDate(8, date);
		stat.setString(9, paciente.getObservacao());
		stat.setString(10, paciente.getNumeroProntuario());
		stat.setInt(11, paciente.getIdlocalTransferencia().getIdlocalTransferencia());
		stat.setInt(12, paciente.getIdpaciente());
		stat.execute();

		ResultSet rs = stat.getGeneratedKeys();

		if (rs.next()) {
			key = rs.getInt(1);
		}

		updateAux(key, paciente.getPrecaucoes());

	}

	public void updateAux(int id, List<Precaucao> precaucoes) throws SQLException {

		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"UPDATE paciente_precaucao SET " + " idprecaucao = ? " + " WHERE " + " idpaciente = ? ",
				Statement.RETURN_GENERATED_KEYS);

		for (Precaucao precaucao : precaucoes) {

			stat.setInt(1, precaucao.getIdprecaucao());
			stat.setInt(2, id);
			stat.execute();
		}

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
			PreparedStatement stat = conn.prepareStatement("SELECT " + " idpaciente, " + "nome, " + " cpf, " + " rg, "
					+ " ativo, " + " nome_mae, " + " data_nascimento, " + " observacao, " + " numero_prontuario, "
					+ "  idsituacao  " + "  FROM " + " paciente " + "WHERE " + "  nome ilike ? ");

			stat.setString(1, nome == null ? "%" : "%" + nome + "%");
			ResultSet rs = stat.executeQuery();

			List<Paciente> listaPaciente = new ArrayList<Paciente>();

			while (rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setIdpaciente(rs.getInt("idpaciente"));
				paciente.setNome(rs.getString("nome"));
				paciente.setCpf(rs.getString("cpf"));
				paciente.setRg(rs.getString("rg"));
				paciente.setAtivo(rs.getBoolean("ativo"));
				paciente.setNomeMae(rs.getString("nome_mae"));
				paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				paciente.setObservacao(rs.getString("observacao"));
				paciente.setNumeroProntuario(rs.getString("numero_prontuario"));

//				PrecaucaoDAO dao = new PrecaucaoDAO(conn);
//				paciente.setPrecaucao(dao.findId(paciente.getIdpaciente()));
//				// caso o retorno do telefone seja nulo, instanciar um telefone
//				if (paciente.getPrecaucao() == null)
//					paciente.setPrecaucao(new Precaucao());
//
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

	public List<Paciente> findByNomeMae(String nomeMae) {
		Connection conn = getConnection();
		if (conn == null)
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement("SELECT " + " idpaciente, " + "nome, " + " cpf, " + " rg, "
					+ " ativo, " + " nome_mae, " + " data_nascimento, " + " observacao, " + " numero_prontuario, "
					+ "  idsituacao  " + "  FROM " + " paciente " + "WHERE " + "  nome_mae ilike ? ");

			stat.setString(1, nomeMae == null ? "%" : "%" + nomeMae + "%");
			ResultSet rs = stat.executeQuery();

			List<Paciente> listaPaciente = new ArrayList<Paciente>();

			while (rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setIdpaciente(rs.getInt("idpaciente"));
				paciente.setNome(rs.getString("nome"));
				paciente.setCpf(rs.getString("cpf"));
				paciente.setRg(rs.getString("rg"));
				paciente.setAtivo(rs.getBoolean("ativo"));
				paciente.setNomeMae(rs.getString("nome_mae"));
				paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				paciente.setObservacao(rs.getString("observacao"));
				paciente.setNumeroProntuario(rs.getString("numero_prontuario"));

//				PrecaucaoDAO dao = new PrecaucaoDAO(conn);
//				paciente.setPrecaucao(dao.findId(paciente.getIdpaciente()));
//				// caso o retorno do telefone seja nulo, instanciar um telefone
//				if (paciente.getPrecaucao() == null)
//					paciente.setPrecaucao(new Precaucao());
//
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

	public List<Paciente> findByCpf(String cpf) {
		Connection conn = getConnection();
		if (conn == null)
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement("SELECT " + " idpaciente, " + "nome, " + " cpf, " + " rg, "
					+ " ativo, " + " nome_mae, " + " data_nascimento, " + " observacao, " + " numero_prontuario, "
					+ "  idsituacao  " + "  FROM " + " paciente " + "WHERE " + "  cpf ilike ? ");

			stat.setString(1, cpf == null ? "%" : "%" + cpf + "%");
			ResultSet rs = stat.executeQuery();

			List<Paciente> listaPaciente = new ArrayList<Paciente>();

			while (rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setIdpaciente(rs.getInt("idpaciente"));
				paciente.setNome(rs.getString("nome"));
				paciente.setCpf(rs.getString("cpf"));
				paciente.setRg(rs.getString("rg"));
				paciente.setAtivo(rs.getBoolean("ativo"));
				paciente.setNomeMae(rs.getString("nome_mae"));
				paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				paciente.setObservacao(rs.getString("observacao"));
				paciente.setNumeroProntuario(rs.getString("numero_prontuario"));

//				PrecaucaoDAO dao = new PrecaucaoDAO(conn);
//				paciente.setPrecaucao(dao.findId(paciente.getIdpaciente()));
//				// caso o retorno do telefone seja nulo, instanciar um telefone
//				if (paciente.getPrecaucao() == null)
//					paciente.setPrecaucao(new Precaucao());
//
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

	public List<Paciente> findByNumeroProntuario(String numeroProntuario) {
		Connection conn = getConnection();
		if (conn == null)
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement("SELECT " + " idpaciente, " + "nome, " + " cpf, " + " rg, "
					+ " ativo, " + " nome_mae, " + " data_nascimento, " + " observacao, " + " numero_prontuario, "
					+ "  idsituacao  " + "  FROM " + " paciente " + "WHERE " + "  numero_prontuario ilike ? ");

			stat.setString(1, numeroProntuario == null ? "%" : "%" + numeroProntuario + "%");
			ResultSet rs = stat.executeQuery();

			List<Paciente> listaPaciente = new ArrayList<Paciente>();

			while (rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setIdpaciente(rs.getInt("idpaciente"));
				paciente.setNome(rs.getString("nome"));
				paciente.setCpf(rs.getString("cpf"));
				paciente.setRg(rs.getString("rg"));
				paciente.setAtivo(rs.getBoolean("ativo"));
				paciente.setNomeMae(rs.getString("nome_mae"));
				paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				paciente.setObservacao(rs.getString("observacao"));
				paciente.setNumeroProntuario(rs.getString("numero_prontuario"));

//				PrecaucaoDAO dao = new PrecaucaoDAO(conn);
//				paciente.setPrecaucao(dao.findId(paciente.getIdpaciente()));
//				// caso o retorno do telefone seja nulo, instanciar um telefone
//				if (paciente.getPrecaucao() == null)
//					paciente.setPrecaucao(new Precaucao());
//
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
					"SELECT " + " idpaciente, " + "nome," + " cpf," + " rg," + " ativo," + " nome_mae," + " idsituacao,"
							+ " data_nascimento," + " observacao," + " numero_prontuario  " + "FROM " + "  paciente ");

			ResultSet rs = stat.executeQuery();

			List<Paciente> listaPaciente = new ArrayList<Paciente>();

			while (rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setIdpaciente(rs.getInt("idpaciente"));
				paciente.setNome(rs.getString("nome"));
				paciente.setCpf(rs.getString("cpf"));
				paciente.setRg(rs.getString("rg"));
				paciente.setAtivo(rs.getBoolean("ativo"));
				paciente.setNomeMae(rs.getString("nome_mae"));
				paciente.setDataNascimento(
						rs.getDate("data_nascimento") == null ? null : (rs.getDate("data_nascimento").toLocalDate()));
				paciente.setObservacao(rs.getString("observacao"));
				paciente.setNumeroProntuario(rs.getString("numero_prontuario"));

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
			PreparedStatement stat = conn.prepareStatement("SELECT " + " idpaciente, " + " nome," + " cpf," + " rg,"
					+ " ativo," + " nome_mae," + " idsituacao," + " idgenero, " + " data_nascimento," + " observacao,"
					+ " numero_prontuario," + " iddepartamento " + " FROM " + "  paciente "
					+ "  WHERE idpaciente = ? ");

			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();

			Paciente paciente = null;

			if (rs.next()) {
				paciente = new Paciente();
				paciente.setIdpaciente(rs.getInt("idpaciente"));
				paciente.setNome(rs.getString("nome"));
				paciente.setCpf(rs.getString("cpf"));
				paciente.setRg(rs.getString("rg"));
				paciente.setAtivo(rs.getBoolean("ativo"));
				paciente.setNomeMae(rs.getString("nome_mae"));
				paciente.setSituacao(new Situacao());
				paciente.getSituacao().setIdsituacao(rs.getInt("idsituacao"));
				paciente.setSexo(new Sexo());
				paciente.getSexo().setIdsexo(rs.getInt("idgenero"));
				paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				paciente.setObservacao(rs.getString("observacao"));
				paciente.setNumeroProntuario(rs.getString("numero_prontuario"));
				paciente.setIdlocalTransferencia(new Departamento());
				paciente.getIdlocalTransferencia().setIdlocalTransferencia(rs.getInt("iddepartamento"));
			}

			return paciente;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Paciente> findAllParaTransferencia() {
		Connection conn = getConnection();
		if (conn == null)
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement("SELECT " + " idpaciente, " + "nome," + " cpf," + " rg,"
					+ " ativo," + " nome_mae," + " idsituacao," + " data_nascimento," + " observacao,"
					+ " iddepartamento," + " numero_prontuario  " + "FROM " + "  paciente ");

			ResultSet rs = stat.executeQuery();

			List<Paciente> listaPaciente = new ArrayList<Paciente>();

			while (rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setIdpaciente(rs.getInt("idpaciente"));
				paciente.setNome(rs.getString("nome"));
				paciente.setCpf(rs.getString("cpf"));
				paciente.setRg(rs.getString("rg"));
				paciente.setAtivo(rs.getBoolean("ativo"));
				paciente.setNomeMae(rs.getString("nome_mae"));
				paciente.setDataNascimento(
						rs.getDate("data_nascimento") == null ? null : (rs.getDate("data_nascimento").toLocalDate()));
				paciente.setObservacao(rs.getString("observacao"));
				Departamento depaux = new Departamento();
				depaux.setIdlocalTransferencia(rs.getInt("iddepartamento"));
				paciente.setIdlocalTransferencia(depaux);
				paciente.setNumeroProntuario(rs.getString("numero_prontuario"));

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

	public void updateTrasnferencia(Integer idLocalDeTransferencia, Integer idPaciente) throws SQLException {
		Connection conn = getConnection();

		try {
			PreparedStatement stat = conn
					.prepareStatement("UPDATE paciente SET " + " iddepartamento = ?" + " WHERE" + " idpaciente = ?");
			stat.setInt(1, idLocalDeTransferencia);
			stat.setInt(2, idPaciente);
			stat.execute();
		} catch (SQLException e) {
			Util.addMessageError("erro banco de dados não foi possivel");
			e.printStackTrace();
		}
	}

}