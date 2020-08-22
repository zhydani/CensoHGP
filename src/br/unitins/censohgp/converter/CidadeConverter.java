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

import br.unitins.censohgp.model.CidadeDepartamento;
import br.unitins.censohgp.model.Cidades;

@Named
@FacesConverter(value = "CidadeConverter", managed = true)
public class CidadeConverter implements Converter<Object>{
	
	@Inject
	private Cidades cidades;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		// se o numero for nulo eu tenho que criar uma instancia e não deixar ele ir atoa
		List<CidadeDepartamento> auxcidades = cidades.getCidades();
		if(!(value.equals("null")) && value.trim().length() > 0) {
			try {
				Integer aux = Integer.parseInt(value);
				for (CidadeDepartamento aux2 : auxcidades) {
					if(aux2.getIdcidade().equals(aux)) {
						Integer aux3 = auxcidades.indexOf(aux2);
						return auxcidades.get(aux3);
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
			return String.valueOf(((CidadeDepartamento) value).getIdcidade());
		}
		return null;
	}
	

}
