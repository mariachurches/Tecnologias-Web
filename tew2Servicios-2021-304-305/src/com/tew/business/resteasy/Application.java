package com.tew.business.resteasy;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import com.tew.business.SettingsService;
import com.tew.infrastructure.Factories;
import impl.tew.business.resteasy.AmigosServicesRsImpl;
import impl.tew.business.resteasy.PublicacionesServiceRsImpl;
import impl.tew.business.resteasy.SettingsServiceRsImpl;
import impl.tew.business.resteasy.UsuariosServiceRsImpl;

@SuppressWarnings("unchecked")
public class Application extends javax.ws.rs.core.Application {

	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public Application() {

		classes.add(AmigosServicesRsImpl.class);
		classes.add(PublicacionesServiceRsImpl.class);
		classes.add(UsuariosServiceRsImpl.class);
		classes.add(SettingsServiceRsImpl.class);
		
		//Reiniciar BBDD
		SettingsService settings = Factories.services.createSettingsService();
		try {settings.reinicioBBDD("");} catch (Exception e) {e.printStackTrace();}
		
	} 
 	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}
 	
	@Override
	public Set<Object> getSingletons() {
		return Collections.EMPTY_SET;
	}
}
