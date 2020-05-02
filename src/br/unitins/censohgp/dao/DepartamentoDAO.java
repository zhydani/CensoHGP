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
				    "public.departamento " +
				    " (nomehospital, numeroleitos, nomedepartamento, ativo) " +
					"VALUES " +
				    " (?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
			stat.setString(1, dep.getNomeHospital());
			stat.setInt(2, dep.getNumeroLeitos());
			stat.setString(3, dep.getNomeDepartamento());
			stat.setInt(4, dep.getAtivo().getValue());
			
			stat.execute();
			
			// obtendo o id gerado pela tabela do banco de dados
			ResultSet rs = stat.getGeneratedKeys();
			rs.next();
			dep.getCidade().setIdcidade(rs.getInt("idcidade"));
			CidadeDepartamentoDAO dao = new CidadeDepartamentoDAO(conn);
			dao.create(dep.getCidade());
			
			dep.getEstado().setIdestado(rs.getInt("idestado"));
			EstadoDepartamentoDAO dao1 = new EstadoDepartamentoDAO(conn);
			dao1.create(dep.getEstado());

				
		}

		@Override
		public void update(Departamento dep) throws SQLException {
			Connection  conn = getConnection();
			
			PreparedStatement stat = conn.prepareStatement(
					"UPDATE public.departamento SET " +
				    " nomehospital = ?, " +
				    " numeroleitos = ?, " +
				    " nomedepartamento = ?, " +
				    " ativo = ?, " +
					"WHERE " +
				    " idlocaltransferencia = ? ");
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

			CidadeDepartamentoDAO dao = new CidadeDepartamentoDAO(conn);
			dao.delete(id);
			EstadoDepartamentoDAO dao1 = new EstadoDepartamentoDAO(conn);
			dao1.delete(id);
			
			PreparedStatement stat = conn.prepareStatement(
					"DELETE FROM public.departamento WHERE idlocaltransferencia = ?");
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
						"  nomehospital, " +
						"  numeroleitos, " +
						"  nomedepartamento, " +
						"  ativo " +
						"FROM " +
						"  public.departamento ");
				ResultSet rs = stat.executeQuery();
				
				List<Departamento> listaDepartamento = new ArrayList<Departamento>();
				
				while(rs.next()) {
					Departamento departamento = new Departamento();
					departamento.setIdlocalTransferencia(rs.getInt("id"));
					departamento.setNomeHospital(rs.getString("nomehospital"));
					departamento.setNumeroLeitos(rs.getInt("numeroleitos"));
					departamento.setNomeDepartamento(rs.getString("nomedepartamento"));
					departamento.setAtivo(StatusDepartamento.valueOf((rs.getInt("ativo"))));
					
					CidadeDepartamentoDAO dep = new CidadeDepartamentoDAO(conn);
					departamento.setCidade(dep.findById(departamento.getIdlocalTransferencia()));
					if (departamento.getCidade() == null)
						departamento.setCidade(new CidadeDepartamento());

					EstadoDepartamentoDAO depa = new EstadoDepartamentoDAO(conn);
					departamento.setEstado(depa.findById(departamento.getIdlocalTransferencia()));
					if (departamento.getEstado() == null)
						departamento.setEstado(new EstadoDepartamento());

					
					
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
								"  nomehospital, " +
								"  numeroleitos, " +
								"  nomedepartamento, " +
								"  ativo " +
								"FROM " +
								"  public.departamento " +
						"WHERE idlocaltransferencia = ? ");
				
				stat.setInt(1, id);
				
				ResultSet rs = stat.executeQuery();
				
				Departamento departamento = null;
				
				if(rs.next()) {
					departamento = new Departamento();
					departamento.setIdlocalTransferencia(rs.getInt("id"));
					departamento.setNomeHospital(rs.getString("nomehospital"));
					departamento.setNumeroLeitos(rs.getInt("numeroleitos"));
					departamento.setNomeDepartamento(rs.getString("nomedepartamento"));
					departamento.setAtivo(StatusDepartamento.valueOf((rs.getInt("ativo"))));
					
					CidadeDepartamentoDAO dep = new CidadeDepartamentoDAO(conn);
					departamento.setCidade(dep.findById(departamento.getIdlocalTransferencia()));
					if (departamento.getCidade() == null)
						departamento.setCidade(new CidadeDepartamento());

					EstadoDepartamentoDAO depa = new EstadoDepartamentoDAO(conn);
					departamento.setEstado(depa.findById(departamento.getIdlocalTransferencia()));
					if (departamento.getEstado() == null)
						departamento.setEstado(new EstadoDepartamento());
					
				}
				
				return departamento;
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

	}



