package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.persistence.AmigosDao;
import com.tew.persistence.exception.AlreadyPersistedException;

public class AceptarAmistad {

	public void aceptar(String emailUsuario, String emailAmigo) throws EntityNotFoundException, AlreadyPersistedException {
		AmigosDao dao = Factories.persistence.createAmigosDao();
		dao.aceptar(emailUsuario,emailAmigo);
	}

}
