package impl.tew.business.resteasy;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.business.resteasy.UsuariosServicesRs;
import com.tew.model.Usuario;

import impl.tew.business.classes.UsuarioAlta;
import impl.tew.business.classes.UsuarioBaja;
import impl.tew.business.classes.UsuarioBuscar;
import impl.tew.business.classes.UsuarioBuscarByCadena;
import impl.tew.business.classes.UsuarioListado;
import impl.tew.business.classes.UsuarioListadoSinUsLogin;
import impl.tew.business.classes.UsuarioUpdate;

public class UsuariosServiceRsImpl implements UsuariosServicesRs {

	@Override
	public List<Usuario> getUsuarios(String rol) throws Exception {
		return new UsuarioListado().getUsuarios(rol);
	}

	@Override
	public Usuario findByEmail(String email) throws EntityNotFoundException {
		return new UsuarioBuscar().find(email);
	}

	@Override
	public List<Usuario> findByCadena(String cadena, String emailUsuario) throws EntityNotFoundException {
		return new UsuarioBuscarByCadena().findByCadena(cadena, emailUsuario);
	}

	@Override
	public List<Usuario> getUsuariosSinUsLogin(String email) throws Exception {
		return new UsuarioListadoSinUsLogin().getUsuariosSinUsLogin(email);
	}

	@Override
	public void deleteUsuario(String email) throws EntityNotFoundException {
		new UsuarioBaja().delete(email);
		
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
	public void deleteAll() throws EntityNotFoundException {
		new UsuarioBaja().deleteAll();
	}

	@Override
	public Usuario findByName(String name) throws EntityNotFoundException {
		return new UsuarioBuscar().findByName(name);
	}

}
