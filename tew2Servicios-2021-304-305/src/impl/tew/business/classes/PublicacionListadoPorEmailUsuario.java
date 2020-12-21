package impl.tew.business.classes;

import java.util.List;

import com.tew.infrastructure.Factories;
import com.tew.model.Publicacion;
import com.tew.persistence.PublicacionDao;


public class PublicacionListadoPorEmailUsuario {
	public List<Publicacion> getPublicacionByEmail(String email) throws Exception {
		
		PublicacionDao dao = Factories.persistence.createPublicacionDao();
		return dao.findByEmail(email);
	}
}
