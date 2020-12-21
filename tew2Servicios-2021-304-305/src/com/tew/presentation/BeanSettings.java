package com.tew.presentation;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.tew.business.SettingsService;
import com.tew.infrastructure.Factories;
import com.tew.model.User;


public class BeanSettings implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	private static final long serialVersionUID = 2L;
	private static final Locale ENGLISH = new Locale("en");
	private static final Locale SPANISH = new Locale("es");
	private Locale locale = new Locale("es");
	private String url="";

	// uso de inyecciÃ³n de dependencia
	@ManagedProperty(value = "#{usuario}")
	private BeanUsuario usuario;

	public BeanUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(BeanUsuario usuario) {
		this.usuario = usuario;
	}

	public Locale getLocale() {
		// Aqui habria que cambiar algo de cÃ³digo para coger locale del
		// navegador
		// la primera vez que se accede a getLocale(), de momento dejamos como
		// idioma de partida â€œesâ€�
		return (locale);
	}

	public void setSpanish(ActionEvent event) {
		locale = SPANISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (usuario != null)
				if (usuario.getEmail()==null) //valores por defecto del alumno, si no NO inicializar
					usuario.iniciaUsuario(null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setEnglish(ActionEvent event) {
		locale = ENGLISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (usuario != null)
				if (usuario.getEmail()==null) //valores por defecto del alumno, si no NO inicializar
					usuario.iniciaUsuario(null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String reiniciar() { 
		SettingsService service;
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		FacesMessage msg = null;
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
		
		Map<String, Object> sesiones =  FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		User admin = (User) sesiones.get("LOGGEDIN_USER");
		sesiones.remove("LOGGEDIN_USER");
		try {
			service = Factories.services.createSettingsService();
			service.reinicioBBDD(url);
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
					bundle.getString("ReinicioBBDDT"), bundle.getString("ReinicioBBDDC"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			sesiones.put("LOGGEDIN_USER", admin);
			//Map<String, Object> i =  FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			//Set<Entry<String, Object>> j= i.entrySet();
			//Iterator<Entry<String, Object>> q = j.iterator();
			//System.out.println("HOLA CARACOLI");
			//while(q.hasNext()) {System.out.println(q.next().getKey());}

			return "exito";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}


		

	}

	// Se inicia correctamente el Managed Bean inyectado si JSF lo hubiera
	// creado
	// y en caso contrario se crea.
	// (hay que tener en cuenta que es un Bean de sesiÃ³n)

	// Se usa @PostConstruct, ya que en el contructor no se sabe todavÃ­a si
	// el MBean ya estaba construido y en @PostConstruct SI.
	@PostConstruct
	public void init() {
		System.out.println("BeanSettings - PostConstruct");
		// Buscamos el alumno en la sesiÃ³n. Esto es un patrÃ³n factorÃ­a
		// claramente.
		usuario = (BeanUsuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(new String("usuario"));

		// si no existe lo creamos e inicializamos
		if (usuario == null) {
			System.out.println("BeanSettings - No existia");
			usuario = new BeanUsuario();
			FacesContext.getCurrentInstance().getExternalContext()
			.getSessionMap().put("usuario", usuario);
		}
	}

	// Es sÃ³lo a modo de traza.
	@PreDestroy
	public void end() {
		System.out.println("BeanSettings - PreDestroy");
	}
	*/

}
