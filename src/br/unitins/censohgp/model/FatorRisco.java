package br.unitins.censohgp.model;

public class FatorRisco {
	private Integer idfatorRisco;
	private String nome;
	private String descricao;
	private boolean ativo;

	public Integer getIdfatorRisco() {
		return idfatorRisco;
	}

	public void setIdfatorRisco(Integer idfatorRisco) {
		this.idfatorRisco = idfatorRisco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
