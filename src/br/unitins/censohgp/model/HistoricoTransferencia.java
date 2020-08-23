package br.unitins.censohgp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HistoricoTransferencia {
	
	private Integer idTransferencia;
	private Integer idTipoDeTrnasferencia;
	private Integer idLocalOrigem;
	private LocalDate dataHora;
	private Integer idLocalDestino;
	private Integer idPaciente;
	private Integer idUsuario;
	private String observasao;
	//adicionar uma data para armazenamento aq no model para puchar 
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		try {
			return (HistoricoTransferencia) super.clone();
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
			System.out.println("Erro ao clonar.");
		}
		return null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTransferencia == null) ? 0 : idTransferencia.hashCode());
		result = prime * result + ((idTipoDeTrnasferencia == null) ? 0 : idTipoDeTrnasferencia.hashCode());
		result = prime * result + ((idLocalOrigem == null) ? 0 : idLocalOrigem.hashCode());
		result = prime * result + ((dataHora == null) ? 0 : dataHora.hashCode());
		result = prime * result + ((idLocalDestino == null) ? 0 : idLocalDestino.hashCode());
		result = prime * result + ((idPaciente == null) ? 0 : idPaciente.hashCode());
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		result = prime * result + ((observasao == null) ? 0 : observasao.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistoricoTransferencia other = (HistoricoTransferencia) obj;
		if (idTransferencia == null) {
			if (other.idTransferencia != null)
				return false;
		} else if (!idTransferencia.equals(other.idTransferencia))
			return false;
		if (idTipoDeTrnasferencia == null) {
			if (other.idTipoDeTrnasferencia != null)
				return false;
		} else if (!idTipoDeTrnasferencia.equals(other.idTipoDeTrnasferencia))
			return false;
		if (idLocalOrigem == null) {
			if (other.idLocalOrigem != null)
				return false;
		} else if (!idLocalOrigem.equals(other.idLocalOrigem))
			return false;
		if (dataHora == null) {
			if (other.dataHora != null)
				return false;
		} else if (!dataHora.equals(other.dataHora))
			return false;
		if (idLocalDestino == null) {
			if (other.idLocalDestino != null)
				return false;
		} else if (!idLocalDestino.equals(other.idLocalDestino))
			return false;
		if (idPaciente == null) {
			if (other.idPaciente != null)
				return false;
		} else if (!idPaciente.equals(other.idPaciente))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		if (observasao == null) {
			if (other.observasao != null)
				return false;
		} else if (!observasao.equals(other.observasao))
			return false;
		return true;
	}
	
	
	public Integer getIdTransferencia() {
		return idTransferencia;
	}

	public void setIdTransferencia(Integer idTransferencia) {
		this.idTransferencia = idTransferencia;
	}

	
	
	public Integer getIdTipoDeTransferencia() {
		return idTipoDeTrnasferencia;
	}

	public void setIdTipoDeTransferencia(Integer idTipoDeTrnasferencia) {
		this.idTipoDeTrnasferencia = idTipoDeTrnasferencia;
	}

	
	
	public Integer getIdLocalDestino() {
		return idLocalDestino;
	}

	public void setIdLocalDestino(Integer idLocalDestino) {
		this.idLocalDestino = idLocalDestino;
	}

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdLocalOrigem() {
		return idLocalOrigem;
	}

	public void setIdLocalOrigem(Integer idLocalOrigem) {
		this.idLocalOrigem = idLocalOrigem;
	}

	public LocalDate getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDate dataHora) {
		this.dataHora = dataHora;
	}

	public String getObservasao() {
		return observasao;
	}

	public void setObservasao(String observasao) {
		this.observasao = observasao;
	}
}
