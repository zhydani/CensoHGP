package br.unitins.censohgp.model;

import java.time.LocalDate;
import java.util.List;

public class Paciente {

	private Integer idpaciente;
	private String nome;
	private int cpf;
	private int rg;
	private Situacao situacao;
	private Boolean ativo = Boolean.TRUE;
	private String nomeMae;
	private Sexo sexo;
	private LocalDate dataNascimento;
	private String observacao;
	private Departamento idlocalTransferencia;
	private int numeroProntuario;
	private List<Precaucao> precaucoes;
	
	
	public Paciente() {
		
		super();
		
	}


	public Paciente(Integer idpaciente, String nome, int cpf, int rg, Situacao situacao, Boolean ativo, String nomeMae,
			Sexo sexo, LocalDate dataNascimento, String observacao, Departamento idlocalTransferencia,
			int numeroProntuario, List<Precaucao> precaucoes) {
		super();
		this.idpaciente = idpaciente;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.situacao = situacao;
		this.ativo = ativo;
		this.nomeMae = nomeMae;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.observacao = observacao;
		this.idlocalTransferencia = idlocalTransferencia;
		this.numeroProntuario = numeroProntuario;
		this.precaucoes = precaucoes;
	}








	@Override
	public Paciente clone() {
		
		try {
			return (Paciente) super.clone();
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
		Paciente other = (Paciente) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (cpf != other.cpf)
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (idlocalTransferencia == null) {
			if (other.idlocalTransferencia != null)
				return false;
		} else if (!idlocalTransferencia.equals(other.idlocalTransferencia))
			return false;
		if (idpaciente == null) {
			if (other.idpaciente != null)
				return false;
		} else if (!idpaciente.equals(other.idpaciente))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomeMae == null) {
			if (other.nomeMae != null)
				return false;
		} else if (!nomeMae.equals(other.nomeMae))
			return false;
		if (numeroProntuario != other.numeroProntuario)
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (precaucoes == null) {
			if (other.precaucoes != null)
				return false;
		} else if (!precaucoes.equals(other.precaucoes))
			return false;
		if (rg != other.rg)
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		return true;
	}


	public Integer getIdpaciente() {
		return idpaciente;
	}

	public void setIdpaciente(Integer idpaciente) {
		this.idpaciente = idpaciente;
	}







	public String getNome() {
		return nome;
	}







	public void setNome(String nome) {
		this.nome = nome;
	}







	public int getCpf() {
		return cpf;
	}







	public void setCpf(int cpf) {
		this.cpf = cpf;
	}







	public int getRg() {
		return rg;
	}







	public void setRg(int rg) {
		this.rg = rg;
	}







	public Situacao getSituacao() {
		return situacao;
	}







	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}







	public Boolean getAtivo() {
		return ativo;
	}







	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}







	public String getNomeMae() {
		return nomeMae;
	}







	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}







	public Sexo getSexo() {
		return sexo;
	}







	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}







	public LocalDate getDataNascimento() {
		return dataNascimento;
	}







	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}







	public String getObservacao() {
		return observacao;
	}







	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}







	public Departamento getIdlocalTransferencia() {
		return idlocalTransferencia;
	}







	public void setIdlocalTransferencia(Departamento idlocalTransferencia) {
		this.idlocalTransferencia = idlocalTransferencia;
	}

	public int getNumeroProntuario() {
		return numeroProntuario;
	}

	public void setNumeroProntuario(int numeroProntuario) {
		this.numeroProntuario = numeroProntuario;
	}

	public List<Precaucao> getPrecaucoes() {
		return precaucoes;
	}

	public void setPrecaucoes(List<Precaucao> precaucoes) {
		this.precaucoes = precaucoes;
	}


		
}
