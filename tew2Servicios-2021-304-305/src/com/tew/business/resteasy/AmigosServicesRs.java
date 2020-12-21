package com.tew.business.resteasy;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.tew.business.AmigosService;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Amigos;
import com.tew.model.Usuario;
import com.tew.persistence.exception.AlreadyPersistedException;

//URL en la que el web service responderá
@Path("{rolID}/AmigosServicesRs")

public interface AmigosServicesRs extends AmigosService {

	@GET
	@Path("getAmigos/")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Amigos> getAmigos() throws Exception;
	
	@GET
	@Path("getNoAmigos/{email}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Usuario> getNoAmigos(@PathParam("email") String email) throws Exception;
	
	// responde a peticiones GET
	@GET
	@Path("getInvitacionesAmistad/{email}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Amigos> getInvitacionesAmistad(@PathParam("email") String email) throws Exception;


	@GET
	// paramentro indicado en la URL, utilizado el método con @PathParam
	@Path("findByEmail/{email}/{emailAmigo}")
	// formato en el que los datos se retornan en el método
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Amigos findByEmail(@PathParam("email") String email,@PathParam("emailAmigo") String emailAmigo) throws EntityNotFoundException, AlreadyPersistedException;

	// responde a peticiones DELETE
	@DELETE
	@Path("deleteAmigo/{email}")
	void deleteAmigo(@PathParam("email") String email) throws EntityNotFoundException;

	@DELETE
	void deleteAll() throws EntityNotFoundException;


	// responde a peticiones PUT
	@POST
	@Path("aceptar/{email}/{emailAmigo}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void aceptar(@PathParam("email") String emailUsuario,@PathParam("emailAmigo") String emailAmigo) throws EntityNotFoundException, AlreadyPersistedException;

	// responde a peticiones PUT
	@GET
	@Path("envio/{email}/{emailAmigo}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void envio(@PathParam("email") String email,@PathParam("emailAmigo") String emailAmigo) throws EntityNotFoundException, AlreadyPersistedException;

	@PUT
	@Path("mutuo/{email}/{emailAmigo}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void mutuo(@PathParam("email") String email,@PathParam("emailAmigo") String emailAmigo) throws EntityNotFoundException, AlreadyPersistedException;
}
