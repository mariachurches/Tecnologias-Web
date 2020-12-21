package com.tew.presentation;

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;


import com.tew.business.LoginService;
import com.tew.infrastructure.Factories;
import com.tew.model.User;

public class BeanLogin implements Serializable {

	private static final long serialVersionUID = 6940871785970821564L;
	private String name = "";
	private String password = "";
	private String email="";
	private String rol="";
	private boolean value; 
	private User user;

	
	@ManagedProperty(value = "#{aplicacion}")
	private BeanAplicacion aplicacion;

	public BeanAplicacion getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(BeanAplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}
	
	public boolean isValue() {return value;}
	public void setValue(boolean value) {
		this.value = value;
	}
	public String getEmail() {return email;}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getRol() {return rol;}
	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getName() {return name;}
	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {return password;}
	public void setPassword(String password) {
		this.password = password;
	}

	public String verify() {
		// necesario para accede a msgs y a los mensajes en español e ingles de los ficheros
		// de propiedades
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
		FacesMessage msg = null;
		
		if(!value) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
					bundle.getString("LeyCookies"), null);

			// se añade al element con id=”msg”
			FacesContext.getCurrentInstance().addMessage(null, msg);
			System.out.println("Entramos en las cookies");
			return "login";
		}


		LoginService login = Factories.services.createLoginService();
		user = login.verify(name, password);
		
		if (user != null) {
			putUserInSession(user);
			return "success-"+user.getRol();
		}

		// si el usuario no se encuentra
		// se prepara el mensaje que saldra en la vista del cliente
		msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
				bundle.getString("login_form_result_error"), null);

		// se añade al element con id=”msg”
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "login";
	}

	public void putUserInSession(User user) {
		Map<String, Object> session =
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.put("LOGGEDIN_USER", user);
		aplicacion.addUser(user);
		name="";password="";value=false;
		

	}

	//Método para cerrar sesión
	public String logout() {
		Map<String, Object> sesiones =  FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		User f = (User) sesiones.get("LOGGEDIN_USER");
		sesiones.remove("LOGGEDIN_USER");
		aplicacion.removeUser(f);
		System.out.println("Borrado usuario " + f.getLogin());
		//FacesContext facesContext = FacesContext.getCurrentInstance();
		//HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		//if (session != null) {
			//session.invalidate(); //Cierre de sesion
		//}
		return "logout";
	}
	
	
	@PostConstruct
	public void init() {
		System.out.println("BeanLogin - PostConstruct");
		
		aplicacion = (BeanAplicacion) FacesContext.getCurrentInstance()
				.getExternalContext().getApplicationMap().get(new String("aplicacion"));

		// si no existe lo creamos e inicializamos
		if (aplicacion == null) {
			aplicacion = new BeanAplicacion();
			FacesContext.getCurrentInstance().getExternalContext()
			.getApplicationMap().put("aplicacion", aplicacion);
		}

		
	}


}
