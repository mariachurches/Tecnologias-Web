 package com.tew.business;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Publicacion;

public interface PublicacionService {
	
	List<Publicacion> getPublicaciones() throws Exception;
	List<Publicacion> findByEmail(String email) throws Exception;
	void savePublicacion(Publicacion publicacion) throws EntityAlreadyExistsException;
	void updatePublicacion(Publicacion publicacion) throws EntityNotFoundException;
	void deletePublicacion(int id) throws EntityNotFoundException;
	void deleteAll() throws Exception;
	void deletePublicacionesAmigos(String email) throws EntityNotFoundException, Exception;
	List<Publicacion> ordenByFecha(String string) throws Exception;
	List<Publicacion> ordenByTitulo(String string) throws Exception;
	List<Publicacion> listadoPubliAmigos(List<String> amigos) throws Exception;
	List<String> getAmigos(String login) throws Exception;


}
