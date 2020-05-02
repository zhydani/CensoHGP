package br.unitins.censohgp.model;

public enum TipoSexo {
	
	MASCULINO(1, "Masculino"), 
	FEMININO(2, "Feminino");
	
	private int value;
	private String label;
	
	TipoSexo(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	public static TipoSexo valueOf(int value) {
		
		for (TipoSexo sexo : TipoSexo.values()) {
			if (sexo.getValue() == value) 
				return sexo;
		}
		return null;
	}
	


}
