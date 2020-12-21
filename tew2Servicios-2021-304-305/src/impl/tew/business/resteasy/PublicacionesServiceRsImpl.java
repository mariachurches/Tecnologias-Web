package impl.tew.business.resteasy;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.business.resteasy.PublicacionesServicesRs;
import com.tew.model.Publicacion;

import impl.tew.business.classes.PublicacionAlta;
import impl.tew.business.classes.PublicacionListado;
import impl.tew.business.classes.PublicacionListadoOrdenPorFecha;
import impl.tew.business.classes.PublicacionListadoOrdenPorTitulo;
import impl.tew.business.classes.PublicacionListadoPorAmigos;
import impl.tew.business.classes.PublicacionListadoPorEmailUsuario;
import impl.tew.business.classes.PublicacionesBaja;

public class PublicacionesServiceRsImpl implements PublicacionesServicesRs{

	@Override
	public List<Publicacion> getPublicaciones() throws Exception {
		return new PublicacionListado().getPublicacion();
	}

	@Override
	public List<Publicacion> findByEmail(String email) throws Exception {
		return new PublicacionListadoPorEmailUsuario().getPublicacionByEmail(email);
	}

	@Override
	public List<Publicacion> ordenByFecha(String string) throws Exception {
		return new PublicacionListadoOrdenPorFecha().getPublicacionOrdenByFecha(string);
	}

	@Override
	public List<Publicacion> ordenByTitulo(String string) throws Exception {
		return new PublicacionListadoOrdenPorTitulo().getPublicacionOrdenByTitulo(string);
	}

	@Override
	public List<Publicacion> listadoPubliAmigos(List<String> amigos) throws Exception {
		return new PublicacionListadoPorAmigos().getPublicacionAmigos(amigos);
	}

	@Override
	public List<String> getAmigos(String email) throws Exception {
		return new PublicacionListadoPorAmigos().getAmigos(email);
	}

	@Override
	public void deletePublicacion(int id) throws EntityNotFoundException {
		
	}

	@Override
	public void deletePublicacionesAmigos(String email) throws Exception {
		new PublicacionesBaja().deleteP(email);
	}

	@Override
	public void savePublicacion(Publicacion publicacion) throws EntityAlreadyExistsException {
		new PublicacionAlta().save(publicacion);
	}
	
	@Override
	public void savePublicacionBBDD(Publicacion publicacion) throws EntityAlreadyExistsException {
		new PublicacionAlta().saveBBDD(publicacion);
	}

	@Override
	public void updatePublicacion(Publicacion publicacion) throws EntityNotFoundException {
		
	}

	@Override
	public void deleteAll() throws Exception {
			new PublicacionesBaja().deleteAll();
	}

}
