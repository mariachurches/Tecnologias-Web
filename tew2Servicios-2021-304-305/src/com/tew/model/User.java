package com.tew.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="user")
public class User implements Serializable {

	private static final long serialVersionUID = 3012229617706371812L;
	private String login;
	private String name;
	private String rol;
	
	//Constructores
	public User() {}
	
	public User(String newLogin, String newName, String rol) {
		this.login=newLogin;
		this.name=newName;
		this.rol=rol;
	}
	
	@XmlElement
	public String getRol() {return rol;}
	
	public void setRol(String rol) {
		this.rol = rol;
	}


	@XmlElement
	public String getLogin() {return login;}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	@XmlElement
	public String getName() {return name;}
	
	public void setName(String name) {
		this.name = name;
	}
	
	

}
