package br.unitins.censohgp.model;

public class Procedimento {
	private Integer idprocedimento;
	private String nome;
	private String descricao;
	private boolean ativo;

	public Procedimento() {
		super();
	}

	public Procedimento(Integer idprocedimento, String nome, String descricao, boolean ativo) {
		super();
		this.idprocedimento = idprocedimento;
		this.nome = nome;
		this.descricao = descricao;
		this.ativo = ativo;
	}

	public Integer getIdprocedimento() {
		return idprocedimento;
	}

	public void setIdprocedimento(Integer idprocedimento) {
		this.idprocedimento = idprocedimento;
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
