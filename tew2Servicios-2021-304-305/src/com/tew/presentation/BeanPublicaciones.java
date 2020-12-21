package com.tew.presentation;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.tew.business.PublicacionService;
import com.tew.infrastructure.Factories;
import com.tew.model.Publicacion;
import com.tew.model.User;

@ManagedBean
@SessionScoped
public class BeanPublicaciones implements Serializable {
	private static final long serialVersionUID = -1786803583194749666L;
	
	private Publicacion[] publicaciones = null;
	private Publicacion[] publicacionesAmigos = null;
	
	
	//uso de inyección de dependencia
    @ManagedProperty(value="#{publicacion}") 
    private BeanPublicacion publicacion;
    public BeanPublicacion getPublicacion() { return publicacion; }
    public void setPublicacion(BeanPublicacion publicacion) {this.publicacion = publicacion;}
    
    //Uso de inteccion de dependencia
  	@ManagedProperty(value="#{login}")
  	private BeanLogin login;
  	public BeanLogin getLogin() {return login;}
  	public void setLogin(BeanLogin login) {this.login=login;}
    
 
    public Publicacion[] getPublicaciones() {return(publicaciones);}
	public void setPublicaciones(Publicacion[] publicaciones) {this.publicaciones = publicaciones;}
	
	public Publicacion[] getPublicacionesAmigos() {return publicacionesAmigos;}
	public void setPublicacionesAmigos(Publicacion[] publicacionesAmigos) {this.publicacionesAmigos = publicacionesAmigos;}
	
	

	public void iniciaPublicacion(ActionEvent event) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");

    	publicacion.setId(-1);
    	publicacion.setEmail("");
    	publicacion.setTitulo(bundle.getString("titulo"));
    	publicacion.setTexto(bundle.getString("texto"));
    	publicacion.setFecha(123456789L);
    	    
	  }	
    
	public String salva() {
	       PublicacionService service;
			FacesContext jsfCtx = FacesContext.getCurrentInstance();
			FacesMessage msg = null;
			ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
			  try {
			  // Acceso a la implementacion de la capa de negocio 
				// a trav��s de la factor��a
				service = Factories.services.createPublicacionService();
				
		      //Salvamos o actualizamos el alumno segun sea una operacion de alta o de edici��n
				//if (publicacion.getId()!=-1) {
				User us = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("LOGGEDIN_USER"));
				service.savePublicacion(publicacion);
	
				//}
				//else {
					//service.updatePublicacion(publicacion); 
				//} 
				//Actualizamos el javabean de alumnos inyectado en la tabla
				//publicaciones = (Publicacion [])service.getPublicaciones().toArray(new Publicacion[0]);
				
				publicaciones = (Publicacion [])service.findByEmail(us.getLogin()).toArray(new Publicacion[0]);
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
						bundle.getString("PublicacionT"),bundle.getString("PublicacionC"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
				return "exito";
				
			  } catch (Exception e) {
				  e.printStackTrace();
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
							bundle.getString("tituloOperacionError") + "salva()",bundle.getString("PublicacionC"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					
				return "error";
			  }
			  
	 	  }
	
	public String listado() {
	       PublicacionService service;
			  try {
			  // Acceso a la implementacion de la capa de negocio 
				// a trav��s de la factor��a
				service = Factories.services.createPublicacionService();
				// De esta forma le damos informaci��n a toArray para poder hacer el casting a Publicacion[]
				publicaciones = (Publicacion[])service.getPublicaciones().toArray(new Publicacion[0]);
				
				return "exito";
				
			  } catch (Exception e) {
				e.printStackTrace();  
				return "error";
			  }
			  
	 	  }
	
	
	public String baja(Publicacion publicacion) {
	       PublicacionService service;
			  try {
			  // Acceso a la implementacion de la capa de negocio 
				// a trav��s de la factor��a
				service = Factories.services.createPublicacionService();
		      //Aliminamos el alumno seleccionado en la tabla
				service.deletePublicacion(publicacion.getId());
			  //Actualizamos el javabean de alumnos inyectado en la tabla.
				publicaciones = (Publicacion [])service.getPublicaciones().toArray(new Publicacion[0]);
				return "exito";
				
			  } catch (Exception e) {
				e.printStackTrace();  
				return "error";
			  }
			  
	 	  }
	
	public String listadoByEmail() {
	       PublicacionService service;
			  try {
			  // Acceso a la implementacion de la capa de negocio 
				// a trav��s de la factor��a
				service = Factories.services.createPublicacionService();
				User us = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("LOGGEDIN_USER"));
				//Accedemos a las publicaciones del usuario
				// De esta forma le damos informaci��n a toArray para poder hacer el casting a Publicacion[]
				publicaciones = (Publicacion[])service.findByEmail(us.getLogin()).toArray(new Publicacion[0]);
				
				return "exito";
				
			  } catch (Exception e) {
				e.printStackTrace();  
				return "error";
			  }
			  
	 	  }
	
	public String listadoPublicacionAmigos() {
	       PublicacionService service;
			  try {
			  // Acceso a la implementacion de la capa de negocio 
				// a trav��s de la factor��a
				service = Factories.services.createPublicacionService();
				
				User us = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("LOGGEDIN_USER"));
				
				//Obtenemos el listado de amigos del usuario
				List<String> listado = service.getAmigos(us.getLogin());
				
				
				// De esta forma le damos informaci��n a toArray para poder hacer el casting a Publicacion[]
				publicacionesAmigos=(Publicacion[])service.listadoPubliAmigos(listado).toArray(new Publicacion[0]);
				
				return "exito";
				
			  } catch (Exception e) {
				e.printStackTrace();  
				return "error";
			  }
			  
	 	  }
	
	public void ordenByFecha() {
	       PublicacionService service;
			  try {
			  // Acceso a la implementacion de la capa de negocio 
				// a trav��s de la factor��a
				service = Factories.services.createPublicacionService();
				User us = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("LOGGEDIN_USER"));
				
				// De esta forma le damos informaci��n a toArray para poder hacer el casting a Publicacion[]
				publicaciones = (Publicacion[])service.ordenByFecha(us.getLogin()).toArray(new Publicacion[0]);
				
			
				
			  } catch (Exception e) {
				e.printStackTrace();  
			  }
			  
	 	  }
	
	public void ordenByTitulo() {
	       PublicacionService service;
			  try {
			  // Acceso a la implementacion de la capa de negocio 
				// a trav��s de la factor��a
				service = Factories.services.createPublicacionService();
				User us = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("LOGGEDIN_USER"));
				// De esta forma le damos informaci��n a toArray para poder hacer el casting a Publicacion[]
				publicaciones = (Publicacion[])service.ordenByTitulo(us.getLogin()).toArray(new Publicacion[0]);
				
				
			  } catch (Exception e) {
				e.printStackTrace();  
			  }
			  
	 	  }

	
	
	//Se inicia correctamente el MBean inyectado si JSF lo hubiera crea
    //y en caso contrario se crea. (hay que tener en cuenta que es un Bean de sesión)
    //Se usa @PostConstruct, ya que en el contructor no se sabe todavía si el Managed Bean
    //ya estaba construido y en @PostConstruct SI.
    @PostConstruct
    public void init() {    	  
      System.out.println("BeanPublicaciones - PostConstruct"); 
      //Buscamos el alumno en la sesión. Esto es un patrón factoría claramente.
      publicacion = (BeanPublicacion) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("publicacion"));
      //si no existe lo creamos e inicializamos
      if (publicacion == null) { 
        System.out.println("BeanPublicaciones - No existia");
        publicacion = new BeanPublicacion();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "publicacion", publicacion);
      }
    }
    @PreDestroy
    public void end()  {
        System.out.println("BeanPublicaciones - PreDestroy");
    }
	
    
}
