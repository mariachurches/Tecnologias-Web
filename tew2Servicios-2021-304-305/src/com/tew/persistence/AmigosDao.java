package com.tew.persistence;

import java.util.List;

import com.tew.model.Amigos;
import com.tew.model.Usuario;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public interface AmigosDao {
	
	List<Amigos> getAmigos();
	void save(Amigos amigos) throws AlreadyPersistedException;
	void update(Amigos amigos) throws NotPersistedException;
	void delete(String email) throws NotPersistedException;
	Amigos findByEmail(String emailUsuario, String emailAmigo);
	List<Amigos> getInvitacionesAmistad(String email);
	void aceptar(String emailUsuario, String emailAmigo) throws AlreadyPersistedException;
	void envio(String emailUsuario, String emailAmigo) throws AlreadyPersistedException;
	void deleteAll() throws NotPersistedException;
	void mutuo(String emailUsuario, String emailAmigo) throws AlreadyPersistedException;
	List<Usuario> getNoAmigos(String email);
	
}
