package br.unitins.censohgp.model;

public class CidadeDepartamento {
	private Integer idcidade;
	private String cidade;
	
	public CidadeDepartamento() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CidadeDepartamento(Integer idcidade, String cidade) {
		super();
		this.idcidade = idcidade;
		this.cidade = cidade;
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
		if (idcidade == null) {
			if (other.idcidade != null)
				return false;
		} else if (!idcidade.equals(other.idcidade))
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
	
	
}
