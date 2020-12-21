package com.tew.persistence;

import java.util.List;

import com.tew.model.Usuario;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;


/**
 * Interfaz de la fachada a servicios de persistencia para la entidad Usuario
 *
 */


public interface UsuarioDao {

	List<Usuario> getUsuarios(String rol);
	void save(Usuario a) throws AlreadyPersistedException;
	void update(Usuario a) throws NotPersistedException;
	void delete(String email) throws NotPersistedException;
	Usuario findByEmail(String email);
	List<Usuario> findByCadena(String cadena, String emailUsuario);
	List<Usuario> getUsuariosSinUsLogin(String email);
	void deleteAll() throws NotPersistedException;
	Usuario findByName(String name);

}

