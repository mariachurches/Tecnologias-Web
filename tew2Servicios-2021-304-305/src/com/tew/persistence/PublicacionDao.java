package com.tew.persistence;

import java.util.List;

import com.tew.model.Publicacion;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public interface PublicacionDao {
	
	List<Publicacion> getPublicaciones();
	void save(Publicacion publicacion) throws AlreadyPersistedException;
	void update(Publicacion publicacion) throws NotPersistedException;
	void delete(int id) throws NotPersistedException;
	void deleteP(String email) throws NotPersistedException;
	Publicacion findById(int id);
	List<Publicacion> findByEmail(String email);
	List<Publicacion> ordenByFechas(String email);
	List<Publicacion> ordenByTitulo(String email);
	List<String> getAmigos(String email);
	List<Publicacion> listadoPubliAmigos(List<String> amigos);
	void deleteAll() throws NotPersistedException;
	void saveBBDD(Publicacion publicacion) throws AlreadyPersistedException;

	
}
