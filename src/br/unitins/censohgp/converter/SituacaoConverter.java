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

import br.unitins.censohgp.model.Departamento;
import br.unitins.censohgp.model.Situacao;
import br.unitins.censohgp.model.Situacoes;

@Named
@FacesConverter(value = "SituacaoConverter", managed = true)
public class SituacaoConverter implements Converter<Object>{
	
	@Inject
	private Situacoes situacoes;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		// se o numero for nulo eu tenho que criar uma instancia e não deixar ele ir atoa
		List<Situacao> auxsituacao = situacoes.getSituacoes();
		if(!(value.equals("null")) && value.trim().length() > 0) {
			try {
				Integer aux = Integer.parseInt(value);
				for (Situacao aux2 : auxsituacao) {
					if(aux2.getIdsituacao().equals(aux)) {
						Integer aux3 = auxsituacao.indexOf(aux2);
						return auxsituacao.get(aux3);
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
			return String.valueOf(((Situacao) value).getIdsituacao());
		}
		return null;
	}
	
}
