package br.unitins.censohgp.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import br.unitins.censohgp.model.Paciente;
import br.unitins.censohgp.model.Pacientes;

@Named
@FacesConverter(value = "PacientesConverter", managed = true)
public class PacientesConverter implements Converter<Object>{
	
	@Inject
	private Pacientes pacientes;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		// se o numero for nulo eu tenho que criar uma instancia e não deixar ele ir atoa
		if(!(value.equals("null")) && value.trim().length() > 0) {
			try {
				Integer aux = Integer.parseInt(value);
				for (Paciente aux2 : pacientes.getPacientes()) {
					if(aux2.getIdpaciente().equals(aux)) {
						Integer aux3 = pacientes.getPacientes().indexOf(aux2);
						System.out.println(aux2.getNome());
						return pacientes.getPacientes().get(aux3);
					}
				}
				//return pacientes.getPacientes().get(aux);
			} catch(NumberFormatException e) {
               throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// TODO Auto-generated method stub
		if(value != null) {
			return String.valueOf(((Paciente) value).getIdpaciente());
		}
		return null;
	}
	

}
