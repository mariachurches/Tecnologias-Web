package com.tew.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="usuario")
public class Usuario {

	//Campos relacionados con el Usuario
	private String email;
	private String passwd; //not null
	private String rol; //not null
	private String nombre; //not null
	
	
	//Constructores
	public Usuario() {}
	
	public Usuario(String email, String passwd, String rol, String nombre) {
		this.email = email;
		this.passwd = passwd;
		this.rol = rol;
		this.nombre = nombre;
	}
	
	//Obtenemos el email del Usuario
	@XmlElement
	public String getEmail() {return email;}
	
	public void setEmail(String email) {this.email = email;}
	
	//Obtenemos la contraseña del Usuario
	@XmlElement
	public String getPasswd() {return passwd;}
	
	public void setPasswd(String passwd) {this.passwd = passwd;}
	
	//Obtenemos el rol del Usuario
	@XmlElement
	public String getRol() {return rol;}
	
	public void setRol(String rol) {this.rol = rol;}
	
	//Obtenemos el nombre del Usuario
	@XmlElement
	public String getNombre() {return nombre;}
	
	public void setNombre(String nombre) {this.nombre = nombre;}
	

}
