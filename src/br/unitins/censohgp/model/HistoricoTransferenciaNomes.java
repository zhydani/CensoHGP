package br.unitins.censohgp.model;

import java.time.LocalDate;

public class HistoricoTransferenciaNomes {
	private String tipoDeTransferencia;
	private String localDeOrigem;
	private String localDeDestino;
	private String nomePaciente;
	private String nomeUsuario;
	private String observasao;
	private LocalDate dataHora;
	
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
		result = prime * result + ((tipoDeTransferencia == null) ? 0 : tipoDeTransferencia.hashCode());
		result = prime * result + ((localDeOrigem == null) ? 0 : localDeOrigem.hashCode());
		result = prime * result + ((dataHora == null) ? 0 : dataHora.hashCode());
		result = prime * result + ((localDeDestino == null) ? 0 : localDeDestino.hashCode());
		result = prime * result + ((nomePaciente == null) ? 0 : nomePaciente.hashCode());
		result = prime * result + ((nomeUsuario == null) ? 0 : nomeUsuario.hashCode());
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
		HistoricoTransferenciaNomes other = (HistoricoTransferenciaNomes) obj;
		if (tipoDeTransferencia == null) {
			if (other.tipoDeTransferencia != null)
				return false;
		} else if (!tipoDeTransferencia.equals(other.tipoDeTransferencia))
			return false;
		if (tipoDeTransferencia == null) {
			if (other.tipoDeTransferencia!= null)
				return false;
		} else if (!tipoDeTransferencia.equals(other.tipoDeTransferencia))
			return false;
		if (localDeOrigem == null) {
			if (other.localDeOrigem != null)
				return false;
		} else if (!localDeOrigem.equals(other.localDeOrigem))
			return false;
		if (dataHora == null) {
			if (other.dataHora != null)
				return false;
		} else if (!dataHora.equals(other.dataHora))
			return false;
		if (localDeDestino == null) {
			if (other.localDeDestino != null)
				return false;
		} else if (!localDeDestino.equals(other.localDeDestino))
			return false;
		if (nomePaciente == null) {
			if (other.nomePaciente != null)
				return false;
		} else if (!nomePaciente.equals(other.nomePaciente))
			return false;
		if (nomeUsuario == null) {
			if (other.nomeUsuario != null)
				return false;
		} else if (!nomeUsuario.equals(other.nomeUsuario))
			return false;
		if (observasao == null) {
			if (other.observasao != null)
				return false;
		} else if (!observasao.equals(other.observasao))
			return false;
		return true;
	}

	public String getTipoDeTransferencia() {
		return tipoDeTransferencia;
	}

	public void setTipoDeTransferencia(String tipoDeTransferencia) {
		this.tipoDeTransferencia = tipoDeTransferencia;
	}

	public String getLocalDeOrigem() {
		return localDeOrigem;
	}

	public void setLocalDeOrigem(String localDeOrigem) {
		this.localDeOrigem = localDeOrigem;
	}

	public String getLocalDeDestino() {
		return localDeDestino;
	}

	public void setLocalDeDestino(String localDeDestino) {
		this.localDeDestino = localDeDestino;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getObservasao() {
		return observasao;
	}

	public void setObservasao(String observasao) {
		this.observasao = observasao;
	}

	public LocalDate getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDate dataHora) {
		this.dataHora = dataHora;
	}
}
