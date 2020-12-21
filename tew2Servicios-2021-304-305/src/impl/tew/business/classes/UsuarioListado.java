package impl.tew.business.classes;

import java.util.List;

import com.tew.infrastructure.Factories;
import com.tew.model.Usuario;
import com.tew.persistence.UsuarioDao;

public class UsuarioListado {
	public List<Usuario> getUsuarios(String rol) throws Exception {

		UsuarioDao dao = Factories.persistence.createUsuarioDao();
		return  dao.getUsuarios(rol);

		// Aqu?????? podr??????a ir m??????s l??????gica de negocio que procesase los datos traidos 
		// de persistencia
		// ...
	}
}
