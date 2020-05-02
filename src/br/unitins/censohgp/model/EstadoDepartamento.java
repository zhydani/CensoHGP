package br.unitins.censohgp.model;

public class EstadoDepartamento {
	private Integer idestado;
	private String estado;
	public EstadoDepartamento() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EstadoDepartamento(Integer idestado, String estado) {
		super();
		this.idestado = idestado;
		this.estado = estado;
	}
	
	@Override
	public EstadoDepartamento clone() {
		
		try {
			return (EstadoDepartamento) super.clone();
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
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((idestado == null) ? 0 : idestado.hashCode());
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
		EstadoDepartamento other = (EstadoDepartamento) obj;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (idestado == null) {
			if (other.idestado != null)
				return false;
		} else if (!idestado.equals(other.idestado))
			return false;
		return true;
	}
	public Integer getIdestado() {
		return idestado;
	}
	public void setIdestado(Integer idestado) {
		this.idestado = idestado;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	
}
