package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.persistence.AmigosDao;
import com.tew.persistence.exception.NotPersistedException;

public class AmigosBaja {
	
	public void delete(String email) throws EntityNotFoundException {

		AmigosDao dao = Factories.persistence.createAmigosDao();
		try {
			dao.delete(email);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Amigo no eliminado " + email, ex);
		}

	}
	
	public void deleteAll() throws EntityNotFoundException {
		AmigosDao dao = Factories.persistence.createAmigosDao();
		try {
			dao.deleteAll();
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("No hay relaciones de amistad");
		}
	}
}
