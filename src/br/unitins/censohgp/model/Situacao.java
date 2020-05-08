package br.unitins.censohgp.model;

public class Situacao {
	
	private Integer idsituacao;
	private String nome;
	
	
	
	public Situacao() {
		super();
	}

	public Situacao(Integer idsituacao, String nome) {
		super();
		this.idsituacao = idsituacao;
		this.nome = nome;
	}

	@Override
	public String toString() {
	    return String.format("idsituacao", getIdsituacao());
	}
	
	@Override
	public Situacao clone() {
		
		try {
			return (Situacao) super.clone();
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
		Situacao other = (Situacao) obj;
		if (idsituacao == null) {
			if (other.idsituacao != null)
				return false;
		} else if (!idsituacao.equals(other.idsituacao))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}



	public Integer getIdsituacao() {
		return idsituacao;
	}



	public void setIdsituacao(Integer idsituacao) {
		this.idsituacao = idsituacao;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
