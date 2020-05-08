package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.model.CidadeDepartamento;
import br.unitins.censohgp.model.Departamento;
import br.unitins.censohgp.model.EstadoDepartamento;
import br.unitins.censohgp.model.StatusDepartamento;
public class DepartamentoDAO extends DAO<Departamento> {
	




		
		public DepartamentoDAO(Connection conn) {
			super(conn);
		}
		
		public DepartamentoDAO() {
			// tchê papai ... cria uma nova conexao
			super(null);
		}
		

		@Override
		public void create(Departamento dep) throws SQLException {
			
			Connection  conn = getConnection();
				
			PreparedStatement stat = conn.prepareStatement(
					"INSERT INTO " +
				    " public.departamento " +
				    " (nome_hospital, numero_leitos, nome_departamento, ativo) " +
					"VALUES " +
				    " (?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
			stat.setString(1, dep.getNomeHospital());
			stat.setInt(2, dep.getNumeroLeitos());
			stat.setString(3, dep.getNomeDepartamento());
			stat.setInt(4, dep.getAtivo().getValue());
			
			stat.execute();
				
		}

		@Override
		public void update(Departamento dep) throws SQLException {
			Connection  conn = getConnection();
			
			PreparedStatement stat = conn.prepareStatement(
					"UPDATE public.departamento SET " +
				    " nome_hospital = ?, " +
				    " numero_leitos = ?, " +
				    " nome_departamento = ?, " +
				    " ativo = ?, " +
					"WHERE " +
				    " idlocal_transferencia = ? ");
			stat.setString(1, dep.getNomeHospital());
			stat.setInt(2, dep.getNumeroLeitos());
			stat.setString(3, dep.getNomeDepartamento());
			stat.setInt(4, dep.getAtivo().getValue());
			stat.setInt(6, dep.getIdlocalTransferencia());
				
			stat.execute();
				
		}

		@Override
		public void delete(int id) throws SQLException {

			Connection  conn = getConnection();

			
			PreparedStatement stat = conn.prepareStatement(
					"DELETE FROM public.departamento WHERE idlocal_transferencia = ?");
			stat.setInt(1, id);
			
			stat.execute();
				
		}

		@Override
		public List<Departamento> findAll() {
			Connection conn = getConnection();
			if (conn == null) 
				return null;
			
			try {
				PreparedStatement stat = conn.prepareStatement(
						"SELECT " +
						"  iddepartamento, " +
						"  nome_hospital, " +
						"  numero_leitos, " +
						"  nome_departamento, " +
						"  ativo " +
						"FROM " +
						"  public.departamento ");
				ResultSet rs = stat.executeQuery();
				
				List<Departamento> listaDepartamento = new ArrayList<Departamento>();
				
				while(rs.next()) {
					Departamento departamento = new Departamento();
					departamento.setIdlocalTransferencia(rs.getInt("iddepartamento"));
					departamento.setNomeHospital(rs.getString("nome_hospital"));
					departamento.setNumeroLeitos(rs.getInt("numero_leitos"));
					departamento.setNomeDepartamento(rs.getString("nome_departamento"));
					departamento.setAtivo(StatusDepartamento.valueOf((rs.getInt("ativo"))));
					
					listaDepartamento.add(departamento);
				}
				
				if (listaDepartamento.isEmpty())
					return null;
				return listaDepartamento;
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		public Departamento findId(Integer id) {
			Connection conn = getConnection();
			if (conn == null) 
				return null;
			
			try {
				PreparedStatement stat = conn.prepareStatement(
						"SELECT " +
								"  iddepartamento, " +
								"  nome_hospital, " +
								"  numero_leitos, " +
								"  nome_departamento, " +
								"  ativo " +
								"FROM " +
								"  public.departamento " +
						"WHERE idlocal_transferencia = ? ");
				
				stat.setInt(1, id);
				
				ResultSet rs = stat.executeQuery();
				
				Departamento departamento = null;
				
				if(rs.next()) {
					departamento = new Departamento();
					departamento.setIdlocalTransferencia(rs.getInt("iddepartamento"));
					departamento.setNomeHospital(rs.getString("nome_hospital"));
					departamento.setNumeroLeitos(rs.getInt("numero_leitos"));
					departamento.setNomeDepartamento(rs.getString("nome_departamento"));
					departamento.setAtivo(StatusDepartamento.valueOf((rs.getInt("ativo"))));
					
					
				}
				
				return departamento;
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

	}



