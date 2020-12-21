package impl.tew.business;

import java.util.List;

import com.tew.business.PublicacionService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Publicacion;

import impl.tew.business.classes.PublicacionAlta;
import impl.tew.business.classes.PublicacionListado;
import impl.tew.business.classes.PublicacionListadoOrdenPorFecha;
import impl.tew.business.classes.PublicacionListadoOrdenPorTitulo;
import impl.tew.business.classes.PublicacionListadoPorAmigos;
import impl.tew.business.classes.PublicacionListadoPorEmailUsuario;
import impl.tew.business.classes.PublicacionesBaja;

public class SimplePublicacionService implements PublicacionService{

	@Override
	public List<Publicacion> getPublicaciones() throws Exception {
		return new PublicacionListado().getPublicacion();
	}

	@Override
	public List<Publicacion> findByEmail(String email) throws Exception {
		return new PublicacionListadoPorEmailUsuario().getPublicacionByEmail(email);
	}

	@Override
	public void savePublicacion(Publicacion publicacion) throws EntityAlreadyExistsException {
		new PublicacionAlta().save(publicacion);
	}

	@Override
	public void updatePublicacion(Publicacion publicacion) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePublicacion(int id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void  deletePublicacionesAmigos(String email) throws EntityNotFoundException {
		try {
			new PublicacionesBaja().deleteP(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Publicacion> ordenByFecha(String email) throws Exception {
		return new PublicacionListadoOrdenPorFecha().getPublicacionOrdenByFecha(email);
	}

	@Override
	public List<Publicacion> ordenByTitulo(String email) throws Exception {
		return new PublicacionListadoOrdenPorTitulo().getPublicacionOrdenByTitulo(email);
	}

	@Override
	public void deleteAll() throws EntityNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Publicacion> listadoPubliAmigos(List<String> amigos) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAmigos(String login) {
		// TODO Auto-generated method stub
		return null;
	}





}
