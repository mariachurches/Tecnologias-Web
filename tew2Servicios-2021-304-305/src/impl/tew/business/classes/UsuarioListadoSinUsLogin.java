package impl.tew.business.classes;

import java.util.List;

import com.tew.infrastructure.Factories;
import com.tew.model.Usuario;
import com.tew.persistence.UsuarioDao;

public class UsuarioListadoSinUsLogin {
	public List<Usuario> getUsuariosSinUsLogin(String email) throws Exception {
		
		UsuarioDao dao = Factories.persistence.createUsuarioDao();
		return  dao.getUsuariosSinUsLogin(email);

		
	}
}
