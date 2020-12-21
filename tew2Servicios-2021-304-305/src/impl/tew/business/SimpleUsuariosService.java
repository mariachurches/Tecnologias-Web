package impl.tew.business;

import java.util.List;

import com.tew.business.UsuariosService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Usuario;

import impl.tew.business.classes.UsuarioAlta;
import impl.tew.business.classes.UsuarioBaja;
import impl.tew.business.classes.UsuarioBuscar;
import impl.tew.business.classes.UsuarioBuscarByCadena;
import impl.tew.business.classes.UsuarioListado;
import impl.tew.business.classes.UsuarioListadoSinUsLogin;
import impl.tew.business.classes.UsuarioUpdate;

public class SimpleUsuariosService implements UsuariosService {

	@Override
	public List<Usuario> getUsuarios(String rol) throws Exception {
		return new UsuarioListado().getUsuarios(rol);
	}

	@Override
	public Usuario findByEmail(String email) throws EntityNotFoundException {
		return new UsuarioBuscar().find(email);
	}

	@Override
	public void saveUsuario(Usuario usuario) throws EntityAlreadyExistsException {
		new UsuarioAlta().save(usuario);
	}

	@Override
	public void updateUsuario(Usuario usuario) throws EntityNotFoundException {
		new UsuarioUpdate().update(usuario);
		
	}

	@Override
	public void deleteUsuario(String email) throws EntityNotFoundException {
		new UsuarioBaja().delete(email);
	}

	@Override
	public List<Usuario> findByCadena(String cadena, String emailUsuario) {
		return new UsuarioBuscarByCadena().findByCadena(cadena, emailUsuario);
	}

	@Override
	public List<Usuario> getUsuariosSinUsLogin(String email) throws Exception {
		return new UsuarioListadoSinUsLogin().getUsuariosSinUsLogin(email);
	}

	@Override
	public void deleteAll() throws EntityNotFoundException {
		new UsuarioBaja().deleteAll();
	}


}
