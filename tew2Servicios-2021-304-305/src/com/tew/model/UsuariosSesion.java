package com.tew.model;

import java.util.ArrayList;

public class UsuariosSesion {
	
	private static ArrayList<Usuario> susuarios ;
	
	public UsuariosSesion() {}
	

	public static ArrayList<Usuario> getSusuarios() {
		if(susuarios == null) susuarios = new ArrayList<Usuario>();
		return susuarios;
	}

	public static void setSusuarios(ArrayList<Usuario> susuarios) {
		UsuariosSesion.susuarios = susuarios;
	}
	
	public void addUser(Usuario u) {
		System.out.println("Nevo usuario en el vector " + u.getEmail());
		susuarios = getSusuarios();
		susuarios.add(u);
		System.out.println("Nevo usuario en el vector quedan " + susuarios.size());

	}
	
	public void removeUser(String email ) {
		System.out.println("Borrado usuario en el vector" + susuarios.size() + email);
		for(int i=0; i< susuarios.size(); i++) {
			if(email.contains(susuarios.get(i).getEmail())) susuarios.remove(i);
			else {System.out.println("email no borrado " + susuarios.get(i).getEmail());}
		}
		System.out.println("Borrado usuario en el vector" + susuarios.size());

	}
	
	
	

}
