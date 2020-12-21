package impl.tew.business.classes;

import java.util.List;

import com.tew.infrastructure.Factories;
import com.tew.model.Usuario;
import com.tew.persistence.UsuarioDao;

public class UsuarioBuscarByCadena {

	public List<Usuario> findByCadena(String cadena, String emailUsuario)  {
		UsuarioDao dao = Factories.persistence.createUsuarioDao();
		
		return dao.findByCadena(cadena, emailUsuario);
		
	}

}
