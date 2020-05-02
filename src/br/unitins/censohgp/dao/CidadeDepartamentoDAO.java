package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.unitins.censohgp.model.CidadeDepartamento;

public class CidadeDepartamentoDAO extends DAO<CidadeDepartamento>{

	
		


			public CidadeDepartamentoDAO(Connection conn) {
				super(conn);
			}
			

			public CidadeDepartamentoDAO() {
				super(null);
			}
			

			@Override
			public void create(CidadeDepartamento entity) throws SQLException {
				Connection conn = getConnection();

				PreparedStatement stat = conn.prepareStatement(
						"INSERT INTO " + " public.cidade " + " ( idcidade, cidade ) " + "VALUES " + " ( ?, ? ) ");
				stat.setInt(1, entity.getIdcidade());
				stat.setString(2, entity.getCidade());

				stat.execute();
				stat.close();

			}

			@Override
			public void update(CidadeDepartamento entity) throws SQLException {
				// TODO Auto-generated method stub
			}

			@Override
			public void delete(int id) throws SQLException {
				Connection conn = getConnection();

				PreparedStatement stat = conn.prepareStatement(
						"DELETE FROM public.cidade WHERE idcidade =  ?");
				stat.setInt(1, id);

				stat.execute();
				stat.close();
			}

			@Override
			public List<CidadeDepartamento> findAll() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public CidadeDepartamento findById(Integer id) {
				Connection conn = getConnection();
				
				try {
					PreparedStatement stat = conn.prepareStatement(
							"SELECT " +
							"  idcidade, " +
							"  cidade  " +
							"FROM " +
							"  public.cidade" +
							"WHERE idcidade = ? ");
					
					stat.setInt(1, id);
					
					ResultSet rs = stat.executeQuery();
					
					CidadeDepartamento cidade = null;
					
					if(rs.next()) {
						cidade = new CidadeDepartamento();
						cidade.setIdcidade(rs.getInt("idcidade"));
						cidade.setCidade(rs.getString("cidade"));
					}
					
					return cidade;
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}


		}





