package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.persistence.AmigosDao;
import com.tew.persistence.exception.AlreadyPersistedException;


public class EnvioAmistad {

	public void envio(String email, String emailAmigo) throws EntityNotFoundException, AlreadyPersistedException {
		AmigosDao dao = Factories.persistence.createAmigosDao();
		dao.envio(email,emailAmigo);
	}

}
