package br.unitins.censohgp.model;

public enum StatusDepartamento {


		ATIVO(0, "ATIVO"), 
		DESATIVADO(1, "DESATIVADO");

		private int value;
		private String label;

		StatusDepartamento(int value, String label) {
			this.value = value;
			this.label = label;
		}

		public int getValue() {
			return value;
		}

		public String getLabel() {
			return label;
		}

		public static StatusDepartamento valueOf(int value) {

			for (StatusDepartamento status : StatusDepartamento.values()) {
				if (status.getValue() == value) 
					return status;
			}
			return null;
		}

		public static StatusDepartamento valueOf(boolean ativo) {
			int var =0 ;
			if(ativo == true)
				var =0;
			else
				var=1;
			for (StatusDepartamento status : StatusDepartamento.values()) {
				if (status.getValue() == var) 
					return status;
			}
			return null;
		}


}