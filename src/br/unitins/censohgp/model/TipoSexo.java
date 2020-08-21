package br.unitins.censohgp.model;

public enum TipoSexo {
	FEMININO(1, "Feminino"),
	MASCULINO(2, "Masculino"), 
	NENHUM(3, "Nenhum");
	
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
	private TipoSexo(int value, String label) {
		this.value = value;
		this.label = label;
	}
	
	public static TipoSexo valueOf(int value) {
		for (TipoSexo tiposexo : TipoSexo.values()) {
			if (tiposexo.getValue() == value)
				return tiposexo;
		}
		return null;
	}
}
