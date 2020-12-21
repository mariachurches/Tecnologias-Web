package com.tew.business;

public interface ServicesFactory {
	
	LoginService createLoginService();
	UsuariosService createUsuariosService();
	SettingsService createSettingsService();
	PublicacionService createPublicacionService();
	AmigosService createAmigosService();

}