package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.Tipo;
import br.unitins.censohgp.model.Usuario;

public class UsuarioDAO extends DAO<Usuario> {
//TODO: Adicionar o atributo matrícula e adicionar os tipo no ENUM TIPO
	public UsuarioDAO(Connection conn) {
		super(conn);
	}

	public UsuarioDAO() {
		super(null);
	}

	public Usuario login(String login, String senha) {

		Connection conn = getConnection();

		try {
			PreparedStatement stat = conn.prepareStatement("SELECT " + "  id, " + "  nome, " + "  login, " + "  senha, "
					+ "  perfil " + "FROM " + "  public.usuario " + "WHERE login = ? AND senha = ? ");

			stat.setString(1, login);
			stat.setString(2, senha);

			ResultSet rs = stat.executeQuery();

			Usuario usuario = null;

			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
//				usuario.setPerfil(Tipo.valueOf(rs.getInt("perfil")));
			}

			return usuario;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void create(Usuario usuario) throws SQLException {

		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " + "public.usuario " + " (nome, email, senha, tipo) " + "VALUES " + " (?, ?, ?, ?) ",
				Statement.RETURN_GENERATED_KEYS);
		stat.setString(1, usuario.getNome());
		stat.setString(2, usuario.getEmail());
		stat.setString(3, usuario.getSenha());
//		stat.setInt(4, usuario.getTipo().getValue());

		stat.execute();

		// obtendo o id gerado pela tabela do banco de dados
		ResultSet rs = stat.getGeneratedKeys();
		rs.next();

	}

	@Override
	public void update(Usuario usuario) throws SQLException {
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement("UPDATE public.usuario SET " + " nome = ?, " + " login = ?, "
				+ " senha = ?, " + " perfil = ? " + "WHERE " + " id = ? ");
		stat.setString(1, usuario.getNome());
		stat.setString(2, usuario.getEmail());
		stat.setString(3, usuario.getSenha());
//		stat.setInt(4, usuario.getTipo().getValue());
		stat.setInt(5, usuario.getId());

		stat.execute();

	}

	@Override
	public void delete(int id) throws SQLException {

		Connection conn = getConnection();
//		// deletando o nascimento (pq possui um relacionamento de fk)
//		// passando o conn para manter a mesma transacao
//		NascimentoDAO dao = new NascimentoDAO(conn);
//		// nascimento tem um relecionamento 1 pra 1, ou seja, o id do usuario eh o mesmo
//		// do nascimento.
//		dao.delete(id);
//
//		ComposicaoDAO dao2 = new ComposicaoDAO(conn);
//		dao2.delete(id);

		// deletando o usuario
		PreparedStatement stat = conn.prepareStatement("DELETE FROM public.usuario WHERE id = ?");
		stat.setInt(1, id);

		stat.execute();

	}

	@Override
	public List<Usuario> findAll() {
		Connection conn = getConnection();
		if (conn == null)
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement("SELECT " + "  u.id, " + "  u.nome, " + "  u.login, "
					+ "  u.senha, " + "  u.perfil, n.dia, n.mes, n.ano " + "FROM "
					+ " usuario u, nascimento n WHERE u.id = n.id");
			ResultSet rs = stat.executeQuery();

			List<Usuario> listaUsuario = new ArrayList<Usuario>();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
//				usuario.setPerfil(Tipo.valueOf(rs.getInt("tipo")));
				listaUsuario.add(usuario);
			}

			if (listaUsuario.isEmpty())
				return null;
			return listaUsuario;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Usuario findId(Integer id) {
		Connection conn = getConnection();
		if (conn == null)
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement("SELECT " + "  id, " + "  nome, " + "  login, " + "  senha, "
					+ "  perfil " + "FROM " + "  public.usuario " + "WHERE id = ? ");

			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();

			Usuario usuario = null;

			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
//				usuario.setPerfil(Tipo.valueOf(rs.getInt("tipo")));


			}

			return usuario;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
