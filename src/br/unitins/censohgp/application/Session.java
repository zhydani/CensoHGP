package br.unitins.censohgp.application;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Session {
	
	private static Session session;
	
	private Session() {	}
	
	public static Session getInstance() {
		if ( session == null) 
			session = new Session();
		return session;
	}
	
	private ExternalContext getExternalContext() {
		if (FacesContext.getCurrentInstance() == null) 
			throw new RuntimeException(
					"O FaceContext eh exclusivo para aplicacao WEB");
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	
	// metodo para obter a informacao da sessao
	public Object getAttribute(String key) {
		return getExternalContext().getSessionMap().get(key);
	}
	
	// metodo para colocar a informacao na sessao
	public void setAttribute(String key, Object value) {
		getExternalContext().getSessionMap().put(key, value);
	}
	
	public void invalidateSession() {
		getExternalContext().invalidateSession();
	}

}
