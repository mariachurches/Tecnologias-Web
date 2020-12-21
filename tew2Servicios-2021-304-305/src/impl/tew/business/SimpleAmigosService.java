package impl.tew.business;


import impl.tew.business.classes.*;


import java.util.List;


import com.tew.business.AmigosService;

import com.tew.business.exception.EntityNotFoundException;

import com.tew.model.Amigos;
import com.tew.persistence.exception.AlreadyPersistedException;

/**
 * Clase de implementación (una de las posibles) del interfaz de la fachada de
 * servicios
 * 
 * @author Enrique
 * 
 */
public class SimpleAmigosService implements AmigosService {

	@Override
	public List<Amigos> getInvitacionesAmistad(String email) throws Exception {
		return new ListadoInvitacionAmistad().getInvitacionesAmistad(email);
	}

	@Override
	public void aceptar(String emailUsuario, String emailAmigo) throws EntityNotFoundException, AlreadyPersistedException {
		new AceptarAmistad().aceptar(emailUsuario, emailAmigo);;
		
	}

	@Override
	public void envio(String email, String emailAmigo) throws EntityNotFoundException, AlreadyPersistedException {
		new EnvioAmistad().envio(email, emailAmigo);
		
	}

	@Override
	public void deleteAmigo(String email) throws EntityNotFoundException {
		new AmigosBaja().delete(email);
		
	}

	@Override
	public Amigos findByEmail(String email, String emailAmigo) throws EntityNotFoundException, AlreadyPersistedException {
		return new AmigosBusqueda().busqueda(email, emailAmigo);
		
	}

	@Override
	public void deleteAll() throws EntityNotFoundException {
		new AmigosBaja().deleteAll();
		
	}

	@Override
	public void mutuo(String email, String emailAmigo) throws EntityNotFoundException, AlreadyPersistedException {
		new AmigosAlta().mutuo(email, emailAmigo);
		
	}

	@Override
	public List<Amigos> getAmigos() throws Exception {
		return new GetAmigos().getAmigos();
	}
	

	
}
