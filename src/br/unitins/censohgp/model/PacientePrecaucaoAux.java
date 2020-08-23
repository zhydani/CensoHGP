package br.unitins.censohgp.model;

public class PacientePrecaucaoAux {

	private Integer idprecaucaoPaciente;
	private Precaucao idprecaucao;
	private Paciente idpaciente;

	@Override
	public Precaucao clone() {

		try {
			return (Precaucao) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			System.out.println("Erro ao clonar.");
		}
		return null;

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PacientePrecaucaoAux other = (PacientePrecaucaoAux) obj;
		if (idpaciente == null) {
			if (other.idpaciente != null)
				return false;
		} else if (!idpaciente.equals(other.idpaciente))
			return false;
		if (idprecaucao == null) {
			if (other.idprecaucao != null)
				return false;
		} else if (!idprecaucao.equals(other.idprecaucao))
			return false;
		if (idprecaucaoPaciente == null) {
			if (other.idprecaucaoPaciente != null)
				return false;
		} else if (!idprecaucaoPaciente.equals(other.idprecaucaoPaciente))
			return false;
		return true;
	}

	public Integer getIdprecaucaoPaciente() {
		return idprecaucaoPaciente;
	}

	public void setIdprecaucaoPaciente(Integer idprecaucaoPaciente) {
		this.idprecaucaoPaciente = idprecaucaoPaciente;
	}

	public Precaucao getIdprecaucao() {
		return idprecaucao;
	}

	public void setIdprecaucao(Precaucao idprecaucao) {
		this.idprecaucao = idprecaucao;
	}

	public Paciente getIdpaciente() {
		return idpaciente;
	}

	public void setIdpaciente(Paciente idpaciente) {
		this.idpaciente = idpaciente;
	}

}
