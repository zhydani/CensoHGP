package br.unitins.censohgp.controller;

import java.sql.SQLException;

import br.unitins.censohgp.application.Util;
import br.unitins.censohgp.dao.DAO;
import br.unitins.censohgp.dao.PacienteDAO;
import br.unitins.censohgp.dao.TransferenciaDAO;
import br.unitins.censohgp.model.Departamento;
import br.unitins.censohgp.model.HistoricoTransferencia;

public class AuxiliarTransferenciaController {

	public void transferir(Integer tipoTranferencia,Departamento idLocalOrigem,
			Integer idLocalDestino,Integer idPaciente,Integer idUsuario,String Observacao) {
		HistoricoTransferencia trans = new HistoricoTransferencia();
		TransferenciaDAO dao = new TransferenciaDAO();
		PacienteDAO dao2 = new PacienteDAO();
		trans.setIdTipoDeTransferencia(tipoTranferencia);
		trans.setIdLocalOrigem(idLocalOrigem.getIdlocalTransferencia());
		trans.setIdLocalDestino(idLocalDestino);
		trans.setIdPaciente(idPaciente);
		trans.setIdUsuario(idUsuario);
		trans.setObservasao(Observacao);
		try {
			if(idLocalOrigem.getIdlocalTransferencia().equals(0)) {
				dao.createSemOrigem(trans);
				dao.getConnection().commit();
				dao2.updateTrasnferencia(idLocalDestino, idPaciente);
				dao2.getConnection().commit();
				Util.addMessageInfo("Transferência realizada com sucesso.");
			}else {
			dao.create(trans);
			dao.getConnection().commit();
			dao2.updateTrasnferencia(idLocalDestino, idPaciente);
			dao2.getConnection().commit();
			Util.addMessageInfo("Transferência realizada com sucesso.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Util.addMessageError("Erro ao realizar transferência.");
			e.printStackTrace();
		}
	}

}
