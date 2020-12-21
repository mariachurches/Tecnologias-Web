package impl.tew.business.resteasy;

import java.util.List;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.business.resteasy.AmigosServicesRs;
import com.tew.model.Amigos;
import com.tew.model.Usuario;
import com.tew.persistence.exception.AlreadyPersistedException;
import impl.tew.business.classes.AceptarAmistad;
import impl.tew.business.classes.AmigosAlta;
import impl.tew.business.classes.AmigosBaja;
import impl.tew.business.classes.AmigosBusqueda;
import impl.tew.business.classes.EnvioAmistad;
import impl.tew.business.classes.GetAmigos;
import impl.tew.business.classes.ListadoInvitacionAmistad;

public class AmigosServicesRsImpl implements AmigosServicesRs{

	@Override
	public void aceptar(String emailUsuario, String emailAmigo)
			throws EntityNotFoundException, AlreadyPersistedException {
		new AceptarAmistad().aceptar(emailUsuario, emailAmigo);
		
	}

	@Override
	public void envio(String email, String emailAmigo) throws EntityNotFoundException, AlreadyPersistedException {
		new EnvioAmistad().envio(email, emailAmigo);
		
	}

	@Override
	public List<Amigos> getInvitacionesAmistad(String email) throws Exception {
		return new ListadoInvitacionAmistad().getInvitacionesAmistad(email);
	}

	@Override
	public Amigos findByEmail(String email, String emailAmigo)
			throws EntityNotFoundException, AlreadyPersistedException {
		return new AmigosBusqueda().busqueda(email, emailAmigo);
	}

	@Override
	public void deleteAmigo(String email) throws EntityNotFoundException {
		new AmigosBaja().delete(email);
	}
	
	@Override
	public void deleteAll() throws EntityNotFoundException{
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

	@Override
	public List<Usuario> getNoAmigos(String email) throws Exception {
		return new GetAmigos().getNoAmigos(email);
	}



}
