package com.tew.business;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Usuario;

public interface UsuariosService {

	List<Usuario> getUsuarios(String rol) throws Exception;
	Usuario findByEmail(String email) throws EntityNotFoundException;
	void saveUsuario(Usuario usuario) throws EntityAlreadyExistsException;
	void updateUsuario(Usuario usuario) throws EntityNotFoundException;
	void deleteUsuario(String email) throws EntityNotFoundException;
	void deleteAll() throws EntityNotFoundException;
	List<Usuario> findByCadena(String cadena, String emailUsuario) throws EntityNotFoundException;
	List<Usuario> getUsuariosSinUsLogin(String email) throws Exception;
	
}
