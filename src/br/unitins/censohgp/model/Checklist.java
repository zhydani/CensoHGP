package br.unitins.censohgp.model;

import java.time.LocalDate;

public class Checklist {
	private Integer idchecklist;
	private String observacao;
	private Paciente paciente;
	private Usuario usuario;
	private LocalDate dataHora;

	public Checklist() {
		super();
	}

	public Checklist(Integer idchecklist, String observacao, Paciente paciente, Usuario usuario, LocalDate dataHora) {
		super();
		this.idchecklist = idchecklist;
		this.observacao = observacao;
		this.paciente = paciente;
		this.usuario = usuario;
		this.dataHora = dataHora;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDate dataHora) {
		this.dataHora = dataHora;
	}

}
