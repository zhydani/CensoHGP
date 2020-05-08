package br.unitins.censohgp.application;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.unitins.censohgp.model.Situacao;

public class SimpleEntityConverter implements Converter<Object>{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null) {
			return this.getAttributesFrom(component).get(value);
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null && !"".equals(value)) {
			Situacao entity = (Situacao) value;
			
			//adiciona item como atributo do componente
			this.addAttribute(component, entity); 
			Integer codigo = entity.getIdsituacao();
			
			if (codigo != null) {
				return String.valueOf(codigo);
			}
		}
		return (String) value;
	}
	
	protected void addAttribute(UIComponent component, Situacao o) {
		String key = o.getIdsituacao().toString();
		this.getAttributesFrom(component).put(key, o);
	}
	
	protected Map<String, Object> getAttributesFrom(UIComponent component){
		return component.getAttributes();
		
	}
	

}
