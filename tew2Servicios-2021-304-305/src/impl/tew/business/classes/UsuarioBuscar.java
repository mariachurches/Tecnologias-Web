package impl.tew.business.classes;

import com.tew.infrastructure.Factories;
import com.tew.model.Usuario;
import com.tew.persistence.UsuarioDao;

public class UsuarioBuscar {

	public Usuario find(String email)  {
		UsuarioDao dao = Factories.persistence.createUsuarioDao();
		Usuario a = dao.findByEmail(email);

		
		return a;
	}
	
	public Usuario findByName(String name)  {
		UsuarioDao dao = Factories.persistence.createUsuarioDao();
		Usuario a = dao.findByName(name);

		
		return a;
	}

}
