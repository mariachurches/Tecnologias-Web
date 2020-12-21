package com.tew.business;


import java.util.List;


import com.tew.business.exception.EntityNotFoundException;

import com.tew.model.Amigos;
import com.tew.persistence.exception.AlreadyPersistedException;


/**
 * Este es el interfaz que ofrecer�� cualquier implementaci��n de la clase fachada.
 * 
 * Al separar la implementaci��n de la fachada de su interfaz se permite cambiar 
 * las implementaciones reales de la fachada. Esto es muy ��til cuando se necesita 
 * a��adir funcionalidad extra como acceso remoto, web services,
 * control de acceso, etc. Al hacerlo de esta forma esos cambios solo 
 * afectan a las factorias y no al contenido de las capas. Las factor��as, en
 * un desarrollo profesional, se configuran declarativamente (properties, xml, etc)
 * 
 * @author alb
 *
 */
public interface AmigosService {

	List<Amigos> getAmigos() throws Exception;
	List<Amigos> getInvitacionesAmistad(String email) throws Exception;
	void aceptar(String emailUsuario, String emailAmigo) throws EntityNotFoundException, AlreadyPersistedException;
	void envio(String email, String emailAmigo) throws EntityNotFoundException, AlreadyPersistedException;
	void deleteAmigo(String email) throws EntityNotFoundException;
	void deleteAll() throws EntityNotFoundException;
	Amigos findByEmail(String email, String emailAmigo) throws EntityNotFoundException, AlreadyPersistedException;
	void mutuo(String email, String emailAmigo) throws EntityNotFoundException, AlreadyPersistedException;
	
	

}

