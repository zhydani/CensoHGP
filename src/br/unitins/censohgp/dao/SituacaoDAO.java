	package br.unitins.censohgp.dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.Situacao;
public class SituacaoDAO extends DAO<Situacao>{


				public SituacaoDAO(Connection conn) {
					super(conn);
				}
				

				public SituacaoDAO() {
					super(null);
				}
				

				@Override
				public void create(Situacao entity) throws SQLException {

				}

				@Override
				public void update(Situacao entity) throws SQLException {
					// TODO Auto-generated method stub
				}

				@Override
				public void delete(int id) throws SQLException {
				}

				@Override
				public List<Situacao> findAll() {
					Connection conn = getConnection();
					if (conn == null) 
						return null;

					try {
						PreparedStatement stat = conn.prepareStatement(
								"SELECT " +
										" idsituacao, " +
										" nome  " +
										" FROM " +
										"  situacao_paciente ");

						ResultSet rs = stat.executeQuery();

						List<Situacao> listaSituacao = new ArrayList<Situacao>();

						while(rs.next()) {
							Situacao sit = new Situacao();
							sit.setIdsituacao(rs.getInt("idsituacao"));
							sit.setNome(rs.getString("nome"));
							
							listaSituacao.add(sit);

						}			 			

						if (listaSituacao.isEmpty())
							return null;
						return listaSituacao;

					} catch (SQLException e) {
						e.printStackTrace();
					}
					return null;	
				}

}
