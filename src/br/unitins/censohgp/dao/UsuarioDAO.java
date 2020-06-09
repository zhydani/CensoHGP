package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.application.Util;
import br.unitins.censohgp.model.Tipo;
import br.unitins.censohgp.model.Usuario;

public class UsuarioDAO extends DAO<Usuario> {

	public String tipoBusca;

	public UsuarioDAO(Connection conn) {
		super(conn);
	}

	public UsuarioDAO() {
		super(null);
	}

//	SELECT idusuario, nome, senha, idtipo_usuario, ativo, email, matricula
//	FROM public.usuario;

	public Usuario login(String matricula, String senha) {

		Connection conn = getConnection();

		try {
			PreparedStatement stat = conn.prepareStatement("SELECT " + "  idusuario, " + "  nome, " + "  senha, "
					+ "  idtipo_usuario, " + "  ativo, " + "  email, " + "  matricula " + "FROM " + "  public.usuario "
					+ "WHERE matricula = ? AND senha = ?");

			stat.setString(1, matricula);
			stat.setString(2, senha);

			ResultSet rs = stat.executeQuery();

			Usuario usuario = null;

			if (rs.next()) {
				usuario = new Usuario();

				usuario.setId(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha1(rs.getString("senha"));
				usuario.setTipo(new Tipo());
				usuario.getTipo().setId(rs.getInt("idtipo_usuario"));
				usuario.setAtivo(rs.getBoolean("ativo"));
				usuario.setEmail(rs.getString("email"));
				usuario.setMatricula("matricula");
			}

			return usuario;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

//	INSERT INTO public.usuario(
//			idusuario, nome, senha, idtipo_usuario, ativo, email, matricula)
//			VALUES (?, ?, ?, ?, ?, ?, ?);

	@Override
	public void create(Usuario usuario) throws SQLException {

		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement("INSERT INTO " + " usuario "
				+ " (nome, senha, idtipo_usuario, ativo, email, matricula) " + " VALUES " + " (?, ?, ?, ?, ?, ?) ",
				Statement.RETURN_GENERATED_KEYS);

		String senha1 = usuario.getSenha1();
		String senha2 = usuario.getSenha2();

		Integer valor = usuario.getTipousuario().getValue();

		TipoDAO dao = new TipoDAO();
//		Integer id_tipo = usuario.getTipo().getId();
		System.out.println(valor);
		Tipo id_tipo_banco = dao.findId(valor);
		stat.setString(1, usuario.getNome());
		if (senha1.equals(senha2)) {

			stat.setString(2, usuario.getSenha1());

		} else {
			Util.addMessageError("O campo confirmar senha é diferente da senha digitada.");
		}

		stat.setInt(3, id_tipo_banco.getId());
		stat.setBoolean(4, usuario.getAtivo());
		stat.setString(5, usuario.getEmail());
		stat.setString(6, usuario.getMatricula());
		stat.execute();

	}

//	UPDATE public.usuario
//	SET idusuario=?, nome=?, senha=?, idtipo_usuario=?, ativo=?, email=?, matricula=?
//	WHERE <condition>;

	@Override
	public void update(Usuario usuario) throws SQLException {
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"UPDATE public.usuario SET " + " nome = ?, " + " senha = ?, " + " idtipo_usuario = ?, " + " ativo = ?, "
						+ " email = ?, " + " matricula = ?, " + "WHERE " + " idusuario = ? ");
		stat.setString(1, usuario.getNome());

		String senha1 = usuario.getSenha1();
		String senha2 = usuario.getSenha2();

		if (senha1.equals(senha2)) {

			stat.setString(2, usuario.getSenha1());

		} else {
			Util.addMessageError("O campo confirmar senha é diferente da senha digitada.");
		}

		stat.setInt(3, usuario.getTipo().getId());
		stat.setBoolean(4, usuario.getAtivo());
		stat.setString(5, usuario.getEmail());
		stat.setString(6, usuario.getMatricula());

		stat.execute();

	}

	@Override
	public void delete(int id) throws SQLException {

		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement("DELETE FROM public.usuario WHERE idusuario = ?");
		stat.setInt(1, id);

		stat.execute();

	}

	@Override
	public List<Usuario> findAll() {
		Connection conn = getConnection();
		if (conn == null)
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement("SELECT " + "  u.idusuario, " + "  u.nome, " + "  u.senha, "
					+ "  u.idtipo_usuario, " + "  u.ativo," + "  u.email" + "  u.matricula" + "FROM " + " usuario u ");
			ResultSet rs = stat.executeQuery();

			List<Usuario> listaUsuario = new ArrayList<Usuario>();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha1(rs.getString("senha"));
				usuario.getTipo().setId(rs.getInt("idtipo_usuario"));
				usuario.setAtivo(rs.getBoolean("ativo"));
				usuario.setEmail(rs.getString("email"));
				usuario.setMatricula(rs.getString("matricula"));
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

	public static String quote(String s) {
		return new StringBuilder().append('\'').append(s).append('\'').toString();
	}

	public List<Usuario> findByName(String nome, String matricula) {
		Connection conn = getConnection();
		if (conn == null)
			return null;
		try {

			if (nome.isEmpty()) {
				if (matricula.isEmpty()) {
					tipoBusca = "Select idusuario, nome, matricula, ativo, email from public.usuario";
				} else {
					tipoBusca = "Select idusuario, nome, matricula, ativo, email from public.usuario where matricula ilike "
							+ quote("%" + matricula + "%");
				}

			} else {
				if (matricula.isEmpty()) {
					tipoBusca = "Select idusuario, nome, matricula, ativo, email from public.usuario where nome ilike "
							+ quote("%" + nome + "%");
				} else {
					tipoBusca = "Select idusuario, nome, matricula, ativo, email from public.usuario where matricula ilike "
							+ quote("%" + matricula + "%") + " and nome ilike " + quote("%" + nome + "%");
				}
			}
			PreparedStatement stat = conn.prepareStatement(tipoBusca);
			ResultSet rs = stat.executeQuery();
			List<Usuario> listaUsuario = new ArrayList<Usuario>();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario = new Usuario();
				usuario.setId(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setMatricula(rs.getString("matricula"));
				usuario.setEmail(rs.getString("email"));
				usuario.setAtivo(rs.getBoolean("ativo"));

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
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " + "  idusuario, " + "  matricula, " + "  nome, " + "  senha, " + "  idtipo_usuario, "
							+ "  ativo, " + "  email " + "FROM " + "  public.usuario " + "WHERE idusuario = ? ");

			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();

			Usuario usuario = null;

			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("idusuario"));
				usuario.setMatricula("matricula");
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha1(rs.getString("senha"));
				usuario.getTipo().setId(rs.getInt("idtipo_usuario"));
				usuario.setAtivo(rs.getBoolean("ativo"));
				usuario.setEmail(rs.getString("email"));

			}

			return usuario;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
