package br.unitins.censohgp.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Checklist {
	private Integer idchecklist;
	private String observacao;
	private Paciente paciente;
	private Usuario usuario;
	private List<Procedimento> procedimentos;
	private List<FatorRisco> fatoresderisco;
	private List<Incidente> incidentes;
	private Date data_hora;

	@Override
	public Checklist clone() {

		try {
			return (Checklist) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			System.out.println("Erro ao clonar.");
		}
		return null;

	}

	public Date getData_hora() {
		return data_hora;
	}

	public void setData_hora(Date data_hora) {
		this.data_hora = data_hora;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getIdchecklist() {
		return idchecklist;
	}

	public void setIdchecklist(Integer idchecklist) {
		this.idchecklist = idchecklist;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Procedimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<Procedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public List<FatorRisco> getFatoresRisco() {
		return fatoresderisco;
	}

	public void setFatoresRisco(List<FatorRisco> fatoresderisco) {
		this.fatoresderisco = fatoresderisco;
	}

	public List<Incidente> getIncidentes() {
		return incidentes;
	}

	public void setIncidentes(List<Incidente> incidentes) {
		this.incidentes = incidentes;
	}

	@Override
	public String toString() {
		return "Checklist [idchecklist=" + idchecklist + ",  observacao=" + observacao + ", paciente=" + paciente + "]";
	}

}
