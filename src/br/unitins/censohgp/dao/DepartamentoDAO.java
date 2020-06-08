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
		
	public String tipoBusca; //---Adicionado em 21/05/2020 por Iury
		
	public DepartamentoDAO(Connection conn) {
		super(conn);
	}
		
	public DepartamentoDAO() {
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
				    " iddepartamento = ? ");
			stat.setString(1, dep.getNomeHospital());
			stat.setInt(2, dep.getNumeroLeitos());
			stat.setString(3, dep.getNomeDepartamento());
			stat.setInt(4, dep.getAtivo().getValue());
			stat.setInt(6, dep.getIdlocalTransferencia());
				
			stat.execute();
				
		}

		@Override
		public void delete(int iddepartamento) throws SQLException {

			Connection  conn = getConnection();
			
			PreparedStatement stat = conn.prepareStatement(
					"DELETE FROM public.departamento WHERE iddepartamento = ?");
			stat.setInt(1, iddepartamento);
			
			stat.execute();
				
		}

		//---TRECHO ADICIONADO EM 21/05/2020 POR IURY	
		//Concatenar string tipobusca para o select 
		public static String quote(String s) {
		    return new StringBuilder()
		        .append('\'')
		        .append(s)
		        .append('\'')
		        .toString();
		}
		public List<Departamento> findByName(String nomehospital, String nomedepartamento) {
			Connection conn = getConnection();
			if (conn == null) 
				return null;
			try {
				
				if(nomedepartamento.isEmpty()) {
					if(nomehospital.isEmpty()) {
						tipoBusca = "Select iddepartamento, nome_hospital, numero_leitos, nome_departamento from public.departamento"; 
					}else {
						tipoBusca = "Select iddepartamento, nome_hospital, numero_leitos, nome_departamento from public.departamento where nome_hospital = " + quote(nomehospital);
					}
					
				}else {
					if(nomehospital.isEmpty()) {
						tipoBusca = "Select iddepartamento, nome_hospital, numero_leitos, nome_departamento from public.departamento where nome_departamento = " + quote(nomedepartamento);
					}else {
						tipoBusca = "Select iddepartamento, nome_hospital, numero_leitos, nome_departamento from public.departamento where nome_hospital = "+quote(nomehospital)+" and nome_departamento = "+quote(nomedepartamento);
					}		
				}
				PreparedStatement stat = conn.prepareStatement(tipoBusca);
				ResultSet rs = stat.executeQuery();
				List<Departamento> listaDepartamento = new ArrayList<Departamento>();
				while(rs.next()) {
					Departamento departamento = new Departamento();
					departamento = new Departamento();
					
					departamento.setIdlocalTransferencia(rs.getInt("iddepartamento"));
					departamento.setNomeHospital(rs.getString("nome_hospital"));
					departamento.setNomeDepartamento(rs.getString("nome_departamento"));
					departamento.setNumeroLeitos(rs.getInt("numero_leitos"));
					
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
		//----FIM TRECHO ADICIONADO EM 21/05/2020 POR IURY
		
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
						"  nome_departamento " +
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

		public Departamento findById(Integer id) {
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
						"WHERE iddepartamento = ? ");
				
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
		
		public List<Departamento> findAllTransferencia() {
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
						"  ativo, " +
						"  idcidade_estado" +
						" FROM " +
						"  public.departamento ");
				ResultSet rs = stat.executeQuery();
				
				List<Departamento> listaDepartamento = new ArrayList<Departamento>();
				
				while(rs.next()) {
					Integer var = 0;
					Departamento departamento = new Departamento();
					departamento.setIdlocalTransferencia(rs.getInt("iddepartamento"));
					departamento.setNomeHospital(rs.getString("nome_hospital"));
					departamento.setNumeroLeitos(rs.getInt("numero_leitos"));
					departamento.setNomeDepartamento(rs.getString("nome_departamento"));
					if(departamento.getNomeDepartamento() == null) {
						departamento.setNomeDepartamento(new String());
					}
					//observação
					departamento.setAtivo(StatusDepartamento.valueOf((rs.getBoolean("ativo"))));
					var = rs.getInt("idcidade_estado");
					
					CidadeDepartamentoDAO dep = new CidadeDepartamentoDAO(conn);
					departamento.setCidade(dep.findByIdParaTransferencia(var));
					if (departamento.getCidade() == null) {
						departamento.setCidade(new CidadeDepartamento());
					}
					//fazer captura por classe
					if(!(departamento.getCidade().getIdcidade() == null)) {
						EstadoDepartamentoDAO depa = new EstadoDepartamentoDAO(conn);
						EstadoDepartamento estaux = departamento.getCidade().getEstado();
						departamento.setEstado(depa.findByIdParaTransferencia(estaux.getIdestado()));
						if (departamento.getEstado() == null) {
							System.out.println("passo aq porque estado está nullo");
							departamento.setEstado(new EstadoDepartamento());
						}
					}
					listaDepartamento.add(departamento);
				}
				if (listaDepartamento.isEmpty()) {
					System.out.println("passa aq pq lista de departamento ta nula");
					return null;
				}
				return listaDepartamento;
			
			} catch (SQLException e) {
				System.out.println("problma de sql");
				e.printStackTrace();
			}
			System.out.println("ta chegando aq e retornando null");
			return null;
		}

	}



