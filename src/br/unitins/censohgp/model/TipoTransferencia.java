package br.unitins.censohgp.model;

public enum TipoTransferencia {

	INTERNO(1, "Interno"), 
	EXTERNO(2, "Externo");
	
	private int value;
	private String label;
	
	private TipoTransferencia(int value, String label) {
		this.value = value;
		this.label = label;
	}
	public int getValue() {
		return value;
	}
	public String getLabel() {
		return label;
	}
	
	public static String valueOf(int value) {	
		for (TipoTransferencia transferencia : TipoTransferencia.values()) {
			if (transferencia.getValue() == value) { 
				String nome = transferencia.getLabel();
				return nome;
			}
		}
		return null;
	}
	public static Integer valueId(String value) {
		for (TipoTransferencia transferencia : TipoTransferencia.values()) {
			if(transferencia.getLabel().equals(value)) {
				return transferencia.getValue();
			}
		}
		return null;
	}

}
