package com.tew.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.tew.business.AmigosService;
import com.tew.business.PublicacionService;
import com.tew.business.UsuariosService;
import com.tew.infrastructure.Factories;
import com.tew.model.User;
import com.tew.model.Usuario;


public class BeanUsuarios implements Serializable{
	private static final long serialVersionUID = 55655L;

	private List<Usuario> usuarios = null;
	private ArrayList<Usuario> seleccion = null;
	private String mensaje;
	private String cadena;

	//uso de inyección de dependencia
	@ManagedProperty(value="#{usuario}") 
	private BeanUsuario usuario;
	public BeanUsuario getUusario() { return usuario; }
	public void setUsuario(BeanUsuario usuario) {this.usuario = usuario;}

	@ManagedProperty(value = "#{aplicacion}")
	private BeanAplicacion aplicacion;

	public BeanAplicacion getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(BeanAplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}


	public ArrayList<Usuario> getSeleccion() {
		return seleccion;
	}
	public void setSeleccion(ArrayList<Usuario> seleccion) {
		this.seleccion = seleccion;
	}


	public List<Usuario> getUsuarios () {
		return(usuarios);
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}  

	public String getCadena() {
		return cadena;
	}
	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public void iniciausuario(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = 
				facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		usuario.setEmail(null);
		usuario.setPasswd(bundle.getString("contrase�a"));
		usuario.setRol(bundle.getString("rol"));
		usuario.setNombre(bundle.getString("nombre")); 
	}

	public String listado() {
		UsuariosService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createUsuariosService();
			// De esta forma le damos informaci��n a toArray para poder hacer el casting a usuario[]
			usuarios = (List<Usuario>)service.getUsuarios("usuarios");
			seleccion=new ArrayList<Usuario>();
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}

	}


	public void baja() {
		System.out.println();
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		FacesMessage msg = null;
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
		ArrayList<User> users = aplicacion.getUser_en();
		boolean sesion = false;
		int j=0;
		UsuariosService service;
		AmigosService serviceA;
		PublicacionService serviceP;
		// Creamos las factorias necesarias para borrar todas las relaciones de la BBDD
		serviceP = Factories.services.createPublicacionService();
		service = Factories.services.createUsuariosService();
		serviceA = Factories.services.createAmigosService();
		if(seleccion.size()==0) {msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
				bundle.getString("No-selecion") , null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
		for(Usuario usuario: seleccion) {
			sesion = false;

			for(int i =0; i< users.size() ; i++) {
				if(users.get(i).getLogin()==usuario.getEmail()) {sesion=true;users.remove(i);j=i;}
			}

			if(sesion) {
				msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
						bundle.getString("Usuario-en-sesion") + j, null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}

			else {
				try {

					//Eliminamos las 
					serviceA.deleteAmigo(usuario.getEmail());
					serviceP.deletePublicacionesAmigos(usuario.getEmail());
					service.deleteUsuario(usuario.getEmail());
					//Actualizamos el javabean de usuarios inyectado en la tabla.
					usuarios = (List<Usuario>)service.getUsuarios("usuarios");
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
							bundle.getString("BorradoUsuarioT") + usuario.getNombre(), bundle.getString("BorradoUsuarioC"));
					FacesContext.getCurrentInstance().addMessage(null, msg);

				} catch (Exception e) {
					e.printStackTrace();  
					msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
							bundle.getString("tituloOperacionError") + "baja()", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}
		}
	}
	
	public String edit() {
		UsuariosService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createUsuariosService();
			//Recargamos el usuario seleccionado en la tabla de la base de datos por si hubiera cambios.
			usuario = (BeanUsuario) service.findByEmail(usuario.getEmail());
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}

	}

	public String salva() {
		usuario.setRol("usuario");
		UsuariosService service;
		Usuario u;
		User us;
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		FacesMessage msg = null;
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");

		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createUsuariosService();
			//Salvamos o actualizamos el usuario segun sea una operacion de alta o de edici��n
			//if (usuario.getEmail() == null) {
			u=(Usuario) service.findByEmail(usuario.getEmail());
			if (u == null) {
				service.saveUsuario(usuario);
				us = new User(usuario.getEmail(), usuario.getNombre(), usuario.getRol());
				Map<String, Object> session =
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
				session.put("LOGGEDIN_USER", us);
				aplicacion.addUser(us);
				usuario.setEmail(null);usuario.setPasswd2(null);usuario.setPasswd(null);usuario.setRol(null);
			}

			else {
				//service.updateUsuario(usuario); 
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
						bundle.getString("find_form_result_error"), null);
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return "repetido";
			} 
			//Actualizamos el javabean de usuarios inyectado en la tabla
			usuarios = (List<Usuario>)service.getUsuarios("usuarios");
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					bundle.getString("registro_correcto"), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					bundle.getString("error_registro"), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "error";
		}

	}


	public void listadoByCadena() {

		UsuariosService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createUsuariosService();
			//String email = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("email"));
			User us = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LOGGEDIN_USER");
			// De esta forma le damos informaci��n a toArray para poder hacer el casting a usuario[]
			usuarios = (List<Usuario>)service.findByCadena(cadena,us.getLogin());

		} catch (Exception e) {
			e.printStackTrace();  
		}
	}

	public String listadoSinUsLogin() {
		UsuariosService service;
		try {
			// Acceso a la implementacion de la capa de negocio 
			// a trav��s de la factor��a
			service = Factories.services.createUsuariosService();
			User us = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LOGGEDIN_USER");
			//String email = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("email"));

			// De esta forma le damos informaci��n a toArray para poder hacer el casting a usuario[]
			usuarios = (List<Usuario>)service.getUsuariosSinUsLogin(us.getLogin());
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}

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
		System.out.println("BeanUsuarios - PostConstruct"); 
		//Buscamos el usuario en la sesión. Esto es un patrón factoría claramente.
		usuario = (BeanUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("usuario"));

		//si no existe lo creamos e inicializamos
		if (usuario == null) { 
			System.out.println("BeanUsuarios - No existia");
			usuario = new BeanUsuario();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "usuario", usuario);
		}
		aplicacion = (BeanAplicacion) FacesContext.getCurrentInstance()
				.getExternalContext().getApplicationMap().get(new String("aplicacion"));

		// si no existe lo creamos e inicializamos
		if (aplicacion == null) {
			aplicacion = new BeanAplicacion();
			FacesContext.getCurrentInstance().getExternalContext()
			.getApplicationMap().put("aplicacion", aplicacion);
		}

		seleccion=new ArrayList<Usuario>();

	}
	@PreDestroy
	public void end()  {
		System.out.println("BeanUsuarios - PreDestroy");
	}

}



