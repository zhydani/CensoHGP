package br.unitins.censohgp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.censohgp.dao.SexoDAO;
import br.unitins.censohgp.model.Sexo;

@FacesConverter(value = "sexoConverter",forClass = Sexo.class)
public class SexoConverter implements Converter<Sexo> {


	@Override
	public Sexo getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty())
			return null;
		SexoDAO dao = new SexoDAO();
		return dao.findById(Integer.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Sexo value) {
		if (value == null || value.getIdsexo() == null)
			return null;
		
		return value.getIdsexo().toString();
	}

}