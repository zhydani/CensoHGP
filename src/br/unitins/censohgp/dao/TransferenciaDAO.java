package br.unitins.censohgp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

import br.unitins.censohgp.application.Util;
import br.unitins.censohgp.model.HistoricoTransferencia;

public class TransferenciaDAO extends DAO<HistoricoTransferencia> {

	public TransferenciaDAO(Connection conn) {
		super(conn);
	}
	
	public TransferenciaDAO() {
		// tchê papai ... cria uma nova conexao
		super(null);
	}

	@Override
	public void create(HistoricoTransferencia entity) throws SQLException {
		Connection conn = getConnection();
		try {
			PreparedStatement stat = conn.prepareStatement(
					"INSERT INTO " +
					" public.historico_transferencia" +
					" (data_hora, idtipo_transferencia, idlocal_origem, idlocal_destino, idpaciente, idusuario, observacao)" +
					" VALUES " +
				    " (?, ?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
			LocalDate dat1 = LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonthValue(),LocalDate.now().getDayOfMonth());
			Date dat = Date.valueOf(dat1);
			stat.setDate(1, dat);
			stat.setInt(2, entity.getIdTipoDeTransferencia());
			stat.setInt(3, entity.getIdLocalOrigem());
			stat.setInt(4, entity.getIdLocalDestino());
			stat.setInt(5, entity.getIdPaciente());
			stat.setInt(6, entity.getIdUsuario());
			if(entity.getObservasao().equals("null") || entity.getObservasao().isEmpty()) {
				entity.setObservasao(" ");
			}
			stat.setString(7, entity.getObservasao());
			stat.execute();
		} catch (SQLException e) {
			System.out.println(e);
			Util.addMessageError("erro banco de dados não foi possivel");
			// TODO: handle exception
		}
	}

	public void createSemOrigem(HistoricoTransferencia entity) throws SQLException {
		Connection conn = getConnection();
		try {
			PreparedStatement stat = conn.prepareStatement(
					"INSERT INTO " +
					" public.historico_transferencia" +
					" (data_hora, idtipo_transferencia, idlocal_destino, idpaciente, idusuario, observacao)" +
					" VALUES " +
				    " (?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
			LocalDate dat1 = LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonthValue(),LocalDate.now().getDayOfMonth());
			Date dat = Date.valueOf(dat1);
			stat.setDate(1, dat);
			stat.setInt(2, entity.getIdTipoDeTransferencia());
			stat.setInt(3, entity.getIdLocalDestino());
			stat.setInt(4, entity.getIdPaciente());
			stat.setInt(5, entity.getIdUsuario());
			if(entity.getObservasao().equals("null") || entity.getObservasao().isEmpty()) {
				entity.setObservasao(" ");
			}
			stat.setString(6, entity.getObservasao());
			stat.execute();
		} catch (SQLException e) {
			System.out.println(e);
			Util.addMessageError("erro banco de dados não foi possivel");
			// TODO: handle exception
		}
	}
	@Override
	public void update(HistoricoTransferencia entity) throws SQLException {
		//não é necessario essa funcionalidade para o programa
	}

	@Override
	public void delete(int id) throws SQLException {
		//não é necessario essa funcionalidade para o programa
	}

	@Override
	public List<HistoricoTransferencia> findAll() {
		Connection conn = getConnection();
		if (conn == null) {
			return null;
		}
		try {
			PreparedStatement stat = conn.prepareStatement(
			"SELECT " +
			" idtransferencia, " +
			" idtipo_transferencia, " +
			" idlocal_origem, " +
			" idlocal_destino, " +
			" data_hora, " +
			" idpaciente, " +
			" idusuario," +
			" observacao" +
			" FROM" +
			" public.historico_transferencia");
			ResultSet rs = stat.executeQuery();
			
			List<HistoricoTransferencia> historico = new ArrayList<HistoricoTransferencia>();
			
			while(rs.next()) {
				HistoricoTransferencia aux = new HistoricoTransferencia();
				aux.setIdTransferencia(rs.getInt("idtransferencia"));
				aux.setIdTipoDeTransferencia(rs.getInt("idtipo_transferencia"));
				aux.setIdLocalOrigem(rs.getInt("idlocal_origem"));
				aux.setIdLocalDestino(rs.getInt("idlocal_destino"));
				//tem q ter um campo para a data no objeto principal
				//aux.setDataHora(rs.getDate("data_hora"));
				//aux.set
				aux.setIdPaciente(rs.getInt("idpaciente"));
				aux.setIdUsuario(rs.getInt("idusuario"));
				aux.setObservasao(rs.getString("observacao"));
				historico.add(aux);
			}
			if(historico.isEmpty())
				return null;
			return historico;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
