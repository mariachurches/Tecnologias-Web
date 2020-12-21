package com.tew.business.resteasy;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.tew.business.PublicacionService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Publicacion;

//URL en la que el web service responderá
@Path("{rolID}/PublicacionesServicesRs")
public interface PublicacionesServicesRs extends PublicacionService {
	

	   // responde a peticiones GET
	   @GET
	   @Path("getPublicaciones/")
	   @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	   public List<Publicacion> getPublicaciones() throws Exception;
		
	   
	   @GET
	   @Path("findByEmail/{email}")
	   // formato en el que los datos se retornan en el método
	   @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	   List<Publicacion> findByEmail(@PathParam("email") String email) throws Exception;
	   
	   @GET
	   @Path("ordenByFecha/{string}")
	   @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	   List<Publicacion> ordenByFecha(@PathParam("string") String string) throws Exception;
	   
	   @GET
	   @Path("ordenByTitulo/{string}")
	   @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	   List<Publicacion> ordenByTitulo(@PathParam("string") String string) throws Exception;
	   
	   @GET
	   @Path("listadoPubliAmigos/{amigos}")
	   @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	   List<Publicacion> listadoPubliAmigos(@PathParam("amigos") List<String> amigos) throws Exception;
	   
	   @GET
	   @Path("getAmigos/{email}")
	   @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	   List<String> getAmigos(@PathParam("email") String email) throws Exception;
		
	   // responde a peticiones DELETE
	   @DELETE
	   @Path("deletePublicacion/{id}")
	   void deletePublicacion(@PathParam("id") int id) throws EntityNotFoundException;
	   
	   @DELETE
	   @Path("deletePublicacionesAmigos/{email}")
	   void deletePublicacionesAmigos(@PathParam("email") String email) throws Exception;

	   @DELETE
	   void deleteAll() throws Exception;

	   // responde a peticiones PUT
	   @PUT
	   @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	   void savePublicacion(Publicacion publicacion) throws EntityAlreadyExistsException;

		
	   // responde a peticiones POST
	   @POST
	   @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	   void updatePublicacion(Publicacion publicacion) throws EntityNotFoundException;


	   void savePublicacionBBDD(Publicacion publicacion) throws EntityAlreadyExistsException;


}
