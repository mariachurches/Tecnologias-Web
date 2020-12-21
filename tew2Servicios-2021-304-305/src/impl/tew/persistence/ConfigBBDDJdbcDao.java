package impl.tew.persistence;

import java.io.StringReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.tew.model.Amigos;
import com.tew.model.Publicacion;
import com.tew.model.Usuario;
import com.tew.persistence.ConfigBBDDDao;
import impl.tew.business.resteasy.AmigosServicesRsImpl;
import impl.tew.business.resteasy.PublicacionesServiceRsImpl;
import impl.tew.business.resteasy.UsuariosServiceRsImpl;

public class ConfigBBDDJdbcDao implements ConfigBBDDDao{

	private String JSON_inicial = 
			
			"{\"usuarios\": [\r\n" + 
			"  {\r\n" + 
			"    \"email\": \"user1@dominio.com\",\r\n" + 
			"    \"passwd\": \"user1\",\r\n" + 
			"    \"rol\": \"usuario\",\r\n" + 
			"    \"nombre\": \"user1\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"email\": \"user2@dominio.com\",\r\n" + 
			"    \"passwd\": \"user2\",\r\n" + 
			"    \"rol\": \"usuario\",\r\n" + 
			"    \"nombre\": \"user2\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"email\": \"user3@dominio.com\",\r\n" + 
			"    \"passwd\": \"user3\",\r\n" + 
			"    \"rol\": \"usuario\",\r\n" + 
			"    \"nombre\": \"user3\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"email\": \"user4@dominio.com\",\r\n" + 
			"    \"passwd\": \"user4\",\r\n" + 
			"    \"rol\": \"usuario\",\r\n" + 
			"    \"nombre\": \"user4\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"email\": \"user5@dominio.com\",\r\n" + 
			"    \"passwd\": \"user5\",\r\n" + 
			"    \"rol\": \"usuario\",\r\n" + 
			"    \"nombre\": \"user5\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"email\": \"user6@dominio.com\",\r\n" + 
			"    \"passwd\": \"user6\",\r\n" + 
			"    \"rol\": \"usuario\",\r\n" + 
			"    \"nombre\": \"user6\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"email\": \"user7@dominio.com\",\r\n" + 
			"    \"passwd\": \"user7\",\r\n" + 
			"    \"rol\": \"usuario\",\r\n" + 
			"    \"nombre\": \"user7\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"email\": \"user8@dominio.com\",\r\n" + 
			"    \"passwd\": \"user8\",\r\n" + 
			"    \"rol\": \"usuario\",\r\n" + 
			"    \"nombre\": \"user8\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"email\": \"user9@dominio.com\",\r\n" + 
			"    \"passwd\": \"user9\",\r\n" + 
			"    \"rol\": \"usuario\",\r\n" + 
			"    \"nombre\": \"user9\"\r\n" + 
			"  }\r\n" + 
			"  ],\r\n" + 
			"  \"publicaciones\": [\r\n" + 
			"  {\r\n" + 
			"    \"email\": \"user1@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 1 user 1\",\r\n" + 
			"    \"texto\": \"Texto publicacion 1 user1\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"email\": \"user1@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 2 user 1\",\r\n" + 
			"    \"texto\": \"Texto publicacion 2 user1\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"email\": \"user1@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 3 user 1\",\r\n" + 
			"    \"texto\": \"Texto publicacion 3 user1\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"email\": \"user2@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 1 user 2\",\r\n" + 
			"    \"texto\": \"Texto publicacion 1 user2\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"email\": \"user2@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 2 user 2\",\r\n" + 
			"    \"texto\": \"Texto publicacion 2 user 2\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"email\": \"user2@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 3 user 2\",\r\n" + 
			"    \"texto\": \"Texto publicacion 3 user 2\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"email\": \"user3@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 1 user 3\",\r\n" + 
			"    \"texto\": \"Texto publicacion 1 user 3\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"email\": \"user3@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 2 user 3\",\r\n" + 
			"    \"texto\": \"Texto publicacion 2 user 3\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"    {\r\n" + 
			"    \"email\": \"user3@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 3 user 3\",\r\n" + 
			"    \"texto\": \"Texto publicacion 3 user 3\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"    {\r\n" + 
			"    \"email\": \"user4@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 1 user 4\",\r\n" + 
			"    \"texto\": \"Texto publicacion 1 user 4\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"    {\r\n" + 
			"    \"email\": \"user4@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 2 user 4\",\r\n" + 
			"    \"texto\": \"Texto publicacion 2 user 4\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"    {\r\n" + 
			"    \"email\": \"user4@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 3 user 4\",\r\n" + 
			"    \"texto\": \"Texto publicacion 3 user 4\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"    {\r\n" + 
			"    \"email\": \"user5@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 1 user 5\",\r\n" + 
			"    \"texto\": \"Texto publicacion 1 user 5\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"    {\r\n" + 
			"    \"email\": \"user5@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 2 user 5\",\r\n" + 
			"    \"texto\": \"Texto publicacion 2 user 5\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"    {\r\n" + 
			"    \"email\": \"user5@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 3 user 5\",\r\n" + 
			"    \"texto\": \"Texto publicacion 3 user 5\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"email\": \"user6@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 1 user 6\",\r\n" + 
			"    \"texto\": \"Texto publicacion 1 user 6\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"email\": \"user6@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 2 user 6\",\r\n" + 
			"    \"texto\": \"Texto publicacion 2 user 6\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"email\": \"user6@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 3 user 6\",\r\n" + 
			"    \"texto\": \"Texto publicacion 3 user 6\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"email\": \"user7@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 1 user 7\",\r\n" + 
			"    \"texto\": \"Texto publicacion 1 user 7\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"email\": \"user7@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 2 user 7\",\r\n" + 
			"    \"texto\": \"Texto publicacion 2 user 7\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"email\": \"user7@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 3 user 7\",\r\n" + 
			"    \"texto\": \"Texto publicacion 3 user 7\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"email\": \"user8@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 1 user 8\",\r\n" + 
			"    \"texto\": \"Texto publicacion 1 user 8\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"email\": \"user8@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 2 user 8\",\r\n" + 
			"    \"texto\": \"Texto publicacion 2 user 8\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"email\": \"user8@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 3 user 8\",\r\n" + 
			"    \"texto\": \"Texto publicacion 3 user 8\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"email\": \"user9@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 1 user 9\",\r\n" + 
			"    \"texto\": \"Texto publicacion 1 user 9\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"email\": \"user9@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 2 user 9\",\r\n" + 
			"    \"texto\": \"Texto publicacion 2 user 9\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"email\": \"user9@dominio.com\",\r\n" + 
			"    \"titulo\": \"Publicacion 3 user 9\",\r\n" + 
			"    \"texto\": \"Texto publicacion 3 user 9\",\r\n" + 
			"    \"fecha\": \"1542116773\"\r\n" + 
			"  }\r\n" + 
			"  ],\r\n" + 
			" \"amigos\":  \r\n" + 
			" [\r\n" + 
			"  {\r\n" + 
			"    \"emailusuario\": \"user1@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user2@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"emailusuario\": \"user1@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user3@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"emailusuario\": \"user1@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user4@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"emailusuario\": \"user2@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user3@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"emailusuario\": \"user2@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user4@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"emailusuario\": \"user2@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user5@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"emailusuario\": \"user3@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user4@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"emailusuario\": \"user3@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user5@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"    {\r\n" + 
			"    \"emailusuario\": \"user3@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user6@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"    {\r\n" + 
			"    \"emailusuario\": \"user4@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user5@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"    {\r\n" + 
			"    \"emailusuario\": \"user4@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user6@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"    {\r\n" + 
			"    \"emailusuario\": \"user4@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user7@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"    {\r\n" + 
			"    \"emailusuario\": \"user5@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user6@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"    {\r\n" + 
			"    \"emailusuario\": \"user5@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user7@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"    {\r\n" + 
			"    \"emailusuario\": \"user5@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user8@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"emailusuario\": \"user6@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user7@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"emailusuario\": \"user6@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user8@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"emailusuario\": \"user6@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user9@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"emailusuario\": \"user7@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user1@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"emailusuario\": \"user7@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user2@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"emailusuario\": \"user7@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user3@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"emailusuario\": \"user8@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user1@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"emailusuario\": \"user8@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user2@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"emailusuario\": \"user8@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user3@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"emailusuario\": \"user9@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user1@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"emailusuario\": \"user9@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user2@dominio.com\"\r\n" + 
			"  },\r\n" + 
			"      {\r\n" + 
			"    \"emailusuario\": \"user9@dominio.com\",\r\n" + 
			"    \"emailamigo\": \"user3@dominio.com\"\r\n" + 
			"  }\r\n" + 
			" ] \r\n" + 
			" }\r\n" + 
			"  \r\n" + "";
	
	
	private UsuariosServiceRsImpl func_usu = new UsuariosServiceRsImpl();
	private PublicacionesServiceRsImpl func_publi = new PublicacionesServiceRsImpl();
	private AmigosServicesRsImpl func_ami = new AmigosServicesRsImpl();

	@Override
	public void reiniciarBBDD(String url) { 
		
		//Borrar los datos de la BBDD
		try {
			func_ami.deleteAll();
			func_publi.deleteAll();
			func_usu.deleteAll();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String jsontxt="";
		
		if(url=="") jsontxt=JSON_inicial;
		else {
		Client cliente = ClientBuilder.newClient();
		WebTarget target = cliente.target(url);
		jsontxt = target.request().get().readEntity(String.class);}
		
		JSONObject o;
		Usuario u;
		Publicacion p;
		Amigos a;

		try {
			JSONObject json = (JSONObject) new JSONParser().parse(new StringReader(jsontxt));
			JSONArray usuarios = (JSONArray) json.get("usuarios");
			JSONArray publicaciones = (JSONArray) json.get("publicaciones");
			JSONArray amigos = (JSONArray) json.get("amigos");
			
			for(int i = 0; i < usuarios.size(); i++) {
				o = (JSONObject) usuarios.get(i);
				u = new Usuario(o.get("email").toString(), o.get("passwd").toString(), o.get("rol").toString(), o.get("nombre").toString());
				func_usu.saveUsuario(u);
			}
			
			
			
			for(int i = 0; i < publicaciones.size(); i++) {
				o = (JSONObject) publicaciones.get(i);
				Long fecha = Long.parseLong(o.get("fecha").toString());
				p = new Publicacion(o.get("email").toString(), o.get("titulo").toString(), o.get("texto").toString(),o.get("fecha").toString(), fecha);
				func_publi.savePublicacionBBDD(p);
			}


			for(int i = 0; i < amigos.size(); i++) {
				o = (JSONObject) amigos.get(i);
				a= new Amigos(o.get("emailusuario").toString(), o.get("emailamigo").toString(), true);	
				func_ami.mutuo(a.getEmail_amigo(), a.getEmail_usuario());
			}



		} catch (Exception e) {
			e.printStackTrace();
		}
		finally  {
		}

	}


}
