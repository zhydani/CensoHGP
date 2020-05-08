package br.unitins.censohgp.model;

public class Sexo {

	private Integer idsexo;
	private String nome;

	public Sexo() {

		super();

	}

	public Sexo(Integer idsexo, String nome) {
		super();
		this.idsexo = idsexo;
		this.nome = nome;
	}
	
	@Override
	public String toString() {
	    return String.format("idsexo", getIdsexo());
	}

	@Override
	public Sexo clone() {

		try {
			return (Sexo) super.clone();
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
		Sexo other = (Sexo) obj;
		if (idsexo == null) {
			if (other.idsexo != null)
				return false;
		} else if (!idsexo.equals(other.idsexo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public Integer getIdsexo() {
		return idsexo;
	}

	public void setIdsexo(Integer idsexo) {
		this.idsexo = idsexo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


}
