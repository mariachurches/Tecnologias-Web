package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Amigos;
import com.tew.persistence.AmigosDao;
import com.tew.persistence.exception.AlreadyPersistedException;

public class AmigosBusqueda {

	public Amigos busqueda(String email, String emailAmigo) throws EntityNotFoundException, AlreadyPersistedException {
		AmigosDao dao = Factories.persistence.createAmigosDao();
		return dao.findByEmail(email, emailAmigo);
	}

}
