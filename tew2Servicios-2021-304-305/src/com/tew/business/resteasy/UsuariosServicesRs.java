package com.tew.business.resteasy;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.tew.business.UsuariosService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Usuario;

//URL en la que el web service responderá
@Path("{rolID}/UsuariosServicesRs")
public interface UsuariosServicesRs extends UsuariosService {

	// responde a peticiones GET
	@GET
	@Path("getUsuarios/{rol}")
	@RolesAllowed("roles")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Usuario> getUsuarios(@PathParam("rol") String rol) throws Exception;


	@GET
	@Path("{email}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Usuario findByEmail(@PathParam("email") String email) throws EntityNotFoundException;

	@GET
	@Path("findByName/{name}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Usuario findByName(@PathParam("name") String name) throws EntityNotFoundException;

	@GET
	@Path("findByCadena/{cadena}/{emailUsuario}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Usuario> findByCadena(@PathParam("cadena") String cadena,@PathParam("emailUsuario") String emailUsuario) throws EntityNotFoundException;
	
	@GET
	@Path("getUsuariosSinUsLogin/{email}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Usuario> getUsuariosSinUsLogin(@PathParam("email") String email) throws Exception;

	// responde a peticiones DELETE
	@DELETE
	@Path("deleteUsuario/{email}")
	void deleteUsuario(@PathParam("email") String email) throws EntityNotFoundException;

	@DELETE
	void deleteAll() throws EntityNotFoundException;

	// responde a peticiones PUT
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void saveUsuario(Usuario usuario) throws EntityAlreadyExistsException;

	// responde a peticiones POST
	@POST
	// formato en que los datos se reciben en el método
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void updateUsuario(Usuario usuario) throws EntityNotFoundException;
}	
