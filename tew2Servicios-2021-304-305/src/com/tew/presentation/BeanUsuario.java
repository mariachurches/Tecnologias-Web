package com.tew.presentation;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.business.UsuariosService;
import com.tew.infrastructure.Factories;
import com.tew.model.Usuario;

@ManagedBean(name="usuario")
@SessionScoped
public class BeanUsuario extends Usuario implements Serializable {

	private Usuario usuario;
	private String passwd2;
	public String getPasswd2() {
		return passwd2;
	}
	public void setPasswd2(String passwd2) {
		this.passwd2 = passwd2;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BeanUsuario() {
		iniciaUsuario(null);
	}

	public void setUsuario(Usuario user) {
		setEmail(user.getEmail());
		setPasswd(user.getPasswd());
		setRol(user.getRol());
		setNombre(user.getNombre());
	}

	public String[] CompruebaLogin(String email) {
		UsuariosService service;
		String [] parametros = new String[4]; 
		try {
			service = Factories.services.createUsuariosService();
			//Devolvemos el email 
			System.out.println("Comprueba login " + email);
			usuario=service.findByEmail(email);
			if(usuario!=null) {
				parametros[0]= usuario.getEmail();
				parametros[1]= usuario.getPasswd();
				parametros[2]= usuario.getNombre();
				parametros[3]= usuario.getRol();
			}
			else {
				parametros[0]="no-usuario";
			}

		} catch (Exception e) {
			e.printStackTrace();  
		}

		return parametros;


	}

	public void iniciaUsuario(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = 
				facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		setEmail(null);
		setPasswd(bundle.getString("valorDefectoContraseña"));
		setRol(bundle.getString("valorDefectoRol"));
		setNombre(bundle.getString("valorDefectoNombre"));
	}	      
}
