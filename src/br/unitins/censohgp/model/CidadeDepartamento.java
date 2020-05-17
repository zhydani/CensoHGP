package br.unitins.censohgp.model;

public class CidadeDepartamento {
	private Integer idcidade;
	private String cidade;
	private Integer idcidadeEstado;
	private EstadoDepartamento estado;
	public CidadeDepartamento() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CidadeDepartamento(Integer idcidade, String cidade, Integer idcidadeEstado, EstadoDepartamento estado) {
		super();
		this.idcidade = idcidade;
		this.cidade = cidade;
		this.idcidadeEstado = idcidadeEstado;
		this.estado = estado;
	}



	@Override
	public String toString() {
	    return String.format("idcidade", getIdcidade());
	}
	
	@Override
	public CidadeDepartamento clone() {
		
		try {
			return (CidadeDepartamento) super.clone();
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
		CidadeDepartamento other = (CidadeDepartamento) obj;
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
		if (idcidade == null) {
			if (other.idcidade != null)
				return false;
		} else if (!idcidade.equals(other.idcidade))
			return false;
		if (idcidadeEstado == null) {
			if (other.idcidadeEstado != null)
				return false;
		} else if (!idcidadeEstado.equals(other.idcidadeEstado))
			return false;
		return true;
	}


	public Integer getIdcidade() {
		return idcidade;
	}
	public void setIdcidade(Integer idcidade) {
		this.idcidade = idcidade;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public Integer getIdcidadeEstado() {
		return idcidadeEstado;
	}


	public void setIdcidadeEstado(Integer idcidadeEstado) {
		this.idcidadeEstado = idcidadeEstado;
	}


	public EstadoDepartamento getEstado() {
		return estado;
	}


	public void setEstado(EstadoDepartamento estado) {
		this.estado = estado;
	}
	
	
}
