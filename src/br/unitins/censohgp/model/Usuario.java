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
	private String senha;
	@NotEmpty(message = "O campo Matricula nao pode ser vazio")
	private String matricula;
//	private Boolean ativo = Boolean.TRUE;
	

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
			@Size(min = 6, max = 30, message = "A senha deve conter entre 6 e 30 caracteres") String senha,
			@NotEmpty(message = "O campo Matricula nao pode ser vazio") String matricula) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.matricula = matricula;
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
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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



}
