package br.unitins.censohgp.model;

public class Precaucao {
	
	private Integer idprecaucao;
	private Boolean padrao = Boolean.TRUE;
	private Boolean goticulas = Boolean.TRUE;
	private Boolean aerossol = Boolean.TRUE;
	private Boolean contato = Boolean.TRUE;
	public Precaucao() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Precaucao(Integer idprecaucao, Boolean padrao, Boolean goticulas, Boolean aerossol, Boolean contato) {
		super();
		this.idprecaucao = idprecaucao;
		this.padrao = padrao;
		this.goticulas = goticulas;
		this.aerossol = aerossol;
		this.contato = contato;
	}
	
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
		result = prime * result + ((aerossol == null) ? 0 : aerossol.hashCode());
		result = prime * result + ((contato == null) ? 0 : contato.hashCode());
		result = prime * result + ((goticulas == null) ? 0 : goticulas.hashCode());
		result = prime * result + ((idprecaucao == null) ? 0 : idprecaucao.hashCode());
		result = prime * result + ((padrao == null) ? 0 : padrao.hashCode());
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
		if (aerossol == null) {
			if (other.aerossol != null)
				return false;
		} else if (!aerossol.equals(other.aerossol))
			return false;
		if (contato == null) {
			if (other.contato != null)
				return false;
		} else if (!contato.equals(other.contato))
			return false;
		if (goticulas == null) {
			if (other.goticulas != null)
				return false;
		} else if (!goticulas.equals(other.goticulas))
			return false;
		if (idprecaucao == null) {
			if (other.idprecaucao != null)
				return false;
		} else if (!idprecaucao.equals(other.idprecaucao))
			return false;
		if (padrao == null) {
			if (other.padrao != null)
				return false;
		} else if (!padrao.equals(other.padrao))
			return false;
		return true;
	}
	public Integer getIdprecaucao() {
		return idprecaucao;
	}
	public void setIdprecaucao(Integer idprecaucao) {
		this.idprecaucao = idprecaucao;
	}
	public Boolean getPadrao() {
		return padrao;
	}
	public void setPadrao(Boolean padrao) {
		this.padrao = padrao;
	}
	public Boolean getGoticulas() {
		return goticulas;
	}
	public void setGoticulas(Boolean goticulas) {
		this.goticulas = goticulas;
	}
	public Boolean getAerossol() {
		return aerossol;
	}
	public void setAerossol(Boolean aerossol) {
		this.aerossol = aerossol;
	}
	public Boolean getContato() {
		return contato;
	}
	public void setContato(Boolean contato) {
		this.contato = contato;
	}
	
	
	

}
