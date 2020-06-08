package br.unitins.censohgp.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Usuario implements Cloneable {
	private Integer id;
	private Tipo tipo;
	@NotEmpty(message = "O campo Nome nao pode ser vazio")
	@Size(max = 60, message = "O campo Nome deve conter no maximo 60 caracteres")
	private String nome;
	@Email
	private String email;
	@Size(min = 6, max = 30, message = "A senha deve conter entre 6 e 30 caracteres")
	private String senha1;
	@Size(min = 6, max = 30, message = "A senha deve conter entre 6 e 30 caracteres")
	private String senha2;
	@NotEmpty(message = "O campo Matricula nao pode ser vazio")
	private String matricula;
	private Boolean ativo = Boolean.TRUE;
	private TipoUsuario tipousuario;

	public Usuario() {
		super();
	}

	@Override
	public Usuario clone() {
		try {
			return (Usuario) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			System.out.println("Erro ao clonar.");
		}
		return null;
	}

	@Override
	public String toString() {
		return String.format("id", getId());
	}

	public Usuario(Integer id, Tipo tipo,
			@NotEmpty(message = "O campo Nome nao pode ser vazio") @Size(max = 60, message = "O campo Nome deve conter no maximo 60 caracteres") String nome,
			@Email String email,
			@Size(min = 6, max = 30, message = "A senha deve conter entre 6 e 30 caracteres") String senha1,
			@Size(min = 6, max = 30, message = "A senha deve conter entre 6 e 30 caracteres") String senha2,
			@NotEmpty(message = "O campo Matricula nao pode ser vazio") String matricula, Boolean ativo,
			TipoUsuario tipousuario) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.nome = nome;
		this.email = email;
		this.senha1 = senha1;
		this.senha2 = senha2;
		this.matricula = matricula;
		this.ativo = ativo;
		this.tipousuario = tipousuario;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha1 == null) {
			if (other.senha1 != null)
				return false;
		} else if (!senha1.equals(other.senha1))
			return false;
		if (senha2 == null) {
			if (other.senha2 != null)
				return false;
		} else if (!senha2.equals(other.senha2))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (tipousuario != other.tipousuario)
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha1() {
		return senha1;
	}

	public void setSenha1(String senha1) {
		this.senha1 = senha1;
	}

	public String getSenha2() {
		return senha2;
	}

	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Tipo getTipo() {
		if (tipo == null) {
			tipo = new Tipo();
		}
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public TipoUsuario getTipousuario() {
		return tipousuario;
	}

	public void setTipousuario(TipoUsuario tipousuario) {
		this.tipousuario = tipousuario;
	}

}
