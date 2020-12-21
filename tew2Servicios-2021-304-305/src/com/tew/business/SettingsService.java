package com.tew.business;

import java.util.ArrayList;

public interface SettingsService {
	
	void reinicioBBDD(String url) throws Exception;
	
	void añadirSesion(String email) throws Exception;
	
	void deleteSesion(String email) throws Exception;
	
	ArrayList<String> getUsuarios() throws Exception;


}
