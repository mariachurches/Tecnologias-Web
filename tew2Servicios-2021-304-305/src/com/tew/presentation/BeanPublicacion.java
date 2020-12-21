package com.tew.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.tew.model.Publicacion;
import com.tew.model.User;

@ManagedBean(name="publicacion")
@SessionScoped
public class BeanPublicacion extends Publicacion implements Serializable{
	private static final long serialVersionUID = -6238217336303198667L;
	
	public BeanPublicacion() {
		iniciaPublicacion(null);
	}

	//Uso de inteccion de dependencia
  	@ManagedProperty(value = "#{login}")
  	private BeanLogin login;
  	public BeanLogin getLogin() {return login;}
  	public void setLogin(BeanLogin login) {this.login=login;}
	
	
	public void setAlumno(Publicacion publicacion) {
		setId(publicacion.getId());
		setEmail(publicacion.getEmail());
		setTitulo(publicacion.getTitulo());
		setTexto(publicacion.getTexto());
		setFecha(publicacion.getFecha());
	}
	
	public void iniciaPublicacion(ActionEvent event) {

	    FacesContext facesContext = FacesContext.getCurrentInstance();
    	    ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
    	    User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("LOGGEDIN_USER"));

    	    setId(-1);
    	    setEmail(user.getLogin());
    	    setTitulo(bundle.getString("titulo"));
    	    setTexto(bundle.getString("texto"));
    	    setFecha(123456789L);
    	    
	  }	      
	
}
