package com.tew.business.resteasy;

import java.util.ArrayList;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.tew.business.SettingsService;

@Path("{rolID}/SettingsServiceRs")
public interface SettingsServiceRs extends SettingsService {
	
	   @GET
	   @Path("{url}")
	   void reinicioBBDD(@PathParam("url") String url) throws Exception;
	   
		@PUT
		@Path("{email}")
		void anadirSesion(@PathParam("email") String email) throws Exception;
	   
		// responde a peticiones DELETE
		@DELETE
		@Path("{email}")
		void deleteSesion(@PathParam("email") String email) throws Exception;

		@GET
		@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		ArrayList<String> getUsuarios() throws Exception;
}
