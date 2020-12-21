package impl.tew.business.classes;

import java.util.List;

import com.tew.infrastructure.Factories;
import com.tew.model.Publicacion;
import com.tew.persistence.PublicacionDao;


public class PublicacionListadoPorAmigos {
	
	public List<Publicacion> getPublicacionAmigos(List<String> amigos) {
		PublicacionDao dao = Factories.persistence.createPublicacionDao();
		return dao.listadoPubliAmigos(amigos);
	}

	public List<String> getAmigos(String email) {
		PublicacionDao dao = Factories.persistence.createPublicacionDao();
		return dao.getAmigos(email);
	}

	
}
