package com.tew.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.tew.business.AmigosService;
import com.tew.infrastructure.Factories;
import com.tew.model.Amigos;
import com.tew.model.User;

@ManagedBean
@SessionScoped
public class BeanAmigos implements Serializable{

		private static final long serialVersionUID = 5297821425254651661L;

		private Amigos[] amigos = null;
		
		private String mensaje;
          
        //uso de inyección de dependencia
          @ManagedProperty(value="#{amigo}") 
          private BeanAmigo amigo;
          public BeanAmigo getAmigo() { return amigo; }
          public void setAmigo(BeanAmigo amigo) {this.amigo = amigo;}

	      public Amigos[] getAmigos() {return amigos;}
	      public void setAmigos(Amigos[] amigos) {this.amigos = amigos;}
		
	      
	      public void iniciaAmigo(ActionEvent event) {
	    	 amigo.setEmail_usuario(null);
	    	 amigo.setEmail_amigo(null);
	    	 amigo.setAceptada(false);
	    	 
	       }
	       
	       
	       public String listadoInvitacionesAmistad() {
		       AmigosService service;
				  try {
					service = Factories.services.createAmigosService();
					User us = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("LOGGEDIN_USER"));
					
					amigos = (Amigos [])service.getInvitacionesAmistad(us.getLogin()).toArray(new Amigos[0]);
					
					return "exito";
					
				  } catch (Exception e) {
					e.printStackTrace();  
					return "error";
				  }
				  
		 	  }
	       
	       public void acepta(String emailAmigo) {
	    	   FacesContext jsfCtx = FacesContext.getCurrentInstance();
	   		FacesMessage msg = null;
	   		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
		       AmigosService service;
				  try {
				  // Acceso a la implementacion de la capa de negocio 
					// a trav��s de la factor��a
					service = Factories.services.createAmigosService();
					User us = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("LOGGEDIN_USER"));
			      
					service.aceptar(us.getLogin(),emailAmigo);
					
					//Actualizamos el javabean de alumnos inyectado en la tabla
					amigos = (Amigos [])service.getInvitacionesAmistad(us.getLogin()).toArray(new Amigos[0]);
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
							bundle.getString("Usuario-en-sesion"), null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
					
				  } catch (Exception e) {
					  e.printStackTrace();
				  }
				  
		 	}
	       
	       public String envio(String emailAmigo) {
		       AmigosService service;
		       FacesContext jsfCtx = FacesContext.getCurrentInstance();
		       FacesMessage msg = null;
		       ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
		       
		       System.out.println("--------------------------------------------------------");
		       
				  try {
				  // Acceso a la implementacion de la capa de negocio 
					// a trav��s de la factor��a
					service = Factories.services.createAmigosService();
					
					
					
					
					//SESSIONS CORREGIR
					User us = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("LOGGEDIN_USER"));
					String email = us.getLogin(); //Email usuario
					
					
					//SI YA SOIS AMIGOS
					if(service.findByEmail(email,emailAmigo)!=null) {
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
								bundle.getString("Peticion-ya-realizada"), null);
						FacesContext.getCurrentInstance().addMessage(null, msg);
						  return "error";
					}
					
					//SI YA MANDASTE ESTA PETICI�N
					if(service.findByEmail(emailAmigo,email)!=null) {
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
								bundle.getString("Peticion-ya-realizada2"), null);
						FacesContext.getCurrentInstance().addMessage(null, msg);
						  return "error";
						
					}
					
					
					service.envio(email,emailAmigo);
					//Actualizamos el javabean de alumnos inyectado en la tabla
					amigos = (Amigos [])service.getInvitacionesAmistad(email).toArray(new Amigos[0]);
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
							bundle.getString("Peticion-ya-realizadaET"), null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
					
				  } catch (Exception e) {
					  e.printStackTrace();
				  }
				  return "exito";
		 	  }
	       
	       
	       
	       public void mensaje() {
	   		
	   		FacesContext context = FacesContext.getCurrentInstance();
	   		context.addMessage(null, new FacesMessage(mensaje, null));
	   		
	   		}
	       
	       
	       
	       
	       
	       
	     //Se inicia correctamente el MBean inyectado si JSF lo hubiera crea
	     //y en caso contrario se crea. (hay que tener en cuenta que es un Bean de sesión)
	     //Se usa @PostConstruct, ya que en el contructor no se sabe todavía si el Managed Bean
	     //ya estaba construido y en @PostConstruct SI.
	     @PostConstruct
	     public void init() {    	  
	       System.out.println("BeanAmigos - PostConstruct"); 
	       //Buscamos el alumno en la sesión. Esto es un patrón factoría claramente.
	       amigo = (BeanAmigo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("amigo"));
	       //si no existe lo creamos e inicializamos
	       if (amigo == null) { 
	         System.out.println("BeanAmigos - No existia");
	         amigo = new BeanAmigo();
	         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "amigo", amigo);
	       }
	     }
	     @PreDestroy
	     public void end()  {
	         System.out.println("BeanAmigos - PreDestroy");
	     }

	}


	
