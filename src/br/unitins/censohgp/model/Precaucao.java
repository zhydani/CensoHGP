package br.unitins.censohgp.model;

public class Precaucao {
	
	private Integer idprecaucao;
	private String nome ;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idprecaucao == null) ? 0 : idprecaucao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Precaucao other = (Precaucao) obj;
		if (idprecaucao == null) {
			if (other.idprecaucao != null)
				return false;
		} else if (!idprecaucao.equals(other.idprecaucao))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public Integer getIdprecaucao() {
		return idprecaucao;
	}

	public void setIdprecaucao(Integer idprecaucao) {
		this.idprecaucao = idprecaucao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
