package br.unitins.censohgp.converter;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import br.unitins.censohgp.model.EstadoDepartamento;
import br.unitins.censohgp.model.Estados;

@Named
@FacesConverter(value = "EstadoConverter", managed = true)
public class EstadoConverter implements Converter<Object>{
	
	@Inject
	private Estados estados;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		// se o numero for nulo eu tenho que criar uma instancia e n�o deixar ele ir atoa
		List<EstadoDepartamento> auxestados = estados.getEstados();
		if(!(value.equals("null")) && value.trim().length() > 0) {
			try {
				Integer aux = Integer.parseInt(value);
				for (EstadoDepartamento aux2 : auxestados) {
					if(aux2.getIdestado().equals(aux)) {
						Integer aux3 = auxestados.indexOf(aux2);
						return auxestados.get(aux3);
					}
				}
				
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
			return String.valueOf(((EstadoDepartamento) value).getIdestado());
		}
		return null;
	}
	

}