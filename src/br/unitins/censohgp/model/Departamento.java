package br.unitins.censohgp.model;

public class Departamento {

	private Integer idlocalTransferencia;
	private String nomeHospital;
	private int numeroLeitos;
	private StatusDepartamento ativo;
	private String nomeDepartamento;
	private EstadoDepartamento estado;
	private CidadeDepartamento cidade;
	public Departamento() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Departamento(Integer idlocalTransferencia, String nomeHospital, int numeroLeitos, StatusDepartamento ativo,
			String nomeDepartamento, EstadoDepartamento estado, CidadeDepartamento cidade) {
		super();
		this.idlocalTransferencia = idlocalTransferencia;
		this.nomeHospital = nomeHospital;
		this.numeroLeitos = numeroLeitos;
		this.ativo = ativo;
		this.nomeDepartamento = nomeDepartamento;
		this.estado = estado;
		this.cidade = cidade;
	}

	@Override
	public Departamento clone() {
		
		try {
			return (Departamento) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			System.out.println("Erro ao clonar.");
		}
		return null;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((idlocalTransferencia == null) ? 0 : idlocalTransferencia.hashCode());
		result = prime * result + ((nomeDepartamento == null) ? 0 : nomeDepartamento.hashCode());
		result = prime * result + ((nomeHospital == null) ? 0 : nomeHospital.hashCode());
		result = prime * result + numeroLeitos;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		if (ativo != other.ativo)
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (idlocalTransferencia == null) {
			if (other.idlocalTransferencia != null)
				return false;
		} else if (!idlocalTransferencia.equals(other.idlocalTransferencia))
			return false;
		if (nomeDepartamento == null) {
			if (other.nomeDepartamento != null)
				return false;
		} else if (!nomeDepartamento.equals(other.nomeDepartamento))
			return false;
		if (nomeHospital == null) {
			if (other.nomeHospital != null)
				return false;
		} else if (!nomeHospital.equals(other.nomeHospital))
			return false;
		if (numeroLeitos != other.numeroLeitos)
			return false;
		return true;
	}

	public Integer getIdlocalTransferencia() {
		return idlocalTransferencia;
	}

	public void setIdlocalTransferencia(Integer idlocalTransferencia) {
		this.idlocalTransferencia = idlocalTransferencia;
	}

	public String getNomeHospital() {
		return nomeHospital;
	}

	public void setNomeHospital(String nomeHospital) {
		this.nomeHospital = nomeHospital;
	}

	public int getNumeroLeitos() {
		return numeroLeitos;
	}

	public void setNumeroLeitos(int numeroLeitos) {
		this.numeroLeitos = numeroLeitos;
	}

	public StatusDepartamento getAtivo() {
		return ativo;
	}

	public void setAtivo(StatusDepartamento ativo) {
		this.ativo = ativo;
	}

	public String getNomeDepartamento() {
		return nomeDepartamento;
	}

	public void setNomeDepartamento(String nomeDepartamento) {
		this.nomeDepartamento = nomeDepartamento;
	}

	public EstadoDepartamento getEstado() {
		return estado;
	}

	public void setEstado(EstadoDepartamento estado) {
		this.estado = estado;
	}

	public CidadeDepartamento getCidade() {
		return cidade;
	}

	public void setCidade(CidadeDepartamento cidade) {
		this.cidade = cidade;
	}
	
}