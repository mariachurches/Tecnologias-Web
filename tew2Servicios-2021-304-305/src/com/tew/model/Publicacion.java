package com.tew.model;

import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="publicacion")
public class Publicacion {
	
	//Campos relacionados con las Publicaciones
	private int id;
	private String email; //not null
	private String titulo; //not null
	private String texto; 
	private Long fecha; //milisegundos
	private String fechaCadena; //String cadena
	
	//Constructores
	public Publicacion() {}
	
	public Publicacion(String email, String titulo, String texto, String fechaCadena, long fecha) {
		this.email = email;
		this.titulo = titulo;
		this.texto = texto;
		this.fechaCadena = fechaCadena;
		this.fecha=fecha;
	}
	
	//Obtenemos el id de la Publicacion
	@XmlElement
	public int getId() {return id;}
	
	public void setId(int id) {this.id = id;}
	
	//Obtenemos el email de la Publicacion
	@XmlElement
	public String getEmail() {return email;}
	
	public void setEmail(String email) {this.email = email;}
	
	//Obtenemos el titulo de la Publicacion
	@XmlElement
	public String getTitulo() {return titulo;}
	
	public void setTitulo(String titulo) {this.titulo = titulo;}
	
	//Obtenemos el texto de la Publicacion
	@XmlElement
	public String getTexto() {return texto;}
	
	public void setTexto(String texto) {this.texto = texto;}
	
	//Obtenemos la fecha de la Publicacion
	@XmlElement
	public Long getFecha() {return fecha;}
	
	public void setFecha(Long fecha) {this.fecha = fecha;}
	
	//Obtenemos la fecha en formato cadena
	@XmlElement
	public String getFechaCadena() {return fechaCadena;}
	
	public void setFechaCadena(long fecha) {SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); this.fechaCadena=formatter.format(fecha);}
	
	
	

}
