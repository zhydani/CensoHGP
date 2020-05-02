package br.unitins.censohgp.model;

public enum SituacaoPaciente {



	ATENDIMENTO(1, "Atendimento"), 
	TRAFERENCIA(2, "Transferência externa"),
	OBITO(3, "Óbito"),
	EMADE(4, "EMADE"),
	ALTA(5, "Alta");

	private int value;
	private String label;

	SituacaoPaciente(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public static SituacaoPaciente valueOf(int value) {

		for (SituacaoPaciente situacao : SituacaoPaciente.values()) {
			if (situacao.getValue() == value) 
				return situacao;
		}
		return null;
	}



}
