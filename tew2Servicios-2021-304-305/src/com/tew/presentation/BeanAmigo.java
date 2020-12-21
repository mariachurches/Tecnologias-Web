package com.tew.presentation;

import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.model.Amigos;

@ManagedBean(name="amigo")
@SessionScoped
public class BeanAmigo extends Amigos implements Serializable {
	
	private static final long serialVersionUID = 5474036495099991825L;
	
	public BeanAmigo() {
		iniciaAmigo(null); 
	}
	
	public void setAmigo(Amigos amigo) {
		setEmail_usuario(amigo.getEmail_usuario());
		setEmail_amigo(amigo.getEmail_amigo());
		setAceptada(amigo.isAceptada());
	}
	
	
	public void iniciaAmigo(ActionEvent event) {
		
		String email = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("email"));
		setEmail_usuario(email);
		setEmail_amigo(null);
		setAceptada(false);
		
	}	      
}
