package br.unitins.censohgp.model;

public enum TipoUsuario {
	ADMINISTRADOR(1, "Administrador"),
	ENFERMEIRO(2, "Enfermeiro");
	
	private int value;
	private String label;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	private TipoUsuario(int value, String label) {
		this.value = value;
		this.label = label;
	}
	
	public static TipoUsuario valueOf(int value) {
		for (TipoUsuario tipousuario : TipoUsuario.values()) {
			if (tipousuario.getValue() == value)
				return tipousuario;
		}
		return null;
	}
}
