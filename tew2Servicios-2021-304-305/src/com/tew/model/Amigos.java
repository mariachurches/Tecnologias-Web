package com.tew.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="amigo")
public class Amigos {
	
	//Campos relacionados con los Amigos
	private String email_usuario;
	private String email_amigo;
	private boolean aceptada;
	
	//Constructores
	public Amigos() {}
	
	public Amigos(String email_usuario, String email_amigo, boolean aceptada) {
		this.email_usuario = email_usuario;
		this.email_amigo = email_amigo;
		this.aceptada = aceptada;
	}
	
	//Obtenemos el email del Usuario
	@XmlElement
	public String getEmail_usuario() {return email_usuario;}
	
	public void setEmail_usuario(String email_usuario) {this.email_usuario = email_usuario;}
	
	//Obtenemos el email del Usuario amigo
	@XmlElement
	public String getEmail_amigo() {return email_amigo;}
	
	public void setEmail_amigo(String email_amigo) {this.email_amigo = email_amigo;}
	
	//Obtenemos si se ha aceptado la peticion de amistad
	@XmlElement
	public boolean isAceptada() {return aceptada;}
	
	public void setAceptada(boolean aceptada) {this.aceptada = aceptada;}
	
}
