package br.unitins.censohgp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.censohgp.model.EstadoDepartamento;

@FacesConverter(forClass = EstadoDepartamento.class)
public class EstadoConverter implements Converter<Object> {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        Integer id = Integer.valueOf(value);
        EstadoDepartamento estado = new EstadoDepartamento();
        estado.setIdestado(id);
        return estado;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        EstadoDepartamento estado = (EstadoDepartamento) value;
        if (estado.getIdestado() == null) {
            return null;
        }

        return estado.getIdestado().toString();
    }

}